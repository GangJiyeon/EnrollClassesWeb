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

		subInfoList.clear();
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

		System.out.println(semester);
		System.out.println(major);
		System.out.println(subjectType);
		System.out.println(searchType);
		System.out.println(searchSub);

		//필요한 것 꺼내기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();

			String sql = "";
			//직접입력이 아닌 경우(select 선택)
			if(semester!=null&&major!=null&&subjectType!=null) {
				//과목타입이 교양선택/교양필수인 경우(전공의 영향을 받지 않으므로 전공은 조건에서 제외)
				if(subjectType.equals("교양선택")||subjectType.equals("교양필수")){
					//쿼리문 작성하기
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_type=?";
					pstmt = DBtool.getPstmt(conn, sql);
					pstmt.setString(1, subjectType);
					rs = pstmt.executeQuery();
				}else if(subjectType.equals("전공선택")||(subjectType.equals("전공기초")||(subjectType.equals("전공필수")))) {
					//학과별로 부여한 과목코드 패턴으로 변환
					if(major.equals("심리학과")){
						major = "2%";
					}else if(major.equals("컴퓨터공학과")){
						major = "3%";
					}else if (major.equals("통계학과")) {
						major = "4%";
					} else if (major.equals("경영학과")) {
						major = "5%";
					}//쿼리문 작성
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_type=? AND sub_code LIKE ?";
					pstmt = DBtool.getPstmt(conn, sql);
					pstmt.setString(1, subjectType);
					pstmt.setString(2, major);
					rs = pstmt.executeQuery();
				}

			//직접 입력 받은 경우
			}else if(searchType!=null&&searchSub!=null) {
				if(searchType.equals("과목명")){
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_name=?";
				} else if (searchType.equals("교수명")) {
					sql = "SELECT * FROM enroll_web.subject_info WHERE pro_name=?";
				}else if(searchType.equals("과목코드")){
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_code=?";
				}else if(searchType.equals("학과/학부")){//학과/학부
					//학과별로 부여한 과목코드 패턴으로 변환
					if(searchSub.equals("심리학과")){
						searchSub = "2%";
					}else if(searchSub.equals("컴퓨터공학과")){
						searchSub = "3%";
					}else if (searchSub.equals("통계학과")) {
						searchSub = "4%";
					} else if (searchSub.equals("경영학과")) {
						searchSub = "5%";
					}
					sql = "SELECT * FROM enroll_web.subject_info WHERE sub_code LIKE ?";
				}
				pstmt = DBtool.getPstmt(conn, sql);
				pstmt.setString(1, searchSub);
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
				subjectInfo.put("minor_stu_num", subInfo.getMinor_stu_num());
				subjectInfo.put("general_stu_num", subInfo.getGeneral_stu_num());
				subjectInfo.put("place", subInfo.getPlace());
				subjectInfo.put("time", subInfo.getTime());
				subjectInfo.put("rating", subInfo.getRating());
			}

			JSONObject json = new JSONObject();

			json.put("list", subInfoList);
			output.print(json);

			output.close();



		}
	}


	
	
}
