package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

    public RegisterServlet() {
        super();
    }


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String eid = request.getParameter("eid"); //eid
      String last_name = request.getParameter("lname");
      String em = request.getParameter("email");
      String hd = request.getParameter("hiredate");
      String first_name = request.getParameter("fname");
      EmpDAO dao = new EmpDAO();
      Employee emp = new Employee();
      emp.setEmployeeId(Integer.parseInt(eid));
      emp.setLastName(last_name);
      emp.setEmail(em);
      emp.setHireDate(hd);
      emp.setFirstName(first_name);
      
      dao.insertEmp(emp);
      
      // {"id":?, "first_name":?, "last_name":?, "email":?, "hire_date":?}
      PrintWriter out = response.getWriter();
//      out.println("{\"id\":" + emp.getEmployeeId() 
//      + ",\"first_name\":\"" + emp.getFirstName()
//      + "\",\"email\":\"" + emp.getEmail()
//      + "\",\"hire_date\":\"" + emp.getHireDate()
//      + "\",\"last_name\":\"" + emp.getLastName() + "\"}");
      
      Gson gson = new GsonBuilder().create();
      out.println(gson.toJson(emp));
      System.out.println(eid+','+last_name+','+em+','+hd+','+first_name);
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}