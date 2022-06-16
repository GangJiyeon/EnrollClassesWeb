package student_info;

import db.DBtool;
import org.json.JSONArray;
import org.json.JSONObject;
import subject_info.SubInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "searchStu", value = "/search/stu")
public class searchStu extends HttpServlet {
    public static List<StuInfo> stuInfoList = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //필요한 것 끄내기
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("Name");


        try{
            //jdbc 드라이버 로딩 + dbms 접속
            conn = DBtool.getConnection();
            String sql = "";

            sql = "SELECT * FROM enroll_web.student_info WHERE stu_name=?";
            pstmt = DBtool.getPstmt(conn, sql);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();


            while (rs.next()){
                int stu_code = rs.getInt(1);
                String stu_name = rs.getString(2);
                String id = rs.getString(3);
                String pw = rs.getString(4);
                Date birthday = rs.getDate(5);
                String major = rs.getString(6);
                String minor = rs.getString(7);
                String mobile = rs.getString(8);
                String email = rs.getString(9);
                String addr = rs.getString(10);
                double avg_grade = rs.getFloat(11);
                Date enterdate = rs.getDate(12);
                String semester = rs.getString(13);

                StuInfo stuInfo = new StuInfo(stu_code, stu_name, id, pw, birthday, major, minor, mobile, email, addr, avg_grade, enterdate, semester);

                stuInfoList.add(stuInfo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBtool.closeResultSet(rs);
            DBtool.closePstmt(pstmt);
            DBtool.closeConn(conn);
        }


        if(stuInfoList.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }else{
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter output = response.getWriter();
            JSONArray jsonArray = new JSONArray();
            for(StuInfo stuInfo : stuInfoList){
                JSONObject subjectInfo = new JSONObject();

                subjectInfo.put("stu_code", stuInfo.getStu_code());
                subjectInfo.put("stu_name", stuInfo.getStu_name());
                subjectInfo.put("id", stuInfo.getId());
                subjectInfo.put("pw", stuInfo.getPw());
                subjectInfo.put("birthday", stuInfo.getBirthday());
                subjectInfo.put("major", stuInfo.getMajor());
                subjectInfo.put("minor", stuInfo.getMinor());
                subjectInfo.put("mobile", stuInfo.getMobile());
                subjectInfo.put("email", stuInfo.getEmail());
                subjectInfo.put("addr", stuInfo.getAddr());
                subjectInfo.put("avg_grade", stuInfo.getAvg_grade());
                subjectInfo.put("enterdate", stuInfo.getEnterdate());
                subjectInfo.put("semester", stuInfo.getSemester());
            }

            JSONObject json = new JSONObject();

            json.put("list", stuInfoList);
            output.print(json);

            output.close();



        }
    }
}
