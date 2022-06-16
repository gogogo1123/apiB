package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import damain.ResVo;
import service.ResDao;


@WebServlet("/rescontroller")
public class ResController extends HttpServlet {
	
       

  
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
	
		String pj = request.getContextPath();
		System.out.println(pj);
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
		
		if(command.equals("/res/rescontent.do")) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/res/rescontent.jsp");
			rd.forward(request, response);
		
		}else if(command.equals("/res/rescontentAction.do")) {
			
			
			System.out.println("예약 페이지 입성");
			
			String Name = request.getParameter("Name");
			
			System.out.println("Name =" +Name);
			
			String respeople = request.getParameter("respeople");
			
			int Respeople = Integer.parseInt(respeople);
			
			System.out.println("Respeople =" + Respeople);
			
			
			//금액
			
			String rvprice1 = request.getParameter("sell_price");
			int rvprice = Integer.parseInt(rvprice1);
			
			
			String rvpricesum1 = request.getParameter("sum");
			
			int rvpricesum = Integer.parseInt(rvpricesum1);
			
			
			//예약일
			
			String dataA = request.getParameter("dataA");			
			
			
			System.out.println("dataA ="+ dataA);
			
			String data = request.getParameter("data");
			
			
			System.out.println("data =" +data);
			
			HttpSession session = request.getSession();
			
			int midx = (int) session.getAttribute("midx");
			
			System.out.println("midx ="+midx);
			
			String membername = (String)session.getAttribute("membername");
			
			System.out.println("membername ="+membername);
			
			
			
			
			ResDao rd  = new ResDao();
			
			int value = rd.insertRes(Name, Respeople,dataA, data, midx, membername,rvprice,rvpricesum);
			
			System.out.println("value =" +value);
			
		
			
			
			if(value==1) {
				session.setAttribute("Name", Name);
				session.setAttribute("sum", rvpricesum);
				response.sendRedirect(request.getContextPath()+"/");
				
				
				//response.sendRedirect(request.getContextPath()+"/res/pay.do"); 결제창 로직.
			}else {
				response.sendRedirect(request.getContextPath()+"/res/rescontent.do");
			}
			
			
			
			
			

			
			
		}else if(command.equals("/res/resList.do")) {
			
			
			
			ResDao rd = new ResDao();
			
			ArrayList<ResVo> alist = rd.ResselectAll();
			
			
			request.setAttribute("alist", alist);
			
			
			System.out.println("alist = "+alist);
			
			
			
			
			RequestDispatcher rd1 = request.getRequestDispatcher("/res/resList.jsp");
			rd1.forward(request, response);
			
		}
		else if(command.equals("/res/rescontent1.do")) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/res/rescontent1.jsp");
			rd.forward(request, response);
		}else if(command.equals("/res/rescontent2.do")) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/res/rescontent2.jsp");
			rd.forward(request, response);
		}else if(command.equals("/res/rescontent3.do")) {
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/res/rescontent3.jsp");
			rd.forward(request, response);
			
		}else if(command.equals("/res/pay.do")) {
			
			System.out.println("들어옴");
			
			RequestDispatcher rd = request.getRequestDispatcher("/res/pay.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
