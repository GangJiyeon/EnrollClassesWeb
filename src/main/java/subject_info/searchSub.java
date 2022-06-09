package subject_info;

import db.DBtool;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search/sub")
public class searchSub extends HttpServlet {

	public static List<SubInfo> subInfoList = new ArrayList<>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		String semester = null;
		String major = null;
		String subjectType = null;
		String searchType = null;
		String searchSub = null;
		semester = request.getParameter("semester");
		major = request.getParameter("major");
		subjectType = request.getParameter("subjectType");
		searchType = request.getParameter("searchType");
		searchSub = request.getParameter("searchSub");

		//필요한 것 꺼내기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();

			String sql = "";
			//선택
			if(semester!=null&&major!=null&&subjectType!=null) {

				//학과별로 부여한 과목코드 패턴으로 변환
				if(major.equals("심리학과")){
					major = "2";
				}else if(major.equals("컴퓨터공학과")){
					major = "3";
				}else if (major.equals("통계학과")) {
					major = "4";
				} else if (major.equals("경영학과")) {
					major = "5";
				}

				//쿼리문 작성하기
				if(subjectType.equals("교양선택")||subjectType.equals("교양필수")){
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_type=?";
					pstmt = DBtool.getPstmt(conn, sql);
					pstmt.setString(1, subjectType);
					rs = pstmt.executeQuery();
				}else if(subjectType.equals("전공선택")||major.equals("심리학과")) {
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_type=? AND sub_code LIKE ?";
					pstmt = DBtool.getPstmt(conn, sql);
					pstmt.setString(1, subjectType);
					pstmt.setString(2, major);
					rs = pstmt.executeQuery();
				}

			//직접 입력 받은 경우
			}else if(searchType!=null&&searchSub!=null) {
				if(searchType.equals(""))
				sql = "SELECT * FROM enroll_web.subject_info WHERE ?=?";
				pstmt = DBtool.getPstmt(conn, sql);
				pstmt.setString(1, searchType);
				pstmt.setString(2, searchSub);
				rs = pstmt.executeQuery();
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

			while (rs.next()){
				Integer sub_code = rs.getInt(1);
				String sub_name = rs.getString(2);
				String sub_type = rs.getString(3);
				String pro_name = rs.getString(4);
				Integer grade = rs.getInt(5);
				Integer stu_num = rs.getInt(6);
				Integer major_stu_num = rs.getInt(7);
				Integer minor_stu_num = rs.getInt(8);
				Integer general_stu_num = rs.getInt(9);
				String place = rs.getString(10);
				String time = rs.getString(11);
				Integer rating = rs.getInt(12);
				SubInfo subInfo = new SubInfo(sub_code, sub_name, sub_type, pro_name, grade, stu_num, major_stu_num, minor_stu_num, general_stu_num, place, time, rating);
				subInfoList.add(subInfo);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DBtool.closeResultSet(rs);
			DBtool.closePstmt(pstmt);
			DBtool.closeConn(conn);
		}


		if(subInfoList.isEmpty()){
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}else{
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter output = response.getWriter();
			JSONArray jsonArray = new JSONArray();
			for(SubInfo subInfo : subInfoList){
				JSONObject subjectInfo = new JSONObject();
				subjectInfo.put("sub_code", subInfo.getSub_code());
				subjectInfo.put("sub_name", subInfo.getSub_name());
				subjectInfo.put("sub_type", subInfo.getSub_type());
				subjectInfo.put("pro_name", subInfo.getPro_name());
				subjectInfo.put("grade", subInfo.getGrade());
				subjectInfo.put("stu_num", subInfo.getStu_num());
				subjectInfo.put("major_stu_num", subInfo.getMajor_stu_num());
				subjectInfo.put("general_stu_num", subInfo.getGeneral_stu_num());
				subjectInfo.put("place", subInfo.getPlace());
				subjectInfo.put("time", subInfo.getTime());
				subjectInfo.put("rating", subInfo.getRating());
			}

			JSONObject json = new JSONObject();

			json.put("list", subInfoList);
			output.print(json);
			RequestDispatcher rd = request.getRequestDispatcher("/html/subject_info.html);

			output.close();



		}
	}


	
	
}
