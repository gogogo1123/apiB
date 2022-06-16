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

import damain.ItemVo;
import service.ItemDao;


@WebServlet("/itemController")
public class itemController extends HttpServlet {
	
       



	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);

		String pj = request.getContextPath();
		System.out.println(pj);
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
		
		
		
		if(command.equals("/item/itemA.do")) {
			
			System.out.println("왓음");
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemA.jsp");
			
			rd.forward(request, response);
			
			
			
		}else if(command.equals("/item/itemB.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemB.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemC.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemC.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemD.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemD.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemE.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemE.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemF.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemF.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemG.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemG.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/itemH.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemH.jsp");
			
			rd.forward(request, response);
			
			
		
		}else if(command.equals("/item/itemAction.do")) {
			
		  
			
			String itemName = request.getParameter("itemName");
			
			System.out.println("itemName ="+itemName);
			
			String itemquantity = request.getParameter("itemQuantity");
			
			System.out.println("itemquantity =" +itemquantity);
			
			int itemQuantity = Integer.parseInt(itemquantity);
			
			String itemprice1 = request.getParameter("sell_price");
			
			int itemPrice = Integer.parseInt(itemprice1);
			
			
			String itempricesum1 = request.getParameter("sum");
			
			int itemPriceSum = Integer.parseInt(itempricesum1);
			
			
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			String membername = (String)session.getAttribute("membername");
		
			
			
			ItemDao id = new ItemDao();
			
			int value = id.insertItem(itemName, itemQuantity, midx,membername,itemPrice,itemPriceSum);
			
			
			if(value==1) {
				
				session.setAttribute("Name", itemName);
				session.setAttribute("sum", itemPrice);
				
				
				
				
				response.sendRedirect(request.getContextPath()+"/");
				
				
				//response.sendRedirect(request.getContextPath()+"/item/pay.do"); 결제창으로 가는 로직
				
			}else {
				response.sendRedirect(request.getContextPath()+"/item/itemA.do");
			}
			
			
			
		}else if(command.equals("/item/itemList.do")) {
			
			
			
			ItemDao iv = new ItemDao();
			
			
			
			
			ArrayList<ItemVo> alist = iv.itemselectAll();
			
			
			
			System.out.println("alist = " +alist);
			
			request.setAttribute("alist", alist);
			
			
			
			
		
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/itemList.jsp");
			
			rd.forward(request, response);
		
		}else if(command.equals("/item/pay.do")) {
			
			System.out.println("들어옴");
			
			RequestDispatcher rd = request.getRequestDispatcher("/item/pay.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
