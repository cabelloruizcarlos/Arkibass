/**
 * 
 */
package com.arkibass.model;

/**
 * @author Carlos
 *
 */

public class RoleRowItem {
    
	private String role;
    private String name;
    private String modules;
    private String products;
    private String activities;
    private String tasks;
    
    public RoleRowItem(String role, String name, String modules, String products, String activities, String tasks) {

    	this.role = role;
        this.name = name;
        this.modules = modules;
        this.products = products;
        this.activities = activities;
        this.tasks = tasks;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the activities
	 */
	public String getRestrictions() {
		String rest = "";
		
		if (!modules.isEmpty()){
			rest = modules;
		}
		if (!products.isEmpty()){
			rest += ";" + products;
		}

		if (!activities.isEmpty()){
			rest += ";" + activities;
		}
		if (!tasks.isEmpty()){
			rest += ";" + tasks;
		}
		
		if (!rest.isEmpty()){
			rest += "NONE";
		}
		return rest + ";";
	}
}
