package unl.soc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	
	private final static Scanner INPUT = new Scanner(System.in);

	public static void main(String[] args) {
		
		Map<String, User> users = new HashMap<>();
		List<TextPost> postList = new ArrayList<>();
		List<String> visibilityList = new ArrayList<>();
		
		//Call method to get info from database
		Database.getDatabaseUsers(users);
		// Gets the number of users in Database to display
		int numOfUsers = users.size();

		User currentAccount = new User();
		
		Windows.mainWindow(numOfUsers);
		System.out.println("Enter your username :");
		String tempUsername = INPUT.nextLine();
		System.out.println("Enter your password :");
		String tempPassword = INPUT.nextLine();
		//Create temp account to attempt to match to a database account
		User tempAcc = new User();
		tempAcc.setUsername(tempUsername);
		tempAcc.setPassword(tempPassword);
		if(users.containsKey(tempAcc.getPassword()) == true) {
			System.out.println("Logged in");
			currentAccount = tempAcc;
			int userID = Database.getUserIDFromDatabase(users, tempUsername);
 			Windows.accountWindow(currentAccount);
			Database.getVisibilityList(userID, visibilityList);
			Database.getPostByVisibility(userID, postList);
			
			// Account window navigation
			String userChoice = " ";
			
			while(!userChoice.equals("Q")) {
				
				userChoice = INPUT.nextLine();
				
				switch (userChoice) {
				case "P":
	
					Windows.postWindowVisibility(postList, currentAccount);
					
					// Post Window Features
					
					String menuChoice = " ";
					
					while(!menuChoice.equals("B")) {
						
					menuChoice = INPUT.nextLine();
				
					if(menuChoice.equals("+")) {
						// Type Text Post
						System.out.println("Type your text");
						String postText = INPUT.nextLine();
						LocalDateTime postTime = LocalDateTime.now();
						String inputPostTime = postTime.format(DateTimeFormatter.ofPattern("HH:mm:ss, MM/dd/yyyy"));
						
						// Create Post 
						TextPost newPost = new TextPost();
						newPost.setMessage(postText);
						newPost = new TextPost(currentAccount, postList.size(), postText, inputPostTime, currentAccount.getUsername(), userID);
						postList.add(newPost);
						// Add post to Database
						DataSave.addPostToDatabase(currentAccount, postText, inputPostTime, currentAccount.getUsername(), userID);
						
						Windows.postWindow(postList, currentAccount);
					
					} else if(menuChoice.equals("B")) {
						Windows.accountWindow(currentAccount);
						break;		
					}
					}
					
					break;
				case "C":
					System.out.println("Type your new username");
					String newUsername = INPUT.nextLine();
					System.out.println("Type your new password");
					String newPassword = INPUT.nextLine();
					DataSave.addUserToDatabase(newUsername, newPassword);
					System.out.println("New account added");
					Windows.accountWindow(currentAccount);
					break;
				case "D":
					System.out.println("Are you sure you wish to delete this User?: (Y/N)");
					String deleteChoice = " ";
					deleteChoice = INPUT.nextLine();
					if(deleteChoice.equals("Y")) {
					DataSave.deleteAccountFromDatabase(userID);
					System.out.println("Account deleted");
					return;
					} else if(deleteChoice.equals("N")){
						System.out.println("Account not deleted");
						Windows.accountWindow(currentAccount);
						break;
					}
				case "V":

					Windows.visibilityWindow(currentAccount, postList, visibilityList);
					 
					menuChoice = " ";
					while(!menuChoice.equals("B")) {
						menuChoice = INPUT.nextLine();
						//Visibility features
						if(menuChoice.equals("+")) {
				
							System.out.println("Type the username to add: ");
							String addUsername = INPUT.nextLine();	
							int userToAddID = Database.getUserIDFromDatabase(users, addUsername);
							DataSave.addUserToVisibility(userID, userToAddID);
							visibilityList.add(addUsername);
							Windows.visibilityWindow(currentAccount, postList, visibilityList);							
							
						} else if(menuChoice.equals("-")) {
				
							System.out.println("Type the user to delete: ");
							String deleteUser = INPUT.nextLine();
							int userToDelete = Database.getUserIDFromDatabase(users, deleteUser);
							DataSave.deleteUserFromVisibility(userID, userToDelete);
							visibilityList.remove(deleteUser);
							Windows.visibilityWindow(currentAccount, postList, visibilityList);
							
						} else if(menuChoice.equals("B")) {
							Windows.accountWindow(currentAccount);
							break;		
						}
					}
					
					break;
				case "Q":
					return;
				}
			}
		} else {
			System.out.println("Username or Password is incorect");
		}
		
		return;

	}

}
