package date;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/checkdate")
public class checkdate extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                //기준시간의 범위 구하기
                LocalDateTime startDateTime1 = LocalDateTime.of(2022, 04, 21, 9, 00, 00);
                LocalDateTime endDateTime1 = LocalDateTime.of(2022, 06, 30, 18, 00, 00);
                LocalDateTime startDateTime2 = LocalDateTime.of(2023, 04, 25, 9, 00, 00);
                LocalDateTime endDateTime2 = LocalDateTime.of(2023, 04, 25, 15, 00, 00);

                //전송할 데이터 구성하기
                String[] patternArr1 = { "(yyyy.MM.dd HH:mm:ss)" };

                String date = "";
                for(String pattern : patternArr1) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                        String data = dtf.format(startDateTime1) + "~" + dtf.format(endDateTime1) + "," +  dtf.format(startDateTime2) + "~" + dtf.format(endDateTime2);
                        date = data + ",";
                }

                //현재시간 구하기
                LocalDateTime currenDateTime = LocalDateTime.now();
                //전송할 데이터 구성하기
                String[] patternArr2 = { "(yyyy.MM.dd HH:mm:ss)"};

                for(String pattern : patternArr2) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                        String nowdate =  dtf.format(currenDateTime);
                        date = date + nowdate + ",";
                }

                //수강신청 가능한 기간인지 판단하기
                //너무 길어서 변수로 설정함
                boolean q1 = startDateTime1.isBefore(currenDateTime) && currenDateTime.isBefore(endDateTime1);
                boolean q2 = startDateTime2.isBefore(currenDateTime) && currenDateTime.isBefore(endDateTime2);

                System.out.println(q1);
                System.out.println(q2);
                response.setContentType("text/plain;charset=UTF-8"); //전달하는 데이터 타입
                PrintWriter output = response.getWriter();
                //수강신청 기간이면
                if(q1==true || q2==true){

                        date = date + "pass:" + "pass";
                        output.print(date);
                        output.close();
                //수강신청 기간이 아니면
                }else{
                        date = date + "pass:" + "nonepass";
                        output.print(date);
                        output.close();
                }

        }


}
