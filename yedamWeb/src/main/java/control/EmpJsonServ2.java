package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import common.EmpDAO;
import common.Employee;

@WebServlet("/EmpJsonServ2")
public class EmpJsonServ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpJsonServ2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		JsonArray oary = new JsonArray();
		EmpDAO dao = new EmpDAO();
		List <Employee> list = dao.getEmpList();
		
		
		for (Employee emp : list) {
			JsonObject iobj = new JsonObject();
			iobj.addProperty("eid", emp.getEmployeeId());
			iobj.addProperty("fn", emp.getFirstName());
			iobj.addProperty("ln", emp.getLastName());
			iobj.addProperty("sa", emp.getSalary());
			iobj.addProperty("em", emp.getEmail());
			iobj.addProperty("hd", emp.getHireDate());
			
			oary.add(iobj);
		}
		JsonObject obj = new JsonObject();
		obj.add("data", oary);
		
        out.println(gson.toJson(obj));
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
