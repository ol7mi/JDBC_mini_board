package dto;

import java.sql.Timestamp;

public class MiniBoardDTO {
	private String id;
	private String pw;
	private String username;
	private Timestamp join_date;
	
	public MiniBoardDTO() {
		super();
	}
	public MiniBoardDTO(String id, String pw, String username, Timestamp join_date) {
		this.id = id;
		this.pw = pw;
		this.username = username;
		this.join_date = join_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}
}
