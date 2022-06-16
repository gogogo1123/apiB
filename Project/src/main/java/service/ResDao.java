package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import damain.ResVo;
import dbcon.dbconn;

public class ResDao {


	private Connection conn;
	private PreparedStatement pstmt;
	
	
	
	
	
	
	public ResDao() {
		dbconn db = new dbconn();
		this.conn = db.getConnection();
	}
	
	
	
	
	
	public int insertRes(String rvname,int Respeople,String dataA,String data,int midx,String membername,int rvprice,int rvpricesum) {

		int value=0;
		PreparedStatement pstmt = null;
		
		
		String sql = "insert into reservation(ridx,rvname,rvpeople,writedayA,writeday,midx,membername,rvprice,rvpricesum)"
				+"values(ridx_b_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
				
		
		try {
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, rvname);
			pstmt.setInt(2, Respeople);
			pstmt.setString(3, dataA);
			pstmt.setString(4, data);
			pstmt.setInt(5, midx);
			pstmt.setString(6, membername);
			pstmt.setInt(7, rvprice);
			pstmt.setInt(8, rvpricesum);
			value=pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("value =" +value);
		
		return value;
		
		
	}
	
	
	public ArrayList<ResVo> ResselectAll(){
		ArrayList<ResVo> alist = new ArrayList<ResVo>();
		
		
		ResultSet rs = null;
		
		String sql = "select * from reservation order by ridx desc";
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				ResVo rv = new ResVo();
				
				
				rv.setRvname(rs.getString("rvname"));
				rv.setRvpeople(rs.getInt("rvpeople"));
				rv.setWritedayA(rs.getString("WRITEDAYA"));
				rv.setWriteday(rs.getString("writeday"));
				rv.setMidx(rs.getInt("midx"));
				rv.setMembername(rs.getString("membername"));
				rv.setRidx(rs.getInt("ridx"));
				rv.setRvPrice(rs.getInt("rvprice"));
				rv.setRvPriceSum(rs.getInt("rvpricesum"));
				
				
				
				alist.add(rv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return alist;
	}
	
	
	
	
	
}
