package com.warrantix.main.customview;

public class TokenItem {

	private String token;

	public TokenItem(){
		this.token = "";
	}

	public TokenItem(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return getToken();
	}
}
