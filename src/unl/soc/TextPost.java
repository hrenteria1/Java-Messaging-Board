package unl.soc;

import java.time.LocalDateTime;

/**
 * Model for a text post used in Jstgram
 * @author henryrenteria
 *
 */
public class TextPost {
	
	private int postID;
	private String message;
	private String inputPostTime;
	private String author;
	private int userID;
	LocalDateTime postTime;
	
	public TextPost() {
	}
	
	//For new posts
	public TextPost(User user, int postID, String message, LocalDateTime postTime, String author, int userID) {
		this.postID = postID;
		this.message = message;
		this.postTime = postTime;
		this.author = author;
		this.userID = userID;
	}
	
	//For posts in databse
	public TextPost(User user, int postID, String message, String inputPostTime, String author, int userID) {
		this.postID = postID;
		this.message = message;
		this.inputPostTime = inputPostTime;
		this.author = author;
		this.userID = userID;
	}
	
	
	// Getter methods
	public int getPostID() {
		return postID;
	}
	
	public String getMessgae() {
		return message;
	}
	
	public String getInputPostTime() {
		return inputPostTime;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getUserID() {
		return userID;
	}
	
	// Setter methods
	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setInputPostTime(String inputPostTime) {
		this.inputPostTime = inputPostTime;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getFormattedPost() {
		return String.format("|%-40s|\n", message);
	}
	 
}
