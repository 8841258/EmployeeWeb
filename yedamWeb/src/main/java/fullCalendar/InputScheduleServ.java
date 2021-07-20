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

@WebServlet("/InputScheduleServ")
public class InputScheduleServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InputScheduleServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터를 통해서 사용자 입력 값을 받아옴
		//정상 입력 => success
		//비정상 처리 => fail
		FullCalendar cal = new FullCalendar();
		cal.setTitle(request.getParameter("title"));
		cal.setStartDate(request.getParameter("start"));
		cal.setEndDate(request.getParameter("end"));
		
		CalDAO dao = new CalDAO();
		String val = dao.insertSchedule(cal);
		
		response.getWriter().print(val);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
