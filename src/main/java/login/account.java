package login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns= {"/account"},loadOnStartup = 1)
public class account extends HttpServlet {

	public static List<MemberInfo> MemberInfoList = new ArrayList<>();
	
	@Override
	public void init() throws ServletException {
		
		//해당 사이트는 회원가입 기능이 없으므로 로그인 정보를 미리 저장해두었음 
		MemberInfo newMemberInfo1 = new MemberInfo("user1", "user1", "김철수");
		MemberInfo newMemberInfo2 = new MemberInfo("user2", "user2", "김소이");
		MemberInfo newMemberInfo3 = new MemberInfo("user3", "user3", "강지연");
		
		MemberInfoList.add(newMemberInfo1);
		MemberInfoList.add(newMemberInfo2);
		MemberInfoList.add(newMemberInfo3);
	}
	

		
	
       
    

}
