package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
<<<<<<< HEAD
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ï¿½ï¿½ï¿½ï¿½ï¿½Î·ï¿½ ï¿½ï¿½ requestï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½  Ã³ï¿½ï¿½
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		String uploadPath="D:\\openApi(B)\\dev\\jspstudy\\src\\main\\webapp\\";
		String saveFolder="images";
		String saveFullPath = uploadPath+saveFolder;
		
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("ï¿½Û¾ï¿½ï¿½ï¿½ È­ï¿½é¿¡ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("ï¿½Û¾ï¿½ï¿½ï¿½ Ã³ï¿½ï¿½ È­ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
			
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
						
			String subject = multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer = multipartRequest.getParameter("writer");
			//System.out.println(subject+";"+content+";"+writer);
			
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ï¿½ï¿½
			Enumeration files =  multipartRequest.getFileNames();
			//ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½ï¿½Â´ï¿½
			String file = (String)files.nextElement();
			//ï¿½Ñ¾ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ß¿ï¿½ ï¿½Ø´ï¿½Ç´ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ì¸ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ç¾ï¿½ï¿½Ö´ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ñ´ï¿½(ï¿½ï¿½ï¿½ï¿½Ç´ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ì¸ï¿½)
			String fileName = multipartRequest.getFilesystemName(file);
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ì¸ï¿½
			String originFileName= multipartRequest.getOriginalFileName(file);
			
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			//int midx = 2;
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx,fileName);
			
			if (value==1) {
				response.sendRedirect(request.getContextPath()+"/index.jsp");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}			
			
		}else if (command.equals("/board/boardList.do")) {
			System.out.println("ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Æ® È­ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
			
			String page = request.getParameter("page");
			if (page == null) page = "1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
				
			String searchType = request.getParameter("searchType");
			if (searchType == null) searchType="subejct";
					
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
						
			//Ã³ï¿½ï¿½
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);			
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist  = bd.boardSelectAll(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			//ï¿½Ìµï¿½
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent.do")) {
			//1.ï¿½Ñ¾ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Þ´Â´ï¿½
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2.Ã³ï¿½ï¿½ï¿½Ñ´ï¿½
			BoardDao bd =new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);		 //ï¿½ï¿½ï¿½Î¿ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ä¡ï¿½ï¿½ï¿½ï¿½ ï¿½Ú¿ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ñ´ï¿½
			
			//3.ï¿½Ìµï¿½ï¿½Ñ´ï¿½
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardModify.do")) {
			
			System.out.println("ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
			//1.ï¿½Ä¶ï¿½ï¿½ï¿½Í°ï¿½ ï¿½Ñ¾ï¿½ï¿½
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2. Ã³ï¿½ï¿½ï¿½ï¿½
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			
			request.setAttribute("bv", bv);		//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ú¿ï¿½ï¿½ï¿½ï¿½ï¿½	
			
			//3.ï¿½Ìµï¿½ï¿½ï¿½			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");			
			rd.forward(request, response);	
		}else if (command.equals("/board/boardModifyAction.do")) {
			
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();
			int value = bd.updateBoard(subject, content, writer, ip, midx,bidx_);
			System.out.println(value);
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);				
			}	
			
			
		}else if (command.equals("/board/boardDelete.do")) {
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			request.setAttribute("bidx", bidx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");					
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardDeleteAction.do")) {
			
		
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
						
			BoardDao bd = new BoardDao();
			int value = bd.deleteBoard(bidx_);
	
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
		}else if (command.equals("/board/boardReply.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			
			request.setAttribute("bv", bv);			
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");					
			rd.forward(request, response);
						
		} else if (command.equals("/board/boardReplyAction.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
			
		} else if (command.equals("/board/fileDownload.do")) {
			
			String filename= request.getParameter("filename");
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ï¿½
			String filePath = saveFullPath+File.separator+filename;
			Path source = Paths.get(filePath);
			
			String mimeType = Files.probeContentType(source);
			
			if(mimeType == null) { 
				mimeType = "application/octet-stream"; 
			} 			
			
			//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Â´ï¿½
			response.setContentType(mimeType);
			
			String encodingFileName = new String(filename.getBytes("UTF-8"));
			//Ã·ï¿½ï¿½ï¿½Ø¼ï¿½ ï¿½Ù¿ï¿½Îµï¿½Ç´ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Â´ï¿½
			response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
			
			//ï¿½Ø´ï¿½ï¿½ï¿½Ä¡ï¿½ï¿½ ï¿½Ö´ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ð¾ï¿½å¸°ï¿½ï¿½.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//ï¿½ï¿½ï¿½Ï¾ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Æ®ï¿½ï¿½
			ServletOutputStream servletOutStream = response.getOutputStream();
			
			byte[] b = new byte[4096];
			
			int read = 0;
			
			while((read =fileInputStream.read(b, 0, b.length))!=-1) {
				//ï¿½ï¿½ï¿½Ï¾ï¿½ï¿½ï¿½
				servletOutStream.write(b, 0, read);
				
			}
			//ï¿½ï¿½ï¿½
=======
import jspstudy.domain.Criteria;
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//°¡»ó°æ·Î·Î ¿Â request°¡ ÀÖÀ¸¸é  Ã³¸®
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		String uploadPath="D:\\openApi(B)\\dev\\jspstudy\\src\\main\\webapp\\";
		String saveFolder="images";
		String saveFullPath = uploadPath+saveFolder;
		
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("±Û¾²±â È­¸é¿¡ µé¾î¿ÔÀ½");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("±Û¾²±â Ã³¸® È­¸éÀ¸·Î µé¾î¿ÔÀ½");
			
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
						
			String subject = multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer = multipartRequest.getParameter("writer");
			//System.out.println(subject+";"+content+";"+writer);
			
			//¿­°ÅÀÚÀÎ ÀúÀåµÉ ÆÄÀÏÀ» ´ã´Â °´Ã¼»ý¼º
			Enumeration files =  multipartRequest.getFileNames();
			//´ã±ä °´Ã¼ÀÇ ÆÄÀÏ ÀÌ¸§À» ¾ò´Â´Ù
			String file = (String)files.nextElement();
			//³Ñ¾î¿À´Â °´Ã¼Áß¿¡ ÇØ´çµÇ´Â ÆÄÀÏÀÌ¸§À¸·Î µÇ¾îÀÖ´Â ÆÄÀÏÀÌ¸§À» ÃßÃâÇÑ´Ù(ÀúÀåµÇ´Â ÆÄÀÏÀÌ¸§)
			String fileName = multipartRequest.getFilesystemName(file);
			//¿øº»ÀÇ ÆÄÀÏÀÌ¸§
			String originFileName= multipartRequest.getOriginalFileName(file);
			
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			//int midx = 2;
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx,fileName);
			
			if (value==1) {
				response.sendRedirect(request.getContextPath()+"/index.jsp");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}			
			
		}else if (command.equals("/board/boardList.do")) {
			System.out.println("°Ô½ÃÆÇ ¸®½ºÆ® È­¸é µé¾î¿ÔÀ½");
			
			String page = request.getParameter("page");
			if (page == null) page = "1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword = "";
				
			String searchType = request.getParameter("searchType");
			if (searchType == null) searchType="subejct";
					
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
						
			//Ã³¸®
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);			
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist  = bd.boardSelectAll(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			//ÀÌµ¿
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent.do")) {
			//1.³Ñ¾î¿Â °ªÀ» ¹Þ´Â´Ù
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2.Ã³¸®ÇÑ´Ù
			BoardDao bd =new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);		 //³»ºÎ¿¡ °°Àº À§Ä¡¿¡¼­ ÀÚ¿ø °øÀ¯ÇÑ´Ù
			
			//3.ÀÌµ¿ÇÑ´Ù
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardModify.do")) {
			
			System.out.println("¼öÁ¤ µé¾î¿ÔÀ½");
			//1.ÆÄ¶ó¹ÌÅÍ°¡ ³Ñ¾î¿È
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2. Ã³¸®ÇÔ
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			
			request.setAttribute("bv", bv);		//³»ºÎÀûÀ¸·Î ÀÚ¿ø°øÀ¯	
			
			//3.ÀÌµ¿ÇÔ			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");			
			rd.forward(request, response);	
		}else if (command.equals("/board/boardModifyAction.do")) {
			
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardDao bd = new BoardDao();
			int value = bd.updateBoard(subject, content, writer, ip, midx,bidx_);
			System.out.println(value);
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);				
			}	
			
			
		}else if (command.equals("/board/boardDelete.do")) {
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			request.setAttribute("bidx", bidx);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");					
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardDeleteAction.do")) {
			
		
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
						
			BoardDao bd = new BoardDao();
			int value = bd.deleteBoard(bidx_);
	
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
		}else if (command.equals("/board/boardReply.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			
			request.setAttribute("bv", bv);			
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");					
			rd.forward(request, response);
						
		} else if (command.equals("/board/boardReplyAction.do")) {
			
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
			
		} else if (command.equals("/board/fileDownload.do")) {
			
			String filename= request.getParameter("filename");
			//ÆÄÀÏÀÇ ÀüÃ¼°æ·Î
			String filePath = saveFullPath+File.separator+filename;
			Path source = Paths.get(filePath);
			
			String mimeType = Files.probeContentType(source);
			
			if(mimeType == null) { 
				mimeType = "application/octet-stream"; 
			} 			
			
			//ÆÄÀÏÇü½ÄÀ» Çì´õÁ¤º¸¿¡ ´ã´Â´Ù
			response.setContentType(mimeType);
			
			String encodingFileName = new String(filename.getBytes("UTF-8"));
			//Ã·ºÎÇØ¼­ ´Ù¿î·ÎµåµÇ´Â ÆÄÀÏÀ» Çì´õÁ¤º¸¿¡ ´ã´Â´Ù
			response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
			
			//ÇØ´çÀ§Ä¡¿¡ ÀÖ´Â ÆÄÀÏÀ» ÀÐ¾îµå¸°´Ù.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//ÆÄÀÏ¾²±âÀ§ÇÑ ½ºÆ®¸²
			ServletOutputStream servletOutStream = response.getOutputStream();
			
			byte[] b = new byte[4096];
			
			int read = 0;
			
			while((read =fileInputStream.read(b, 0, b.length))!=-1) {
				//ÆÄÀÏ¾²±â
				servletOutStream.write(b, 0, read);
				
			}
			//Ãâ·Â
>>>>>>> branch 'master' of https://github.com/hohojong/apiB.git
			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
			
			
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
