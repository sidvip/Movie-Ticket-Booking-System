import java.util.Scanner;

public class UserEntry {
	public String UserLR() {
		Scanner sc = new Scanner(System.in);
		Utility my_util = new Utility();

		String userResponse = "";
		String userInfo = "";

		System.out.println("\t\n ******** Press 'l' to Login or 'r' to Register ******** \n");

		System.out.print("  Enter the response: ");
		String response = sc.nextLine();
		userResponse = my_util.ResponseCheck("  Enter the response: ", response);
		System.out.println();

		if (userResponse.equals("r")){
			System.out.println(" \n\t Register Now\n");
			String custNamePrompt = "Enter your name: ";
			String custMobNumPrompt = "Enter the mobile Number: ";
			String custEmailIdPrompt = "Enter the E-mail Id: ";
			String userNamePrompt = "Enter the user name: ";
			String userPassPrompt = "Enter the password: ";


			System.out.print(custNamePrompt);
			String custName = sc.nextLine();

			System.out.print(custMobNumPrompt);
			String custMobNum = sc.nextLine();
			custMobNum = my_util.MobileNumCheck(custMobNumPrompt, custMobNum);

			System.out.print(custEmailIdPrompt);
			String custEmailId = sc.nextLine();
			custEmailId = my_util.EmailIdCheck(custEmailIdPrompt, custEmailId);

			System.out.print(userNamePrompt);
			String userName = sc.nextLine();

			System.out.print(userPassPrompt);
			String userPass = sc.nextLine();

			CustomerTable csTable = new CustomerTable(custName, custMobNum, custEmailId, userName, userPass);
			csTable.updateCustomerTable();

			System.out.println();

		} else if (userResponse.equals("l")) {

			System.out.println("\n\t Login Now\n");
			String ltag = "notdone";
			while(ltag.equals("notdone")) {
				String userNamePrompt = "Enter the mobile number: ";
				String userPassPrompt = "Enter the password: ";

				System.out.print(userNamePrompt);
				String userName = sc.nextLine();
				userName = my_util.MobileNumCheck(userNamePrompt, userName);

				System.out.print(userPassPrompt);
				String userPass = sc.nextLine();
				System.out.println();

				CustomerTable csTable = new CustomerTable(userName, userPass);
				ltag = csTable.CheckLogin();
				if (ltag.equals("")) {
					userInfo = "";
				} else {
					userInfo = userName + "," + userPass;
				}
			}
			System.out.println();
		}
		return userInfo;
	}
}
