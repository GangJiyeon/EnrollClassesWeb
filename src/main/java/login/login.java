package login;

import db.DBtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("UTF-8");
		
		
		//사용자가 입력한 값을 받아와서 
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		String userName = null;

		//필요한 것 끄내기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//db에 접속해서 입력받은 id, pw가 있는 지 확인하기
		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();
			//쿼리문 작성
			String sql = "SELECT * FROM enroll_web.student_info WHERE id=? AND pw=?";
			pstmt = DBtool.getPstmt(conn, sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);

			rs = pstmt.executeQuery();

			//조회 결과
			if(rs.next()){//조회결과가 있다면 로그인 성공
				userName = rs.getString("stu_name");
				HttpSession session = request.getSession(); //세션 꺼내기
				session.setMaxInactiveInterval(1800);
				session.setAttribute("islogin", true);
				session.setAttribute("Name", userName);

				System.out.println(userName);

				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				response.setContentType("text/plain;charset=UTF-8"); //전달하는 데이터 타입
				PrintWriter output = response.getWriter();
				output.print(userName);
			}else{//조회결과가 없다면 로그인 실패
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
