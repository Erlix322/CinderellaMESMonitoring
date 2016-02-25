package cinderella.Beans;

public class User {
	private static String userName;
	private static String password;
	
	public User(String username,String password){
		this.userName = username;
		this.password = password;
	}
	
	public static String getUser(){
		return userName;		
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		User.password = password;
	}
}
