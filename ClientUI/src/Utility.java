import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Utility {


	public String CommandCheck(String prompt, String command) {
		String comVar = command;
		while (!(comVar.equals("ls_movies") || comVar.equals("ls_shows") || comVar.equals("in") || comVar.equals("exit"))) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
		}
		return comVar;
	}

	public String ResponseCheck(String prompt, String command) {
		String comVar = command;
		while (!(comVar.equals("l") || comVar.equals("r"))) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
		}
		return comVar;
	}

	public String MobileNumCheck(String prompt, String command) {
		String comVar = command;
		while (!(comVar.length() == 10) || !comVar.matches("[0-9]+")) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
		}
		return comVar;
	}

	public String EmailIdCheck(String prompt, String command) {
		String comVar = command;
		while (!comVar.contains("@")) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
		}
		return comVar;
	}

	public String allMoviesName() {
		String moviesName = "";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rc = stmt.executeQuery("select * from movie");

			while (rc.next()) {
				moviesName += rc.getString(2).trim() + ",";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return moviesName.trim();
	}

	public String movieCheck(String prompt, String command, String moviesName) {
		String comVar = command;
		String[] attrName = moviesName.split(",");
		String flag = checkString(attrName, comVar);
		
		while (!flag.equals("pass") || comVar.equals("")) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
			flag = checkString(attrName, comVar);
		}
		return comVar;
	}
	
	public String checkString(String[] array, String value) {
		String flag = "";
		for (int i=0; i<array.length ; i++) {
			if(array[i].equals(value)) {
				flag = "pass";
				break;
			} else {
				flag = "fail";
			}
		}
		return flag;
	}

	public String allTheatresName() {
		String theatresName = "";
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbsystem?useSSL=false", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rc = stmt.executeQuery("select * from theatre");

			while (rc.next()) {
				theatresName += rc.getString(2).trim() + ",";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theatresName.trim();
	}

	public String seatClassCheck(String prompt, String command, String seatClasses) {
		String comVar = command;
		while (!seatClasses.contains(comVar)|| comVar.equals("")) {
			Scanner sc = new Scanner(System.in);
			System.out.print(prompt);
			comVar = sc.nextLine();
		}
		return comVar;
	}

}


