package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/status")
public class loginstatus extends HttpServlet {       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 상태 정보 꺼내기
		HttpSession session = request.getSession();
		String Name = (String)session.getAttribute("Name"); //object 타입이라 형변환 필요 
		
		if(Name != null) {
			//꺼낸 상태 정보를 클라이언트에게 전달하기
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter output = response.getWriter();
			output.print(Name);
			
			output.close();
		}else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		
		
		
	}

	

}
