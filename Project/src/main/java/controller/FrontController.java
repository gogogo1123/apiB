package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "/FrontController" , urlPatterns = "*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
		
		
	
		
		// 넘어온 url 값 뽑기
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		// 넘어온 project 뽑기
		String pj = request.getContextPath();
		System.out.println(pj);
		
		// url 을 project 만큼 잘라내기
		String command = uri.substring(pj.length()); 
		System.out.println(command);
		//ex) /member/memberList.do
		
		//  /memberList.do 짤라내기
		String[] subpath = command.split("/");
		String location = subpath[1];  
		System.out.println(location);
		
		if(location.equals("member")) {
		   MemberController mc = new MemberController();	
			mc.process(request, response);
			
		}else if(location.equals("board")) {
			BoardController bc = new BoardController();
			bc.process(request,response);
			
		}else if(location.equals("item")) {
			itemController ic = new itemController();
			ic.process(request,response);
			
		}else if(location.equals("res")) {
			ResController rs = new ResController();
			rs.process(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
