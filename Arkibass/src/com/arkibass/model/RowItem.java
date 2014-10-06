/**
 * 
 */
package com.arkibass.model;

/**
 * @author Carlos
 *
 */

public class RowItem {
    
	private String report;
    private String name;
    private String description;
    private String department;
    private String standard;
    private String employee;
    private String telephone;
    
    public RowItem(){}
    
    //Constructor used by: Rooms by Building&Floor
    public RowItem(String report, String name, String desc, String department, String standard, String employee, String telephone) {

    	this.report = report;
        this.name = name;
        this.description = desc;
        this.department = department;
        this.standard = standard;
        this.employee = employee;
        this.telephone = telephone;
    }
    // Constructor used by: Rooms by Department
    public RowItem(String report, String name, String desc, String standard, String employee, String telephone) {

    	this.report = report;
        this.name = name;
        this.description = desc;
        this.standard = standard;
        this.employee = employee;
        this.telephone = telephone;
    }
    // Method used by: Rooms by Standard
    public void RowItemSt(String report, String name, String desc, String department, String employee, String telephone) {

    	this.report = report;
        this.name = name;
        this.description = desc;
        this.department = department;
        this.employee = employee;
        this.telephone = telephone;
    }
    
    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
    public String getStandard() {
        return standard;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public String getEmployee() {
        return employee;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone= telephone;
    }
	public String getReport() {
		// TODO Auto-generated method stub
		return this.report;
	}
	public void setReport(String report) {
        this.report= report;
    }   
    
    @Override
    public String toString() {
    	
    	switch (report){
    	
    	case "buildingfloor":
    		return name + "\n" + description + "\n" + department + "\n" + standard + "\n" + employee + "\n" + telephone;
    	case "department":
    		return name + "\n" + description + "\n" + standard + "\n" + employee + "\n" + telephone;
    	case "standard":
    		return name + "\n" + description + "\n" + department + "\n" + employee + "\n" + telephone;    		
    	default:
    		return "";
    	}
    }
}
