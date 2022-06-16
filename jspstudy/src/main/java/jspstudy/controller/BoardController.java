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
import jspstudy.domain.PageMaker;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����η� �� request�� ������  ó��
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
			System.out.println("�۾��� ȭ�鿡 ������");
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("�۾��� ó�� ȭ������ ������");
			
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
						
			String subject = multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer = multipartRequest.getParameter("writer");
			//System.out.println(subject+";"+content+";"+writer);
			
			//�������� ����� ������ ��� ��ü����
			Enumeration files =  multipartRequest.getFileNames();
			//��� ��ü�� ���� �̸��� ��´�
			String file = (String)files.nextElement();
			//�Ѿ���� ��ü�߿� �ش�Ǵ� �����̸����� �Ǿ��ִ� �����̸��� �����Ѵ�(����Ǵ� �����̸�)
			String fileName = multipartRequest.getFilesystemName(file);
			//������ �����̸�
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
			System.out.println("�Խ��� ����Ʈ ȭ�� ������");
			
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
						
			//ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);			
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist  = bd.boardSelectAll(scri);
			
			request.setAttribute("alist", alist);
			request.setAttribute("pm", pm);
			
			//�̵�
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardContent.do")) {
			//1.�Ѿ�� ���� �޴´�
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2.ó���Ѵ�
			BoardDao bd =new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);		 //���ο� ���� ��ġ���� �ڿ� �����Ѵ�
			
			//3.�̵��Ѵ�
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
		}else if (command.equals("/board/boardModify.do")) {
			
			System.out.println("���� ������");
			//1.�Ķ���Ͱ� �Ѿ��
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2. ó����
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			
			request.setAttribute("bv", bv);		//���������� �ڿ�����	
			
			//3.�̵���			
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
			//������ ��ü���
			String filePath = saveFullPath+File.separator+filename;
			Path source = Paths.get(filePath);
			
			String mimeType = Files.probeContentType(source);
			
			if(mimeType == null) { 
				mimeType = "application/octet-stream"; 
			} 			
			
			//���������� ��������� ��´�
			response.setContentType(mimeType);
			
			String encodingFileName = new String(filename.getBytes("UTF-8"));
			//÷���ؼ� �ٿ�ε�Ǵ� ������ ��������� ��´�
			response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
			
			//�ش���ġ�� �ִ� ������ �о�帰��.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//���Ͼ������� ��Ʈ��
			ServletOutputStream servletOutStream = response.getOutputStream();
			
			byte[] b = new byte[4096];
			
			int read = 0;
			
			while((read =fileInputStream.read(b, 0, b.length))!=-1) {
				//���Ͼ���
				servletOutStream.write(b, 0, read);
				
			}
			//���
			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
			
			
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
