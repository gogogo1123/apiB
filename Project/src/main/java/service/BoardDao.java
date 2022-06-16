package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import damain.BoardVo;
import damain.SearchCriteria;
import dbcon.dbconn;
public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	
	public BoardDao() {
	dbconn db = new dbconn();
	this.conn = db.getConnection();
	}
	
	
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri){    // SearchCriteria scri 값을 안넣어주면 
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();          // 29번 scri . << 이거 안됌 주의
		
		ResultSet rs = null;
		
		String str="";
			if(scri.getSearchType().equals("subject")){
				str = "and subject like ?";
			}else {
				str="and writer like  ?";
			}
		
		
		
		
		// "SELECT * FROM BOARD where delyn='N' ORDER BY originbidx DESC,depth asc"; ( 기존 로직)
		
		
			String sql = "SELECT * FROM ("
					+ "	SELECT ROWNUM AS rnum, A.* FROM ("
					+ "		select * from board where delyn='N' "+str+" order by originbidx desc, depth ASC)A "
					+ "	)B WHERE rnum BETWEEN ? AND ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%" );
			pstmt.setInt(2, (scri.getPage()-1)*15+1);
			pstmt.setInt(3, scri.getPage()*15);
			rs = pstmt.executeQuery();
		    
		    
		    while(rs.next()) {
		    	
		    
		    	BoardVo bv = new BoardVo();
		    	bv.setBidx(rs.getInt("bidx"));
		    	bv.setSubject(rs.getString("subject"));
		    	bv.setWriter(rs.getString("writer"));
		    	bv.setIp(rs.getString("ip"));
		    	bv.setWriteday(rs.getString("writeday"));
		    	bv.setLevel_(rs.getInt("level_"));
		    	bv.setHit(rs.getInt("hit"));           // 조회수 증가시키기
		    	alist.add(bv);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		
		return alist;
	}
	
	public int insertBoard(String subject,String content,String writer,String ip, int midx,String filename) {
		int value= 0;
		
		String sql="INSERT INTO BOARD(bidx,ORiginbidx,depth,level_,subject,content,writer,ip,midx,filename)"
				+ "values(bidx_b_seq.NEXTVAL,bidx_b_seq.NEXTVAL,0,0,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			pstmt.setString(6, filename);
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}	
		}	
		
		return value;
	}
	public BoardVo boardSelectOne(int bidx) {
		
		//boardCnt(bidx);          // 글작성 조회수 같이 실행해주기.. 같은  bidx 이니
		
		
		BoardVo bv = null;
		ResultSet rs= null;
		String sql="select * from board where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);   
			pstmt.setInt(1, bidx);
			rs  = pstmt.executeQuery();
			
			if(rs.next()) {  
				 bv = new BoardVo();
				
				bv.setBidx(rs.getInt("bidx"));   
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_"));
				
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));	
				bv.setFilename(rs.getString("filename"));
				bv.setHit(rs.getInt("hit"));
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			
		}	
		
		return bv;
	}
	
	public int updateBoard(String subject,String content,String writer,String ip,int midx,int bidx) {
		int value=0;
		
		String sql="update board set subject=?, content=?, writer=?, ip=?, midx=?, writeday=sysdate where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			pstmt.setInt(6, bidx);
			value= pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}		
		
		return value;
	}
	
	public int deleteBoard(int bidx) {
		int value=0;
		
		String sql="update board set delyn='Y', writeday=sysdate where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value= pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}			
		}		
		
		return value;
	}
	
	public int replyBoard(BoardVo bv) {
		int value= 0;
		
		String sql1= "update board set depth = depth+1 where originbidx=? and depth >?";
		
		String sql2="insert into board(bidx,originbidx,depth,level_,subject,content,writer,ip,midx)"
				+ " VALUES(BIDX_B_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		try {
			conn.setAutoCommit(false); //�ڵ�Ŀ�� ��� ��
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth()+1);
			pstmt.setInt(3, bv.getLevel_()+1);
			pstmt.setString(4, bv.getSubject());
			pstmt.setString(5, bv.getContent());
			pstmt.setString(6, bv.getWriter());
			pstmt.setString(7, bv.getIp());
			pstmt.setInt(8, bv.getMidx());
			value = pstmt.executeUpdate();	
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}	
		}	
		
		return value;
	}
	
	public int  boardTotal(SearchCriteria scri) {   //검색창!!
		int result = 0; //반환값.
		ResultSet rs = null; // 쿼리 실행값
		String str = ""; // 검색 키워드,서치 값
		
		if(scri.getSearchType().equals("subject")){     
	       
			str = "and subject like ?";	
	    }else {
	    	str = "and writer like ?";	
	     }; 
	
	  String SQL = "select count(*) as result from board where delyn='N' "+str+" ";
	
	  try {
		  pstmt = conn.prepareStatement(SQL);
		  pstmt.setString(1, "%"+scri.getKeyword()+"%");
		  
		  
		  rs = pstmt.executeQuery(); 
		  
	
 		  
		  if(rs.next()) {
			  result = rs.getInt("result");
		  }
		
	} catch (Exception e) {
          e.printStackTrace();
 	}
		
	  return result; // 값을 받는다 이걸  = rs.getInt("result"); 받는다느거임...
	}
	
	
	
	public void boardCnt(int bidx) {            // 게시판 조회수
		int cnt = 0 ;
		int rs = 0;
		
		
		String sql = "update board SET HIT =(HIT+1) where bidx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
            e.printStackTrace();
  		}

	}
	
	
}
