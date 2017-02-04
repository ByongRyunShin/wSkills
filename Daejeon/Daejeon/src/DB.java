import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
	public Connection con=null;
	public Statement stmt=null;
	public DB() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project101","user","1234");		
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
