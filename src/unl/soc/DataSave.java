package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Establishes a JDBC connection 
 * to insert data into the database 
 * @author henryrenteria
 *
 */
public class DataSave {
	
	// JDBC driver parameters 
	public final static String hostname = " ";
	public final static String username = " ";
	public final static String password = " ";
	public final static String url = "jdbc:mysql://"+hostname+"/"+username;
	

	/**
	 * Adds post posted by the current user
	 * to the Database of Posts
	 * @param user
	 * @param message
	 * @param inputPostTime
	 * @param author
	 * @param userID
	 */
	public static void addPostToDatabase(User user, String message, String inputPostTime, String author, int userID) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				insert into Post (postText, postTime, author, userID)
				values (?, ?, ?, ?)
				""";
		PreparedStatement prep = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, message);
			prep.setString(2, inputPostTime);
			prep.setString(3, author);
			prep.setInt(4, userID);
			prep.executeUpdate();
			
			conn.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows an established User to create a new account and 
	 * add that account to the database
	 * @param newUsername
	 * @param newPassword
	 */
	public static void addUserToDatabase(String newUsername, String newPassword) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				insert into User (username, password)
				values (?, ?)
				""";
		PreparedStatement prep = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setString(1, newUsername);
			prep.setString(2, newPassword);
			prep.executeUpdate();
			
			conn.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Deletes current account from the database
	 * starts with wiping visiblity, posts, and 
	 * finally the User
	 * @param userIDToDelete
	 */
	
	public static void deleteAccountFromDatabase(int userIDToDelete) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		String visiblityQuery = """
				delete from Visibility where userID = ? or userID2 = ?;
				""";
		String postQuery = """
				delete from Post where userID = ?
				""";
		String query = """
				delete from User where userID = ?
				""";
		PreparedStatement prep = null;
		
		try {
			prep = conn.prepareStatement(visiblityQuery);
			prep.setInt(1, userIDToDelete);
			prep.setInt(2, userIDToDelete);
			prep.executeUpdate();
			
			prep = conn.prepareStatement(postQuery);
			prep.setInt(1, userIDToDelete);
			prep.executeUpdate();
			
			prep = conn.prepareStatement(query);
			prep.setInt(1, userIDToDelete);
			prep.executeUpdate();
			
			conn.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Adds a User to the current accounts 
	 * visibility list using their userID
	 * @param accountsID
	 * @param userIDToAdd
	 */
	public static void addUserToVisibility(int accountsID, int userIDToAdd) {
		
		Connection conn = null;  
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				insert into Visibility(userID, userID2) 
				values (?, ?)
				""";
		PreparedStatement prep = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, accountsID);
			prep.setInt(2, userIDToAdd);
			prep.executeUpdate();
			
			conn.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
	
	/**
	 * Deletes a User from the current accounts 
	 * Visibility list
	 * @param accountsID
	 * @param userToDelete
	 */
	public static void deleteUserFromVisibility(int accountsID, int userToDelete) {
	
		Connection conn = null;  
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				delete from Visibility where userID = ? and userID2 = ?
				""";
		PreparedStatement prep = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, accountsID);
			prep.setInt(2, userToDelete);
			prep.executeUpdate();
			
			conn.close();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	
		
	

}
