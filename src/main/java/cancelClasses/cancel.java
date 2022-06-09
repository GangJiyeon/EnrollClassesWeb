package cancelClasses;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cancel")
public class cancel extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean isLogin = (boolean)session.getAttribute("isLogin");

		
		if(!isLogin) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			return;
		}
	}
}
