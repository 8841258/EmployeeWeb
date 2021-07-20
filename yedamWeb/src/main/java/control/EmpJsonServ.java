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

@WebServlet("/EmpJsonServ")
public class EmpJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpJsonServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//{"data" : [ [ ], [ ], [ ], [ ] ] } empl_demo
		PrintWriter out = response.getWriter();
//		Gson gson =  new Gson(); //순서 없이 출력
		Gson gson =new GsonBuilder().create(); //순서대로 출력
		
		EmpDAO dao = new EmpDAO();
		List <Employee> list = dao.getEmpList();
		JsonArray oAry = new JsonArray();
		for (Employee emp : list) {
			JsonArray iAry = new JsonArray();
			iAry.add(emp.getEmployeeId());
			iAry.add(emp.getFirstName());
			iAry.add(emp.getLastName());
			iAry.add(emp.getEmail());
			iAry.add(emp.getHireDate());
			iAry.add(emp.getSalary());
			
			oAry.add(iAry);
		}
		JsonObject obj = new JsonObject();
		obj.add("data", oAry);
		
		out.println(gson.toJson(obj));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
