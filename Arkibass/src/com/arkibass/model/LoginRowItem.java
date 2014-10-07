/**
 * 
 */
package com.arkibass.model;

/**
 * @author Carlos
 *
 */

public class LoginRowItem {
    
	private String role;
    private String user;
    private String password;
    
    public LoginRowItem(String user, String password, String role) {

    	this.role = role;
        this.user = user;
        this.password = password;
    }

    /**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}


	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


}
