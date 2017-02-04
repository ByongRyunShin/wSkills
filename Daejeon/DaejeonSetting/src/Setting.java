import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Setting {
	public static void main(String[] args){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost","root","1234");		
			Statement stmt = con.createStatement();
			
			ResultSet rs = con.getMetaData().getCatalogs();
			while (rs.next()) {
				String s = rs.getString("table_cat");
				if (!s.equals("sys") && !s.equals("information_schema") && !s.equals("mysql") && !s.equals("performance_schema")) {
					stmt.execute("drop database `" + s + "`");
				}
			}
			stmt.execute("CREATE SCHEMA `project101` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;");
			stmt.execute("CREATE TABLE `project101`.`member` (  `id` VARCHAR(11) NOT NULL,  `pw` VARCHAR(6) NULL,  `name` VARCHAR(45) NULL,  `phone_num` VARCHAR(13) NULL,  `address` VARCHAR(45) NULL,  `point` INT(11) NULL,  PRIMARY KEY (`id`));");
			stmt.execute("CREATE TABLE `project101`.`product` (  `id` INT(11) NOT NULL AUTO_INCREMENT,  `name` VARCHAR(45) NULL,  `type` VARCHAR(2) NULL,  `price` INT(11) NULL,  `size` VARCHAR(45) NULL,  `amount` INT(11) NULL,  PRIMARY KEY (`id`));");
			stmt.execute("CREATE TABLE `project101`.`purchase` (  `id` INT(11) NOT NULL,  `recipient` VARCHAR(45) NULL,  `size` VARCHAR(45) NULL,  `amount` INT(11) NULL,  `cost` INT(11) NULL,`address` VARCHAR(45) NULL,  `order_date` DATE NULL,  `delivery` TINYINT(1) NULL,  `product_id` INT(11) NULL AUTO_INCREMENT,  `member_id` VARCHAR(11) NULL,  PRIMARY KEY (`id`),  INDEX `pid_idx` (`product_id` ASC),  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC),  CONSTRAINT `pid`    FOREIGN KEY (`product_id`)    REFERENCES `project101`.`product` (`id`)    ON DELETE CASCADE    ON UPDATE CASCADE);");
			
			String line;
			int c=0;
			String cur=System.getProperty("user.dir");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cur+"\\DataFiles\\member.txt"),"UTF8"));
			while ((line = br.readLine()) != null) {
				String[] word = line.trim().split("\t");
				stmt.executeUpdate("insert into `project101`.`member` values('" + word[0] +"','" + word[1] +"','" + word[2] +"','" + word[3] +"','" + word[4] +"','" + word[5] +"')");
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(cur+"\\DataFiles\\product.txt"),"UTF8"));
			while ((line = br.readLine()) != null) {
				String[] word = line.trim().split("\t");
				stmt.executeUpdate("insert into `project101`.`product` values(" + (++c) + ", '" + word[0] +"','" + word[1] +"','" + word[2] +"','" + word[3] +"','" + word[4] +"')");
			}
			
			stmt.execute("create user IF NOT EXISTS user@localhost identified by '1234';");
			stmt.execute("grant select, insert, delete, update on `project101`.* to user@localhost;");
			
		} catch (Exception e) {
			// TODO Auto-generated catch bck
			e.printStackTrace();
		}
		
		
	}
}
