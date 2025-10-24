package unl.soc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;

/**
 * Establishes a JDBC connection 
 * to extract data from the database
 * @author henryrenteria
 *
 */

public class Database {
		
	// JDBC driver parameters 
	public final static String hostname = " ";
	public final static String username = " ";
	public final static String password = " ";
	public final static String url = "jdbc:mysql://"+hostname+"/"+username;
	
	/**
	 * Gets Users from Database and puts them 
	 * into a map of users
	 * @param users
	 */
	public static void getDatabaseUsers(Map<String, User> users) {
		
	Connection conn = null;
	
	try {
		conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
		e.printStackTrace();
		return;
	}
	
	String s = "Select * from User;";
	PreparedStatement prep = null;
	ResultSet rs = null;
	
	try {
		prep = conn.prepareStatement(s);
		
		rs = prep.executeQuery();
		
		while(rs.next()) {
			
			//Get all user information from database
			int userID = rs.getInt("userID");
			String username = rs.getString("username");
			String password = rs.getString("password");
				
			//Create a User and put database info in
			User databaseUsers = new User();
			databaseUsers.setUserID(userID);
			databaseUsers.setUsername(username);
			databaseUsers.setPassword(password);
			//Add Users into map of users
			users.put(password, databaseUsers);
		} 

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	//Close all resources 
	try {
		if(rs != null)
			rs.close();
		if(prep != null)
			prep.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return;

} 
	
	/**
	 * Gets the UserID of the signed in account using their
	 * username and password
	 * @param users
	 * @param tempUsername
	 * @param tempPassword
	 * @return
	 */
	public static int getUserIDFromDatabase(Map<String, User> users, String tempUsername) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		String query = "select userID from User where username = ?";
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {		
			prep = conn.prepareStatement(query);
			prep.setString(1, tempUsername);
			rs = prep.executeQuery();
			
			while(rs.next()) { 
			
			int userID = rs.getInt("userID");
			
			rs.close();
			prep.close();
			conn.close();
			
			return userID;
			
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return -1;
	}
	
	
	/**
	 * Gets all posts from database 
	 * @param postList
	 */
	public static void getDatabasePosts(List<TextPost> postList) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String s = "Select * from Post";
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(s);
			
			rs = prep.executeQuery();
			
			while(rs.next()) {
				//Get all posts from database
				int postID = rs.getInt("postID");
				String postText = rs.getString("postText");
				String postTime = rs.getString("postTime");
				String author = rs.getString("author"); 
					
				//Create new post and put database info in
				TextPost databasePosts = new TextPost();
				databasePosts.setPostID(postID);
				databasePosts.setMessage(postText);
				databasePosts.setInputPostTime(postTime);
				databasePosts.setAuthor(author);
		
				//add posts into list
				postList.add(databasePosts);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Close all resources
		try {
			if(rs != null)
				rs.close();
			if(prep != null)
				prep.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return;
	}
	
	/**
	 * Gets Posts from database depending on the visibility 
	 * table
	 * @param userID
	 * @param postList
	 */
	public static void getPostByVisibility(int userID, List<TextPost> postList) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				select postID, postText, postTime, author from Post where userID = ? 
				union 
				select postID, postText, postTime, author from Post natural left join Visibility where userID2 = ? 
				order by postID;
				""";
		
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, userID);
			prep.setInt(2, userID);
			rs = prep.executeQuery();
			
			if(rs != null) {
			while(rs.next()) {
				// Get all filtered posts
				int postID = rs.getInt("postID");
				String postText = rs.getString("postText");
				String postTime = rs.getString("postTime");
				String author = rs.getString("author");
			
								
				//Create new post and put database info in
				TextPost databasePosts = new TextPost();
				databasePosts.setPostID(postID);
				databasePosts.setMessage(postText);
				databasePosts.setInputPostTime(postTime);
				databasePosts.setAuthor(author);
		
				//add posts into list
				postList.add(databasePosts);
			}
			}
							
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Close all resources
		   try {  
			   if(rs != null)   
				   rs.close();
			   if(prep != null)
				   prep.close();
				   conn.close();
			} catch (SQLException e) {
				   e.printStackTrace();
			}	
			return;
	}
	/**
	 * Gets the visibility list of current User from
	 * the database
	 * @param userID
	 * @param visibilityList
	 */
	public static void getVisibilityList(int userID, List<String> visibilityList) {
		
		Connection conn = null; 
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String query = """
				select username from User where userID in (select userID2 from Visibility where userID = ?)
				""";
		
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(query);
			prep.setInt(1, userID);
			rs = prep.executeQuery();
			
			if(rs != null) {
			while(rs.next()) {
				// Get all filtered posts
				String username = rs.getString("username");
				
				//add username to List
				visibilityList.add(username);
				
			}
			}
							
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Close all resources
		   try {  
			   if(rs != null)   
				   rs.close();
			   if(prep != null)
				   prep.close();
				   conn.close();
			} catch (SQLException e) {
				   e.printStackTrace();
			}	
			return;
		
		
	}
	
		
}

