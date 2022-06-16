package enrollClasses;

import db.DBtool;
import subject_info.SubInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/enroll")
public class enroll extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//파라미터 가져와서 변수에 저장
		request.setCharacterEncoding("UTF-8");
		Integer sub_code = null;
		sub_code = Integer.parseInt(request.getParameter("sub_code"));
		Integer stu_code = null;
		stu_code = Integer.parseInt(request.getParameter("stu_code"));

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			//jdbc 드라이버 로딩 + dbms 접속
			conn = DBtool.getConnection();

			//<<입력한 과목코드가 존재하는 지 확인>>
			//1. 쿼리 구성 후 실행
			String sql = "SELECT * FROM enroll_web.subject_info WHERE sub_code =?";
			pstmt = DBtool.getPstmt(conn, sql);
			pstmt.setString(1, "sub_code");
			rs = pstmt.executeQuery();

			//2. 필요한 값 가져오기
			int stu_num = rs.getInt("stu_num");
			int major_stu_num = rs.getInt("major_stu_num");
			int minor_stu_num = rs.getInt("minor_stu_num");
			int general_stu_num = rs.getInt("general_stu_num");
			int grade = rs.getInt("grade");

			//3. 쿼리가 성공적으로 실행된 경우
			if(rs.next()){
				//<<해당 학생의 수강신청 가능 학점, 수강신청된 학점 정보 가져오기>>
				//1. 쿼리문 구성 후 실행하기
				sql = "SELECT * FROM enroll_web.student_enroll WHERE stu_code =?";
				pstmt = DBtool.getPstmt(conn, sql);
				pstmt.setString(1, "stu_code");
				rs = pstmt.executeQuery();

				//2. 쿼리가 성공적으로 실행된 경우
				if(rs.next()){
					//3. 필요한 값 가져와서 저장하기
					int total_grd = rs.getInt("total_grd");
					int enrolled_grd = rs.getInt("enrolled_grd");
					//입력한 과목이 교양과목이라면
					if(sub_code%10000==1){
						if(grade+enrolled_grd<=total_grd){
							Random random = new Random();
							int user_num = random.nextInt(stu_num%2*3)+1;
							if(stu_num>=user_num){ //수강가능학생수>=user의 등수
								//학생별 수강신청 테이블의 과목이 최초로 null이 아닌 곳에 해당 과목코드 추가


								sql = "SELECT * FROM enroll_web.student_enroll WHERE stu_code =?";
								pstmt = DBtool.getPstmt(conn, sql);
								pstmt.setString(1, "stu_code");
								rs = pstmt.executeQuery();

								for(int i=0; i<=15; i++){

									if(rs.getString("i").equals("null")){
										String column = rs.getString("i");
										sql = "UPDATE enroll_web.student_enroll SET ? = ? WHERE stu_code=?";
										pstmt = DBtool.getPstmt(conn, sql);
										pstmt.setString(1, "column");
										pstmt.setString(2, "sub_code");
										pstmt.setString(3, "stu_code");
										rs = pstmt.executeQuery();

										break;
									}
								}
							}else{
								//수강인원 초과
							}
						}else{
							//수강신청가능 학점 초과
						}

					}else{
						if((sub_code-(sub_code%10000*10000))%1000==(stu_code-(stu_code%10000*10000))%1000){
							//복수, 부전공
						}else{
							//일반선택
						}
					}

				}else{
					//
				}
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
