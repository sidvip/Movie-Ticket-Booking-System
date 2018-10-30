import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class ShowTicket {

	private String movieName;
	private String theatreName;
	private String seatClass;
	private String seatClassStatus;
	private String userName;

	public ShowTicket(String movieName, String theatreName, String seatClass, String seatClassStatus, String userName) {
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.seatClass = seatClass;
		this.seatClassStatus = seatClassStatus;
		this.userName = userName;
	}

	public void showTicket() {

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
			Statement stmt = con.createStatement();
	        
			int seatClassPriceIndex = 0;
			int seatClassNumIndex = 0;
			String seatClassNumLabel = "";
			
			String modifyQuery = "select * from theatre inner join mshow, hall, movie"
					+ " where theatre.tshowId=mshow.showId and theatre.hallInfo=hall.hallId and mshow.showId = movie.mId and tName='" 
					+ theatreName + "'" + " and mName='" + movieName + "'"; 
			ResultSet uprs = stmt.executeQuery(modifyQuery);
			ResultSetMetaData rsmd =  uprs.getMetaData();

			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				if(rsmd.getColumnName(i).equals(this.seatClassStatus)) {
					seatClassNumIndex = i+1;
					seatClassPriceIndex = i+2;
					seatClassNumLabel = rsmd.getColumnName(seatClassNumIndex);
				}
			}
			
			while (uprs.next()) {
		   		
	    		System.out.println("\t\t Your Ticket: \n");
				System.out.println("\t\t\tUserName: " + this.userName);
				String movieName = uprs.getString(23);
				System.out.println("\t\t\tMovie Name: " + uprs.getString(23));
				
				String TheatreName = uprs.getString(2);
				System.out.println("\t\t\tTheatre Name: " + uprs.getString(2));
				
				String TheatreAdd = uprs.getString(3);
				System.out.println("\t\t\tTheatre Address: " + uprs.getString(3));
				
				String showDate = uprs.getString(7);
				System.out.println("\t\t\tShow Date: " + uprs.getString(7));
				
				String showTime = uprs.getString(8);
				System.out.println("\t\t\tShow Time: " + uprs.getString(8));
				
				double duration = uprs.getDouble(9);
				System.out.println("\t\t\tShow Duration: " + uprs.getDouble(9));
				
				System.out.println("\t\t\tSeat Class: " + this.seatClass);

				String seatNum = this.seatClass+uprs.getInt(seatClassNumIndex);
				System.out.println("\t\t\tSeat Number: " + this.seatClass+uprs.getInt(seatClassNumIndex));
				
				double classPrice = uprs.getDouble(seatClassPriceIndex);
				System.out.println("\t\t\tClass Price: " + uprs.getDouble(seatClassPriceIndex));

				double showPrice = uprs.getDouble(10);
				System.out.println("\t\t\tShow Price: " + uprs.getDouble(10));
				
				double totalPrice = uprs.getDouble(seatClassPriceIndex) + uprs.getDouble(10);
				System.out.println("\t\t\tTotal Price: " + (uprs.getDouble(seatClassPriceIndex) + uprs.getDouble(10)) + "\n\n");
				

				int hallInfoId = uprs.getInt(5);
				int maxNumberOfSeats = uprs.getInt(12);
				int seatNumCurrent = uprs.getInt(seatClassNumIndex);
				
				String hallQuery = "update hall set maxnumOfSeats=" + (maxNumberOfSeats-1) + ", " + seatClassNumLabel +"=" + (seatNumCurrent-1) + " where hallId="+hallInfoId+";";
				stmt.executeUpdate(hallQuery);
				
				// create ticket table //
				String createTicketTable = "create table if not exists TICKET ( " + "userId varchar(10) not null, movieName text, theatreName text, theatreAdd text, showDate text, showTime text" +
						",showDuration text, seatClass text, seatNumber text, classPrice double, showPrice double, totalPrice double);";
				stmt.executeUpdate(createTicketTable);
				
				
			    String updateTicketTable = "insert into ticket values('" + this.userName + "','" + movieName  + "','" + theatreName +"','" +TheatreAdd +
			    		"','" + showDate + "','" + showTime  + "','" + duration + "','" + this.seatClass +"','" + seatNum + "','" + classPrice + "','" +
			    		showPrice + "','" + totalPrice +"')";
			    stmt.executeUpdate(updateTicketTable);
			    stmt.close();
			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
