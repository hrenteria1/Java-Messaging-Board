package unl.soc;

/**
 * A class that models a Jstagram Users
 * account information
 * @author henryrenteria
 *
 */
public class User {
	
	private int userID;
	private String username;
	private String password;
	private String visibility;
	
	public User() {
	}
	
	public User(int userID, String username, String password, String visibility) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.visibility = visibility;
	}
	
	
	// Getter methods
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getVisibility() {
		return visibility;
	}
	
	// Setter methods
	public void setUserID(int userID) {
		 this.userID = userID;
	}
	
	public void setUsername(String username) {
		 this.username = username;
	}
	
	public void setPassword(String password) {
		 this.password = password;
	}
	
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

}
