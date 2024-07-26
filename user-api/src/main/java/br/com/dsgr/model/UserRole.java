package br.com.dsgr.model;

public enum UserRole {
	ROLE_BASIC("BASIC"),
	ROLE_ADMIN("ADMIN"),
	ROLE_MANAGER("MANAGER");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
