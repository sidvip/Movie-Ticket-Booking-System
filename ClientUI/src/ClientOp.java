import java.util.*;

public class ClientOp {

	public static void main(String[] args) {
		Utility my_util = new Utility();

		System.out.println("\t\t\n ******* Welcome to Movie Ticket Booking System ******* \n");
		Scanner sc = new Scanner(System.in);
		String exitMessage = "exit";

		System.out.print("\t -> Use the following commands\n");
		System.out.println("\n\t\t\t a) ls_movies : To list all the recently available movies in the city");
		System.out.println("\n\t\t\t b) ls_shows : To list all the current shows in the theatres");
		System.out.println("\n\t\t\t c) in : To book the show");

		System.out.print("\n\t -> Enter the command or exit (to exit): ");
		String userInput = sc.nextLine();
		while (!userInput.equals(exitMessage)) {
			userInput = my_util.CommandCheck("\n\t -> Enter the command or exit (to exit): ", userInput);
			if (userInput.equals("ls_movies")) {
				ListDatabase moviesList = new ListDatabase();
				moviesList.MoviesList();
				userInput = "";
				continue;
			} else if (userInput.equals("ls_shows")) {
				ListDatabase showsList = new ListDatabase();
				showsList.ShowsList();
				userInput = "";
				continue;				
			} else if (userInput.equals("in")) {
				UserEntry userInfo = new UserEntry();
				String yourInfo = userInfo.UserLR();
				
				if (!yourInfo.equals("")) {
					TicketBooking tb = new TicketBooking(yourInfo.split(",")[0], yourInfo.split(",")[1]);
					tb.bookTicket();
				} 

				userInput = "";
				continue;
			} 
		}
	}
}
