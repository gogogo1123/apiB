package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import damain.ItemVo;
import dbcon.dbconn;

public class ItemDao {

	
	private Connection conn;
	private PreparedStatement pstmt;
	
	
	
	public ItemDao() {
		dbconn db = new dbconn();
		this.conn = db.getConnection();
	}
	
	
	
	
	
	public int insertItem(String itemName,int itemQuantity,int midx,String membername,int itemPrice,int itemPricesum) {
		
		int value=0;
		PreparedStatement pstmt =null;
		
		
		String sql = "insert into item(itemidx,itemName,itemQuantity,midx,membername,itemprice,itempricesum)"
				+ "values(itemidx_b_seq.nextval,?,?,?,?,?,?)";
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, itemName);
			pstmt.setInt(2, itemQuantity);
			pstmt.setInt(3, midx);
		    pstmt.setString(4, membername);
		    pstmt.setInt(5, itemPrice);
		    pstmt.setInt(6, itemPricesum);
			value= pstmt.executeUpdate();
			
			
		} catch (Exception e) {
	      e.printStackTrace();
		}
		
		
		return value;
		
	}
	

	
	public ArrayList<ItemVo> itemselectAll(){
		ArrayList<ItemVo> alist = new ArrayList<ItemVo>();
		
		System.out.println("리스트 다오");
		ResultSet rs = null;
		
		String sql = "SELECT * FROM item ORDER BY itemidx DESC";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("rs =" +rs);
			// while 문을 사용해야함
			while(rs.next()) {
				
				ItemVo iv = new ItemVo();
				iv.setItemidx(rs.getInt("itemidx"));
				iv.setItemName(rs.getString("itemName"));
				iv.setItemQuantity(rs.getInt("itemQuantity"));
				iv.setMidx(rs.getInt("midx"));
				iv.setWriteday(rs.getString("writeday"));
				iv.setMemberName(rs.getString("membername"));
				iv.setItemPriceSum(rs.getInt("itempricesum"));
				iv.setItemPrice(rs.getInt("itemprice"));
				alist.add(iv);
				
				System.out.println("iv =" + iv);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return alist;
	}

	
}
