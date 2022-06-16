package controller;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import damain.BoardVo;
import damain.PageMaker;
import damain.SearchCriteria;
import service.BoardDao;


@WebServlet("/BoardController")
public class BoardController {

protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
	
		String pj = request.getContextPath();
		System.out.println(pj);
		String command = uri.substring(pj.length());
		System.out.println("command:"+command);
		
		
		// 파일 업로드 하기 !!!
		
		
		int sizeLimit = 1024*1024*15;  // 파일 사이즈 크기 지정
		
		String uploadPath ="D:\\dev\\Project\\src\\main\\webapp\\"; // 파일 업로드시 저장할 위치
		
		String saveFolder = "images";  // 위치에 있는 폴더이름
		
		String saveFullPath = uploadPath + saveFolder;  // 위치 + 폴더이름 
		
		
		
		
			 
			 
		if(command.equals("/board/boardWrite.do")) {  // 글쓰기 페이지
				
		    // 값을 보낼게 없으니 서블릿으로 보냄 서블릿 = forward 방식으로 지정.
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			
			rd.forward(request, response);
			
			
			
		}else if (command.equals("/board/boardWriteAction.do")) {  // 작성페이지에서 글다 작성하고 글쓰기 등록버튼을 누르면 실행됨
			System.out.println("작성액션화면으로 들어옴~");
			
			
			
			// jar 로 이용해서 새로운 객체를 생성함...
			
			MultipartRequest multipartRequest = null;   // mutipatrequet= request 같은 역할인데 파일 업로드를 위해서 필요한것
			
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
			
			
			// request = 클라이언트 >> 서버 요청 servlet ...서블릿
			// saveFullPath => 저장경로 위치 - > 서버측에 파일이 저장될 경로
			// sizeLimit = > 파일 사이즈 - > 한번에 업로드 사이즈 크기
			// 언어 코드 = > UTF-8 - > 파일의 인코딩 방식.
			// new DefalutrenamePolicy()  == > 파일이름 중복처리를 위함
			
			
			
			// /board/boardWrite 에서 값이 넘어온  제목 , 내용 ,작성자를 request --> multipartRequest 수정
			
			String subject = multipartRequest.getParameter("subject");
			String content = multipartRequest.getParameter("content");
			String writer = multipartRequest.getParameter("writer");
			
			
		//	System.out.println(subject+";"+content+";"+writer);
			
			
			//열거자에 저장될 파일을 담는 객체 
			// Enumeration == > 데이터를 한번에 출력하게 도와줌..
			
			// 폼에서 전송된 파라미터 타입의 file 이 아닌 이름 들을  Enumeration =>리턴 ?
			Enumeration files = multipartRequest.getFileNames();
			
			// 실행순서
			// Enumneration = > 
            // hasMoreElements() = > Enumneration 내용이있는지 확인
			// nextElement(); = > 내용 값을 가져옴.
			
			
			String file = (String)files.nextElement();
			
			// 글쓰기페이지에 업로드한 파일이 서버에 업로된 파일의 이름을 가져온다. 서버에서 인식한 이름
			
			String fileName = multipartRequest.getFilesystemName(file);
			
			
			System.out.println("fileName =" +fileName);
			
			// 클라이언트가 업로드한 파일의 원본이름을 반환한다. 내가 올린 이름.
			
			String originalFileName = multipartRequest.getOriginalFileName(file);
			
			
			
			//  ip 주소 
			String ip = InetAddress.getLocalHost().getHostAddress();
			
			
			
			// 회원으로 로그인 한 사람의 세션 값  === > midx 기준
			
			
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			
			
			// 글쓰기 페이지 넘어온 값을 insertboard 에다가 데이터 집어 넣음.
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx,fileName);
			
			if (value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}			
			
		}else if (command.equals("/board/boardList.do")) {
			System.out.println("글쓰기리스트");
			
			
			String page = request.getParameter("page");      //글쓰기 게시판 페이지 !!
			if(page==null) page="1";
			int pagex = Integer.parseInt(page);
			
			
			
			// 글쓰기게시판 리스트에서 찾을 내용을 keyword 값으로 받아서 
			// 계속 둘려줌
			
			String keyword =request.getParameter("keyword");
			if(keyword==null) keyword = "";
			
			System.out.println("keyword = " + keyword);
			
			// 글쓰기 게시판에서 제목으로 검색이냐 , 작성자로 검색이냐를 알려줌
			
			
			String searchType = request.getParameter("searchType");
			if(searchType==null) searchType = "";
			
			System.out.println("searchType =" +searchType);
			
			
			// 받아온 페이지값과 keyword 값 searchType 을 새로운 객체 생성하여 값을 저장해줌!!
			
			SearchCriteria scri = new SearchCriteria();  
			scri.setPage(pagex);			
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			System.out.println("scri="+scri);
			
			// 검색창에서 전송해서 받은 keyword 값을 넣어서 글 개수를 출력하는 부문...
			
			BoardDao bd = new BoardDao();
			int result = bd.boardTotal(scri);
			
			
			System.out.println("result="+result);
			
			//  페이지 메이커 클래스에서 서치타입 scri 값을 넣어주고 전체 게시물 값도 확인해줌....
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(result); // << Dao  boardtotal 로직도 같이 실행됨.
			
			System.out.println("pm"+pm);
			
			System.out.println("----------------------------------------");
				
			// scri 추가로 넣어줌.. 값을 sql 구문도 변경.
			// MemberDao  boardlistall sql 구문 변경 필수.
			
			ArrayList<BoardVo> alist  = bd.boardSelectAll(scri);
			
		   
			
			request.setAttribute("pm", pm);
 					
			
			request.setAttribute("alist", alist);
			
			System.out.println("alist="+alist);
			System.out.println("pm="+pm);
			
		
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		
		
 
        }else if (command.equals("/board/boardContent.do")) { // 게시판 내용보기
			
			String bidx = request.getParameter("bidx");
			System.out.println("bidx="+bidx);
			
			
			int bidx_ = Integer.parseInt(bidx);
		   System.out.println("bidx="+bidx_);
			
		   
		   
		   System.out.println("-------------------조회수 쿠키ㅏ------------------------------------");
		   
		   
		   Cookie viewCookie=null; // 1.쿠키값을 정희해준다.
			Cookie[] cookies=request.getCookies(); // 2. 클라이언트가 보낸 데이터에서 쿠기값을 가져온다.

			System.out.println("cookie : "+cookies); // 3.쿠키이름 확인하기 위해서 출력한다.
			
	        
	        if(cookies !=null) {  // 4. 쿠키의 값이 있을경우에

				for (int i = 0; i < cookies.length; i++) {  // 5.쿠기가 돌아가는동안에
				
	                
	                //만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
					if(cookies[i].getName().equals("|"+bidx+"|")) {
						System.out.println("if문 쿠키 이름 : "+cookies[i].getName());  // 6.내가 밑에서 만들어놓은 쿠키가 있는지 확인좀..
					
	                //찾은 쿠키를 변수에 저장
						viewCookie=cookies[i]; // 7. 쿠키값을 찾으면 그거를 viewCookkie 에다가 저장을 해봅시다.
						System.out.println("viewCookie ="+viewCookie); // 8. 위치확인
					}
				}
				
			}else {  // 9.쿠키가 없으면 실행
				System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
			}


			//만들어진 쿠키가 없음을 확인
			if(viewCookie==null) {  // 10. 쿠키가 없을때 실행대는 로직이고 쿠기가 있으면 실행이 안되고 위에서 멈춘다.
	        
	          	System.out.println("viewCookie 확인 로직 : 쿠키 없당"); // 11. 쿠키가 없다는걸 알려줘야함.,
				
	            try {                                                // 12. 예외출력을 이용해서 씀 try catch 
	            
	            	//이 페이지에 왔다는 증거용(?) 쿠키 생성
					Cookie newCookie=new Cookie("|"+bidx+"|","OK"); // 13.새로운 쿠기를 생성해서 (key,value) 값을 넣어줌 
					response.addCookie(newCookie);                      // 14. 13번에서 쿠기 값을 넣어준거를 다시 클라이언트에다가 보내줌 
	                
	                //쿠키가 없으니 증가 로직 진행
					BoardDao bd =new BoardDao();                         // 15. 쿠키 클라이언트에 보내주고 조회수 증가 로직을 실행시켜줌
					bd.boardCnt(bidx_);                                    // 16. 게시물번호에 대한 조회수 카운트
	                
				} catch (Exception e) {
					System.out.println("쿠키 넣을때 오류 나나? : "+e.getMessage());  // 17. 입력오류 나올때는 입력 오류인지 확인하는 에외부분
					e.getStackTrace();

				}
			
	        //만들어진 쿠키가 있으면 증가로직 진행하지 않음
			}else {                                                // 18. 만들어진 쿠기가 있으면 
				System.out.println("viewCookie 확인 로직 : 쿠키 있당");  
				String value=viewCookie.getValue();                  // 19.쿠키의 값을 반환  13번 "Engilsh" 부분을반환
				System.out.println("viewCookie 확인 로직 : 쿠키 value : "+value); // 20. 콘솔로 알아보기 쉽게 출력
			}
		   
			
			System.out.println("-------------------조회수 쿠키ㅏ------------------------------------");
			
		   // 각각의 bidx 의 값을꺼냄
			BoardDao bd =new BoardDao();
			
			
			// 게시판 BIDX 번호만으로 내용 출력하기
			BoardVo bv = bd.boardSelectOne(bidx_);
			
		
			
			System.out.println("bv="+bv);
			
			request.setAttribute("bv", bv);		
			
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
			
			
        }else if (command.equals("/board/boardModify.do")) { //게시판 수정하기
			
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
			System.out.println("subject="+subject);
			String content = request.getParameter("content");
			System.out.println("content="+content);
			String writer = request.getParameter("writer");
			System.out.println("writer="+writer);
			String bidx = request.getParameter("bidx");
			System.out.println("bidx="+bidx);
			int bidx_ = Integer.parseInt(bidx);
			System.out.println("bidx_="+bidx_);
			
			String ip = InetAddress.getLocalHost().getHostAddress();
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
		//	int midx =3;
			
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
			System.out.println("bidx_ ="+bidx_);
			
		
			
			
			
			System.out.println("삭제페이지");
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
			
		}else if (command.equals("/board/boardReplyAction.do")) {
			
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
			//int midx = 1;
			
			
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
			
			
			//-----------------------------------------------------------------------------
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if (value ==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");				
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);				
			}	
			
		} else if(command.equals("/board/fileDownload.do")) {
			
			
			// boardWirte input 에 있는 name 값을 가져온다.
			
			String filename = request.getParameter("filename");
			
			System.out.println("filename =" + filename);
			
			// 파일의 전체 경로를 뽑기.
			//             saveFullPATH        + /  +  filename   ==> 값임
			
			String filePath = saveFullPath+File.separator + filename;
			
			 // 파일 리턴 값 == >   ??
			Path source = Paths.get(filePath);
			
			// 파일을 텍스트 형태로 전환해서 전달하기 위해 변환함..
			// probeContentType == > 
			String mimeType = Files.probeContentType(source);
			
			System.out.println("mimeType ="+mimeType);
			
			// 브라우저로 보낼때 파일로 보낼수 없으니 마임타입으로 웹루트 저장소에 읽혀서 보내야함
			response.setContentType(mimeType);
			
			
			// 파일 업로드를 구현하고 파일을 다운하면 되는데 
			// 파일이 깨지는 현상이 발생함 
			// 크롬 : 파일명 특수문자 깨짐 , 익스플로우 : 크롬 + 한글파일 다운 x
			// 파이어폭스 : 파일 공백잇으면 공백기준으로 뒤에 짤림...
			
			//폼에서 전송 된 파일이름을 가져와서 인코딩을= utf-8 로 시켜줌....
			String encodingfileName = new String(filename.getBytes("UTF-8"));
			
			System.out.println("encodingfileName =" +encodingfileName);
			
			// 파일을 헤더 정보에 담는다...
              // -- > Content-Disposition == > 다운로드 되거나 로컬에 저장되는 형식
			// Content-Disposition => inline (기본값 , 웹페이지 안에서 또는 웹페이지로 나타남)
			// attachment = > 반드시 다운로드 받아야 한다는 내용
			// filename = > UTF-8 로 인코딩 시키자......
		
			
			
			  response.setHeader("Content-Disposition", "attachment;fileName="+encodingfileName);
			   // 해당 위치에 있는파일을 읽어드린다 -- > filePath 위치
			  FileInputStream fileInputStream = new FileInputStream(filePath);
			  
			  System.out.println("fileInputStream =" +fileInputStream);
			
			
			// 서버에서 클라이언트 서버로 뿌려줘야함..
		    ServletOutputStream servletOutputStream=response.getOutputStream();	
		    
		    
		    System.out.println("servletOutputStream =" +servletOutputStream);
			
		    // 바이트를 자를때 많이 쓴다   크기를 어떻게 주냐에 늦게 올수도 있고 빠르게 올수도 있음.
		    byte[] b = new byte[4096];
		    
		    int read = 0;
		    
		    while((read =fileInputStream.read(b,0,b.length))!=-1) {
				  
				servletOutputStream.write(b,0,read);
		    
		}
		    servletOutputStream.flush(); //강제출력
			  servletOutputStream.close();  // 닫아줌
			  fileInputStream.close(); //외부자료 닫아줌,
			
}
}

}