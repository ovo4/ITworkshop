package model;

public class User {
	private int user_id;
	private String name;
	private String password;
	private String role;
	
	public User() {
	    // デフォルトコンストラクタ
	}

	
	public User(int user_id, String name, String password, String role) {
		this.user_id = user_id;
		this.name = name;
		this.password = password;
		this.role = role;
	}
//	ゲッター
	public int getUser_id() { return user_id;}
	public String getName() {return name;}
	public String getPassword() {return password;}
	public String getRole() {return role;}
	
// セッター
	public void setUser_id(int user_id) { this.user_id = user_id; }
	public void setName(String name) {this.name = name;}
	public void setPassword(String password) {this.password = password;}
	public void setRole(String role) {this.role = role;}
	
}
