import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CustomerTable {
	private String name;
	private String mobNum;
	private String emailId;
	private String userName;
	private String userPass;

	public CustomerTable(String name, String mobNum, String emailId, String userName, String userPass) {
		this.name = name;
		this.mobNum = mobNum;
		this.emailId = emailId;
		this.userName = userName;
		this.userPass = userPass;
	}

	public CustomerTable(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
	}


	public void updateCustomerTable() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
			Statement stmt = con.createStatement();
			String query = "insert into customer values('" + this.name + "','" + this.mobNum + "','" + this.emailId + "','" + this.userName + "','" + this.userPass + "');";
			stmt.executeUpdate(query);
			System.out.println("\nRegistered successfully\n");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String CheckLogin() {
		String ltag = "";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rc = stmt.executeQuery("select * from customer");

			while (rc.next()) {
				String dbUser = rc.getString(2);
				String dbPassword = rc.getString(5);
				if(dbUser.equals(this.userName) && dbPassword.equals(this.userPass)) {
					ltag = "done";
					break;
				} else {
					ltag = "notdone";
				}
			}
			
			if (ltag.equals("done")) {
				System.out.println("\n\t Logged in successfully \n");
			}
			else {
				System.out.println("\n\tEither username or password is invalid\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ltag;
	}

}
