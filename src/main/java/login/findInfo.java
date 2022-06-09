package login;

import db.DBtool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/find/info")
public class findInfo extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//파라미터 꺼내기
		String findName = null; //아이디 찾기에 쓰이는 변수
		String findId = null; //비밀번호 찾기에 쓰이는 변수
		findName = request.getParameter("name");
		findId = request.getParameter("id");

		String savedID;
		String savedPW;

		//db 관련 필요한 것들 꺼내기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();
			//쿼리문 작성

			//id 찾기
			if(findName!=null&&findId==null) {
				String sql = "SELECT * FROM enroll_web.student_info WHERE stu_name=?";
				pstmt = DBtool.getPstmt(conn, sql);
				pstmt.setString(1, findName);
				rs = pstmt.executeQuery();
				if(rs.next()){
					savedID = rs.getString("id");
					response.setContentType("text/plain;charset=UTF-8");
					PrintWriter output = response.getWriter();
					output.print(savedID);
					output.close();
				}

			//비밀번호 찾기
			}else if(findName==null&&findId!=null) {
				String sql = "SELECT * FROM enroll_web.student_info WHERE id=?";
				pstmt = DBtool.getPstmt(conn, sql);
				pstmt.setString(1, findId);
				rs = pstmt.executeQuery();
				if(rs.next()){
					savedPW = rs.getString("pw");
					response.setContentType("text/plain;charset=UTF-8");
					PrintWriter output = response.getWriter();
					output.print(savedPW);
					output.close();
				}
			}else{
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBtool.closeResultSet(rs);
			DBtool.closePstmt(pstmt);
			DBtool.closeConn(conn);
		}

				
	}	

}
