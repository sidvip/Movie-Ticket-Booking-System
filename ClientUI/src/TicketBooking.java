import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TicketBooking {
	
	private String userName;
	private String Password;
	
	public TicketBooking(String userName, String password) {
		super();
		this.userName = userName;
		Password = password;
	}
	
	public void bookTicket() {
		try {
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
    		Statement stmt = con.createStatement();
    
			Scanner sc = new Scanner(System.in);
			Utility my_util = new Utility();
			
			String flag = "pass";
			
			while (flag.equals("pass")) {
			
			String moviePrompt = "\n\t Enter the movie name: ";
			System.out.print(moviePrompt);
			String movieName = sc.nextLine();
			String moviesName = my_util.allMoviesName();
			movieName = my_util.movieCheck(moviePrompt, movieName, moviesName);
			
			String theatrePrompt = "\n\t Enter the theatre name: ";
			System.out.print(theatrePrompt);
			String theatreName = sc.nextLine();
			String theatresName = my_util.allTheatresName();
			theatreName = my_util.movieCheck(theatrePrompt, theatreName, theatresName);
			
			String seatClassPrompt = "\n\t Enter the seat class: ";
			System.out.print(seatClassPrompt);
			String seatClass = sc.nextLine();
			String seatClasses = "A,B,C";
			seatClass = my_util.movieCheck(seatClassPrompt,seatClass, seatClasses);
			
			String seatClassStatus = "";
			if (seatClass.equals("A")) {
				seatClassStatus = "seatClass1";
			} else if (seatClass.equals("B")) {
				seatClassStatus = "seatClass2";
			} else if (seatClass.equals("C")) {
				seatClassStatus = "seatClass3";
			}
			
			String requestTheatreQuery = "select * from theatre inner join mshow, hall, movie"
    				+ " where theatre.tshowId=mshow.showId and theatre.hallInfo=hall.hallId and mshow.showId = movie.mId and tName='" + theatreName + "'"; 
			ResultSet rs = stmt.executeQuery(requestTheatreQuery);
			while (rs.next()) {
				String mName = rs.getString(23);
				String tName = rs.getString(2);
				if(mName.equals(movieName) && tName.equals(theatreName)) {
					flag = "fail";
					System.out.println("\n\n\tTicket Booked Successfully\n\n");
					String query = "insert into bookticket values('" + this.userName + "','" + movieName + "','" + theatreName + "','" + seatClass + "');";
		    		stmt.executeUpdate(query);
		    
		    		// Show Ticket to user //
		 			ShowTicket st = new ShowTicket(movieName, theatreName, seatClass, seatClassStatus, this.userName);
					st.showTicket();

					break;
				}
				flag = "pass";
				System.out.println("\n\tChoose the right show from the Theatre show time");
				}
			}
    	} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
