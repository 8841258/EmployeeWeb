package comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CommentsServlet")
//HttpServlet 상속
public class CommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentsServlet() {
		super();
	}

	//ajax에서 servlet을 호출할 때 GET을 사용했으므로, doGET을 사용
	//request, response를 파라미터로 사용
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//응답할 타입은 html. sb에 담기는 것은 xml이지만, xml 작성은 html로 하기 때문인듯....???
		response.setContentType("text/html;charset=utf-8");

		//응답할 때 텍스트 기록.
		PrintWriter out = response.getWriter();
		//클라이언트로부터 온 servlet 요청에 요청 파라미터가 같이 온다. 그것을 스트링으로 선언.
		String cmd = request.getParameter("cmd");

		//comments.html에서 xhttp.open()에 cmd=~~~~ 값에 따라 처리하는 부분
		if (cmd == null) {
			//서버를 구성하고 다중의 사용자가 접근이 가능해야할 경우 StringBuffer를 사용한다.
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("<code>error</code>");
			sb.append("<data>");
			sb.append("cmd null");
			sb.append("</data>");
			sb.append("</result>");
			out.print(sb.toString());
			
		} else if (cmd.equals("selectAll")) {
			//getInstance() 메소드에 의해 한번만 할당된 객체(싱글톤)의 주소값을 참조
			List<HashMap<String, Object>> list = CommentsDAO.getInstance().selectAll();
			//여기서 쓰는 selectAll은 제일 밑에 있는 거임
			out.print(selectAll(list));
			
		} else if (cmd.equals("insert")) {
			Comments comment = new Comments();
			comment.setContent(request.getParameter("content"));
			comment.setName(request.getParameter("name"));
			HashMap<String, Object> map = CommentsDAO.getInstance().insert(comment);
			out.println(toXML(map));
			
		} else if (cmd.equals("update")) {
			
		}
	}

	private String toXML(HashMap<String, Object> map) {
		//StringBuffer랑 거의 비슷하다.
		StringBuilder sb = new StringBuilder();
		sb.append("<result>");
		sb.append("<code>");
		sb.append(map.get("code"));
		sb.append("</code>");
		sb.append("<data>");
		Gson gson = new GsonBuilder().create();
		sb.append(gson.toJson(map));
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString();
	}
	
	
	private String selectAll(List<HashMap<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append("<data>");
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			sb.append("{");
			sb.append("id:" + map.get("id"));
			sb.append(", name:'" + map.get("name"));
			sb.append("', content:'" + map.get("content"));
			sb.append("'}");
			if (i != list.size()-1) {
				sb.append(",");
			}
		}
		sb.append("]");
		sb.append("</data>");
		sb.append("</result>");

		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
