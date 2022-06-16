package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconn {

	private String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String user="system";
	private String password="1234";
	
	
	
	
	
	

public Connection getConnection() {
	Connection conn = null;
	
  try {
	  //����� ����̹� �߿� ��밡���� Ŭ���� ã�Ƽ� ����
	  Class.forName("oracle.jdbc.driver.OracleDriver");
	  
	  //���������� ���ؼ� ���ᰴü�������� conn�� ��´�
	  conn = DriverManager.getConnection(url, user, password);
	  
} catch (Exception e) {
	
	e.printStackTrace();
}
 
	System.out.println("db연결");
  	return conn;
	}
	
}


