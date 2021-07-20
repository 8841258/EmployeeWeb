package fullCalendar;

import java.io.IOException;
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

@WebServlet("/DeleteScheduleServ")
public class DeleteScheduleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteScheduleServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FullCalendar cal = new FullCalendar();
		cal.setTitle(request.getParameter("title"));
		
		CalDAO dao = new CalDAO();
		String val = dao.deleteSchedule(cal);
		
		response.getWriter().print(val);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
