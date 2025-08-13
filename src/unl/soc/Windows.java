package unl.soc;

import java.util.*;

/**
 * Displays the windows used in the Jstgram 
 * project
 * @author henryrenteria
 *
 */
public class Windows {
	
	public static final String DISYPLAY_RESET = "\u001B[0m";
	public static final String ANSI_Green = "\u001B[32m";
    public static final String ANSI_Yellow = "\u001B[33m";
	public static final String DISPLAY_BLUE = "\u001B[34m";
    public static final String ANSI_Cyan = "\u001B[36m";

	
	/** 
	 * Displays the main window and the
	 * total number of users in the database
	 * @param numOfUsers
	 */
	public static void mainWindow(int numOfUsers) {
        System.out.println(ANSI_Cyan + " =============================================");
        System.out.println("|           Welcome to Jstgram 2.0!           |");
        System.out.println("|                                             |");
        System.out.println("|                *************                |");
        System.out.println("|                      *                      |");
        System.out.println("|                      *                      |");
        System.out.println("|                      *                      |");
        System.out.println("|                      *                      |");
        System.out.println("|               *      *                      |");
        System.out.println("|                *******                      |");
        System.out.println("|                                             |");
        System.out.println("|                                             |");
        System.out.println("|                                             |");
        System.out.printf("|   Current numbers of users in database: %d   |\n",numOfUsers);
        System.out.println("|                                             |");
        System.out.println(" =============================================" + DISYPLAY_RESET);
	}
	
	/**
	 * Displays user window after logging in
	 * @param account
	 */
	public static void accountWindow(User account) {
		System.out.println(ANSI_Cyan + " ====================================");
		System.out.println("|                                    |");
		System.out.println("|  (P) Posts                         |");
		System.out.println("|  (C) Create New Account            |");
		System.out.println("|  (D) Delete Account                |");
		System.out.println("|  (V) Post Visibility               |");
		System.out.println("|  (Q) Quit                          |");
		System.out.println("|                                    |");
		System.out.printf("|   Current User: %s              |\n", account.getUsername());
		System.out.println(" ====================================" + DISYPLAY_RESET);
	}
	
	/**
	 * Displays users posts and visible posts
	 * @param postList
	 * @param users
	 * @param account
	 */
	public static void postWindow(List<TextPost> postList, User currentAccount) {
		System.out.println(ANSI_Cyan + " ========================================");
		System.out.println("| My posts and visible posts             |");
		System.out.println("|                                        |");
		for(TextPost p : postList) {
			System.out.print(p.getFormattedPost());
			System.out.printf("|%18s, %18s|\n", p.getAuthor(), p.getInputPostTime());
		}
		System.out.println("|                                        |");
		System.out.println("|   (+) Publish a new post               |");
		System.out.println("|   (B) Back                             |");
		System.out.println("|                                        |");
		System.out.printf("|    Current user: %s                 |\n", currentAccount.getUsername());
		System.out.println(" ========================================" + DISYPLAY_RESET);
	}
	
	/**
	 * Displays all posts despite visibility
	 * @param postList
	 * @param currentAccount
	 */
	public static void postWindowVisibility(List<TextPost> postList, User currentAccount) {
		System.out.println(ANSI_Cyan + " ========================================");
		System.out.println("| My posts and visible posts             |");
		System.out.println("|                                        |");
		for(TextPost p : postList) {
			System.out.print(p.getFormattedPost());
			System.out.printf("|%18s, %18s|\n", p.getAuthor(), p.getInputPostTime());
		}
		System.out.println("|                                        |");
		System.out.println("|   (+) Publish a new post               |");
		System.out.println("|   (B) Back                             |");
		System.out.println("|                                        |");
		System.out.printf("|    Current user: %s                 |\n", currentAccount.getUsername());
		System.out.println(" ========================================" + DISYPLAY_RESET);
	}
	
	
	/**
	 * Displays the users who can see 
	 * the current users posts
	 * @param account
	 * @param postList
	 * @param visibilityList
	 */
	public static void visibilityWindow(User account, List<TextPost> postList, List<String> visibilityList) {
		System.out.println(ANSI_Cyan + " ==============================================");
		System.out.println("| My posts are visible to the following users   |");
		System.out.println("|\t\t\t\t\t        |"); 
		for(String s : 	visibilityList) {
			System.out.print("|  ");
			System.out.printf(ANSI_Green + "%2s\t\t\t\t\t" + DISYPLAY_RESET, s);
			System.out.println(ANSI_Cyan +"|");
		}
		System.out.println("|\t\t\t\t\t        |"); 
		System.out.println("|  (+) Add a user\t\t\t\t|");
		System.out.println("|  (-) Delete a user\t\t\t\t|");
		System.out.println("|  (B) Back\t\t\t\t\t|");
		System.out.println("|\t\t\t\t\t        |");
		System.out.printf( "|  Current user :  %s\t\t\t|\n", account.getUsername());
		System.out.println(" ==============================================="+ DISYPLAY_RESET);
		 
	}

	
	
}
