package enrollClasses;

import db.DBtool;
import subject_info.SubInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/enroll")
public class enroll extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String sudCode = null;
		sudCode = request.getParameter("sudCode");


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();
			String sql = "SELECT * FROM enroll_web.subject_info WHERE sub_code =?";
			pstmt.setString(1, sudCode);
			rs = pstmt.executeQuery();

			if(rs.next()){
				
			}else{
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DBtool.closeResultSet(rs);
			DBtool.closePstmt(pstmt);
			DBtool.closeConn(conn);
		}

	}

}
