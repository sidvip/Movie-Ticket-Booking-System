import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListDatabase {
	
	public void MoviesList() {
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem", "root", "root");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from movie");
		System.out.println("\t --------------------------------------");
		System.out.println("\t|  Movie   | Director  | IMDB R | Lang |");
		System.out.println("\t --------------------------------------");
		while(rs.next()) {
			System.out.println("\t|  " + rs.getString(2) +  "  |  " + rs.getString(3) +
					"  |  " + rs.getInt(4) + "  |  " +rs.getString(5) + "   |");
		}
		System.out.println("\t --------------------------------------");
		con.close();
	
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}
	
	public void ShowsList() {
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from theatre "
				+ "inner join mshow, hall, movie "
				+ "where theatre.tshowId=mshow.showId "
				+ "and theatre.hallInfo=hall.hallId"
				+ " and mshow.showId = movie.mId");
		System.out.println("\n\t\t  --------------------------------------");
		System.out.println("\t\t |      Available Shows in the City     |");
		System.out.println("\t\t  --------------------------------------\n");
		int count = 1;
		while(rs.next()) {
			System.out.println("\n\n\t ===========================================================================");
			System.out.println("\t| Show " + count + "  ");
			System.out.println("\t|");

			System.out.println("\t|    Movie Information\n");
			System.out.println("\t|       Movie Name : " + rs.getString(23));
			System.out.println("\t|       Director Name : " + rs.getString(24));
			System.out.println("\t|       IMDB Rating : " + rs.getInt(25));
			System.out.println("\t|       Language : " + rs.getString(26));

			System.out.println("\t|");

			System.out.println("\t|    Theatre Information\n");
			System.out.println("\t|       Theatre Name : " + rs.getString(2));
			System.out.println("\t|       Theatre Address : " + rs.getString(3));
			System.out.println("\t|       Number of Halls : " + rs.getString(4));
			System.out.println("\t|       Show Date : " + rs.getString(7));
			System.out.println("\t|       Show Time : " + rs.getString(8));
			System.out.println("\t|       Show Duration : " + rs.getInt(9));
			System.out.println("\t|       Show Price : " + rs.getDouble(10));
			System.out.print("\t|       Seat Information: ");
			System.out.println("---------------------------------------");
			System.out.println("\t|\t\t\t  Class Type      -> " + rs.getString(13) + "     "+ rs.getString(16) +"     " + rs.getString(19)); 
			System.out.println("\t|\t\t\t  Number of Seats -> " + rs.getInt(14) + "    "+ rs.getInt(17) +"    " + rs.getInt(20));
			System.out.println("\t|\t\t\t  Seat Charge     -> " + rs.getDouble(15) +"  "+ rs.getDouble(18) +"  " + rs.getDouble(21));
			System.out.println("\t|\t\t\t ---------------------------------------");
			
			System.out.println("\t|");
			System.out.println("\t ===========================================================================");
			count +=1;
		}
		con.close();
	
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}

}
