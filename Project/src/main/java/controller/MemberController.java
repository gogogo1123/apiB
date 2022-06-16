package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import damain.MemberVo;
import service.MemberDao;
import service.SMS;

@WebServlet("/MemberController")
public class MemberController {
	
	
protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
	
		String pj = request.getContextPath();
		
		String command = uri.substring(pj.length());
		
		System.out.println("command:"+command);
	
		
		if(command.equals("/member/memberJoin.do")) {
			
			
			
			
			
			
			
			
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
		rd.forward(request, response);
		
}else if(command.equals("/member/memberLogin.do")) {
	
	
	

	
	
  RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
     rd.forward(request, response);
  
     
     
     
     
}else if(command.equals("/member/memberJoinAction.do")) {
	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=utf-8");
	
	String memberId = request.getParameter("memberId");
	System.out.println("memberId"+memberId);
	String memberPwd = request.getParameter("memberPwd");
	System.out.println("memberPwd"+memberPwd);
	String memberName = request.getParameter("memberName");
	System.out.println("memberName"+memberName);
	String memberEmail = request.getParameter("memberEmail");
	System.out.println("memberEmail"+memberEmail);
	String memberGender = request.getParameter("memberGender");
	System.out.println("memberGender"+memberGender);
	

	MemberDao md = new MemberDao();
	int value = md.insertmember(memberId, memberPwd, memberName, memberGender, memberEmail);
	
	
	
	
	if(value==1) {
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}else{
		response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
		
	}
		
	}else if (command.equals("/member/memberList.do")) {
		
		// 리스트를 보여줄 배열인 Array 배열 을 생성해주고
		// 값을 저장함
		
		MemberDao md = new MemberDao();	
		
		// 배열인 ArrayList 를 임시 저장하기위해서 배열인 alist 안에 memberListAll 실행값 적용
		
		
		ArrayList<MemberVo> alist = md.memberListAll();
		
		
		// 저장한 memberListAll 값을 alist 로 임시 저장을 해줌
		
		
		request.setAttribute("alist", alist);
		
		// 저장이 완료되면 저장한 값인 alist 를 가지고 memberList 페이지로 이동 하기.
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
		rd.forward(request, response);			
	
	}else if (command.equals("/member/memberLoginAction.do")) {
		
		// memberLogin 페이지에서 확인버튼을 누르면 memberid 와 memberpwd 를 받아와서
		// 파라미터로 가져옴.
		
		System.out.println("연결됨");
		
		

		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		// 그리고 로그인 로직을 실행하기 위해 memberDao 로직을 객체 생성하구
		
		
		MemberDao md = new MemberDao();
		
		// 로직을 적용하여 멤버 데이터 객체인 MemberVo 에다가 값을 넣어줌.

		MemberVo mv = md.Login(memberId, memberPwd);
		
		
		
		
		// 로그인 여부에 따라서 볼수 있는 페이지와 볼수 없는 페이지를 구분하기위해서
		// LoginActio 페이지에서 로그인이 되면 session 값을 가지고 
		// 볼수 있는 유무룰 파악하기 위해서 세선을 지정해줌 ..  세션을 이클립스에서 가져옴.
		
		HttpSession session = request.getSession();
		
		// mv = 값은 위에서 mv.Login 연결 시킨 객체가 값이 나오게 되면 mv!=null 값을실행.

		
		if(mv != null) {
			session.setAttribute("midx",mv.getmidx());
			session.setAttribute("memberId", mv.getMemberid());
			session.setAttribute("memberPwd", mv.getMemberPwd());			
			session.setAttribute("membername", mv.getMemberName());
		
			response.sendRedirect(request.getContextPath()+"/");
			
			// memberid 가 값이 없을때는 다시 로그인 페이지로 돌아갑니다..
		}else{
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
		}
		
		
		
		// 아이디값 찾는 결과값 가져오기.
		
		
		
  }else if(command.equals("/member/memberfindByIdAction.do")) {  // 아이디 찾기 버튼 누르고 값을 입력하면 실행되는 로직
		
	  
	    // 이름 , 이메일 값 불러오기
		String memberName = request.getParameter("memberName");
		System.out.println("memberName"+memberName);
		String memberEmail = request.getParameter("memberEmail");
		System.out.println("memberEmail"+memberEmail);
		
		// SQL 실행하는 로직 부분 가져오기
		MemberDao md = new MemberDao();	
		
		// 새로운 데이터 객체 생성하고 이름이랑 이메일을 입력하게 되면 실행되는 로직 
		MemberVo mv = md.findByid(memberName, memberEmail);
		System.out.println("mv"+mv);
	//	
//		request.setAttribute("mv", mv);     // << 리다이렉트로 보내주기때문에 사용하지 못함 forward 방식이면 사용가능함. ㅎ
	//	
		HttpSession session=request.getSession();  // 리다이렉트 하기때문에 session 값에 데이터 값 담아서 보내줄거임
		session.setAttribute("mv",mv);
		
		// int midx = (int) session.getAttribute("midx");
		
		
		
		
		if(mv != null) { // mv 값이 데이터가 제대로 들어잇으면 실행
		   response.sendRedirect(request.getContextPath()+"/member/memberFindByidResult.jsp");	
		}else {  // mv 값이 아무것도 안들어잇으면 실행
			response.sendRedirect(request.getContextPath()+"/member/memberFindByid.jsp");	
		}
		
		
		//아이디 찾기
	}else if(command.equals("/member/memberFindByid.do")) {
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberFindByid.jsp");
		rd.forward(request, response);

		
		// 비밀번호 찾기
	}else if(command.equals("/member/memberFindBypwd.do")) {
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberFindBypwd.jsp");
		rd.forward(request, response);
		
		
		
		//비밀번호 찾는 결과값을 가져오기
	}else if(command.equals("/member/memberfindByPwdAction.do")) {
		
		
		String memberId = request.getParameter("memberid");
		System.out.println("memberid"+memberId);
		String memberName = request.getParameter("memberName");
		System.out.println("memberNmae"+memberName);
		
		
		
		// 로직 실행할 객체 새로 생성해줌
		MemberDao md = new MemberDao();
		
		//저장할 객체인 VO 에다가 아이디,이름 넣으면 로직 실행되게 만들기
		MemberVo mv = md.findByPwd(memberId, memberName);
		
		
		// 리다이렉트로 보내는 경우니깐 세션값에다가 값을 저장 request 는 서블릿 내에서만 저장하는거라서
		HttpSession session = request.getSession();
		session.setAttribute("mv", mv);
		
		
		if(mv != null) {
			   response.sendRedirect(request.getContextPath()+"/member/memberFindByPwdResult.jsp");	
			}else {
				response.sendRedirect(request.getContextPath()+"/member/memberFindBypwd.jsp");	
			}
		
		

	}else if(command.equals("/member/memberLogout.do")) {

	 HttpSession session = request.getSession();
	 
	 
	 //로그아웃 하는 기능 세션값을 지워버려서 아무권한이 없게 다시 만들어냄
	 session.invalidate(); 
	 
	 //그리고 index 페이지로 세션값을 지우고 다시 돌려보내기.
	 response.sendRedirect(request.getContextPath()+"/");
		
	}else if (command.equals("/member/idcheck.do")) {
		
		String memberId = request.getParameter("userId");
		
		System.out.println("memberId="+memberId);
		
		MemberDao md = new MemberDao();
		
		int idCheck = md.checkId(memberId);
		
		System.out.println("idCheck =" + idCheck);
		
		if(idCheck==0) {
			System.out.println("이미 존재하는 아이디입니다");
		}else if(idCheck==1) {
			System.out.println("사용 가능한 아이디입니다.");
		}
		
	
		
		//PrintWriter out  = response.getWriter();
	    //out.write(idCheck+"" ); // ajax 결과값이 result 가됨
		                          // string 값을 내보낼수 잇도록 + "" < 넣어줌
		
		
		response.getWriter().write(idCheck+"");
	}

		
else if(command.equals("/member/membersns.do")) {
	
	SMS sms = new SMS();
	
	
	String phone = request.getParameter("phone");
	
	
	System.out.println("phone ="+phone);
	
    Random rand  = new Random();
    String numStr = "";
    for(int i=0; i<4; i++) {
        String ran = Integer.toString(rand.nextInt(10));
        numStr+=ran;
        
        
       
        
        
    }

 
  
    
    System.out.println("수신자 번호 : " + phone);
    System.out.println("인증번호 : " + numStr);
  String re = sms.certifiedPhoneNumber(phone,numStr);
 
    System.out.println("re =" +re);
    System.out.println("인증번호 : " + numStr);
    
    
    
    
    response.getWriter().write("re");

    
}
		
		
}



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
}


}