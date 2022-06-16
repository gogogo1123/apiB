package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import damain.MemberVo;
import dbcon.dbconn;

public class MemberDao {

	private Connection conn;
	private PreparedStatement pstmt;

	
	public MemberDao() {
		dbconn db = new dbconn();
		this.conn = db.getConnection();
	}
	
	
	
	public int insertmember(String memberId,String memberPwd,String memberName,String memberGender,String memberEmail) {
		
		int value=0;
		PreparedStatement pstmt =null;
		
		
		String sql = "insert into member(midx,memberid,memberpwd,membername,membergender,memberemail)"
		+"values(midx_b_seq.nextval,?,?,?,?,?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberGender);
			pstmt.setString(5, memberEmail);
			value=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return value;
	}
	
	
	public MemberVo Login(String memberid,String memberPwd) {  // 아이디 비밀번호 입력시 로직 실행
		MemberVo mv = null;     // 데이터 객체 담아줄거임 return 값
		ResultSet rs = null;    // sql 실행결과 값 지정
		
		
		// sql 로그인하기위한 sql 쿼리문
	    String SQL = "select * from member where delyn='N' and memberid=? and memberPwd=?";  
	    
	    try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();  // 여기서 성공하면 rs 값이 = 1 , 실패하면 0 이됨
			
			// rs 의 값이 실행되고 한칸씩 밑으로 계속 도는 로직
			if(rs.next()) {
				
				// 데이터베이스에 입력된 멤버 변수에 대한 vo 객체 생성
				mv=new MemberVo();
				
				// vo 객체에다가 사용할 값 지정 해줌 
				mv.setmidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberPwd(rs.getString("memberPwd"));
				mv.setMemberName(rs.getString("membername"));
			}
			// 예외처리구문 어디가 잘못됫는지 확인
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    return  mv;  // vo 객체에다가 값 지정해놓고 나중에 사용
	}
	
     public MemberVo findByid(String memberName,String memberEmail) {  // 이름과 , 이메일을 입력하면 실행되게 만든것 다른것 넣어도됌
    	 System.out.println("memberName"+memberName);
    	 System.out.println("memberEmail"+memberEmail);
    	 
    	 MemberVo mv = null;  // 데이터 전송객체 값 널로 변경
 		ResultSet rs = null; // 결과값도 널 
 		
 		
 		String sql = "SELECT memberid FROM member WHERE membername=? and memberemail=?";
 		
 		System.out.println("sql"+sql);
 		
 		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt"+pstmt);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberEmail);
			rs = pstmt.executeQuery();
			
			System.out.println("rs"+rs);
			 
		//	System.out.println(rs.next());
		     
			
           if(rs.next()) { // 쿼리가 재대로 실행된경우
 				
        	   //객체 생성 
				mv = new MemberVo();	
				// 값을 넣어줌 멤버아이디
			    mv.setMemberid(rs.getString("memberid"));
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		
 	//	System.out.println("mv"+mv);
 		return mv;
     }
	
     
     public MemberVo findByPwd(String memberid,String memberName) {  // 아이디랑 ,이름입력하면 로직 실행됨
    	 
    	 MemberVo mv = null;  // 데이터객체 값 널 여기다가 저장해줄거임
  		ResultSet rs = null; // 결과값도 널 결과값 실행 위함
  		
  		String sql = "SELECT memberpwd FROM member WHERE memberid=? and membername=?";
  		
  		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt"+pstmt);
			
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberName);
			rs = pstmt.executeQuery();
			
			System.out.println("rs"+rs);
			 
		//	System.out.println(rs.next());
		     
			
           if(rs.next()) {  // 쿼리가 진행하는 동안에
 				
				mv = new MemberVo();	
				//객체생성
				
			    mv.setMemberPwd(rs.getString("memberPwd"));
			    // 찾을 비밀번호 값을 찾아줌
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		
 	//	System.out.println("mv"+mv);
 		return mv;
     }
	
     
     public ArrayList<MemberVo> memberListAll(){         
    	 
    	 // 멤버리스트를 뽑을 것은 ArrayList 배열에다가 모두 담기위해서  MemberVo 로 새로운 객체를 생성. 
    	 
    	 ArrayList<MemberVo> alist = new ArrayList<MemberVo>(); // << ARRYLIST 는 배열로 나타냄 [0,1,2,3,4] 저장
    	 
    	 // 멤버리스트 쫙 뽑고 midx (회원번호 기준으로 내림차순 실시)
    	 
    	 String sql = "select * from member where delyn='N' order by midx desc";
    	 
    	 // 쿼리문을 실행해야하는 prepareStatement pstm << 실행문
    	 // conn Connertion conn 데이터베이스 연결 시켜주기  conn << 연결
    	 
    	 ResultSet rs = null;
    	 
    	 try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// 생성한 MemverVo 인 데이터와 이클립스를 연결 하는 새로운 객체를 생성하고
				// 생성한 객체에다가 oracle 결과값을 실행하는 동안 이라는 코드를 만들어서
				// 객체에다가 결과 값을 저장해준다.
				
				MemberVo mv = new MemberVo();
				mv.setmidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberPwd(rs.getString("memberPwd"));
				mv.setMemberName(rs.getString("memberName"));
				mv.setMemberEmail(rs.getString("memberEmail"));
				mv.setMembergender(rs.getString("membergender"));
			    mv.setWriteday(rs.getString("writeday"));
				
				// 객체에다가 저장 했는데 저장한 객체가 소량이 아니기때문에 한번에 묶어서 가져가기 위해서
				// 위에서 만들었던 alist 배열에 순서대로 저장되게 값을 넣는다 밑에 양식있음..
				
				alist.add(mv);
				
				// 위에 저장한 멤버 객체인 mv 를 alist 배열에다가 넣어줘서 꺼내기 쉽게 만듬..
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    	 // alist 배열은 담을 데이터를 반환 시켜줌~
    	 
    	 return alist;
     }
     
     
     public int checkId(String memberId) {
    	 ResultSet rs = null;
    	 String sql = "select * from member where memberid = ?";
    	 int idCheck = 0;
    	 
    	 try {
    		 
    		 pstmt = conn.prepareStatement(sql);
    		 pstmt.setString(1, memberId);
    		 
    		 rs = pstmt.executeQuery();
			
    		 
    		 if(rs.next() || memberId.equals("")) {
    			 idCheck = 0; // 이미존재
    		 }else {
    			 idCheck = 1; // 존재하지않는경우 생성가능
    		 }
    		 
		} catch (Exception e) { 
           e.printStackTrace();
		}
    		
    	return idCheck;
    	 
     }
     
     
 
     
     
     
     
     
     
     }

