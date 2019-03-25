package com.test1;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private String message;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	
	@Override
	public String execute() throws Exception {
		
		message = user.getUserName() + "´Ô ¹æ°¡¹æ°¡...";
		
		return "ok";
		
	}
	

}



