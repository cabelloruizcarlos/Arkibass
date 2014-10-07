package com.arkibass.model;

public class DBDefinition {

	// Office
	public  final String URL = "http://172.26.66.59/arkibass/";
	public  final String URL_Login = "http://172.26.66.59/arkibass/get_credentials.php";
	public  final String URL_Drawings = "http://172.26.66.59/arkibass/drawings/";
	// Home
//	public  final String URL = "http://192.168.233.1/arkibass/";
//	public  final String URL_Login = "http://192.168.233.1/arkibass/get_credentials.php";
//	public  final String URL_Drawings = "http://192.168.233.1/arkibass/drawings/";
	// Ñeko casa
//	public  final String URL = "http://192.168.1.33/arkibass/";
//	public  final String URL_Login = "http://192.168.1.33/arkibass/get_credentials.php";
//	public  final String URL_Drawings = "http://192.168.1.33/arkibass/drawings/";
	
	// Roles name
	public  final String SYSTEM = "AFMROOT";
	public  final String SYSTEM_ADMINISTRATOR = "AFMADMIN";
	public  final String HR_MANAGER = "AFMHRManager";
	public  final String HR_ASISTANT = "AFMHRAssistant";
	public  final String ASSISTANT = "AFMAssistant";
	
	// Tables name
	public  final String TABLE_AFMActivities = "afm_activities";
	public  final String TABLE_AFMLogin = "afm_login";
	public  final String TABLE_AFMModules = "afm_modules";
	public  final String TABLE_AFMProducts = "afm_products";
	public  final String TABLE_AFMRoles = "afm_roles";
	public  final String TABLE_AFMTasks = "afm_tasks";
	public  final String TABLE_Bl = "bl";
	public  final String TABLE_Dp = "dp";
	public  final String TABLE_Fl = "fl";
	public  final String TABLE_Rmstd = "rmstd";
	public  final String TABLE_Rm = "rm";
	public  final String TABLE_Em = "em";

	// Columns names
	public  final String Name = "name";
	public  final String Icon = "icon";
	public  final String AFM_Activity = "afm_activity";
	public  final String WWW_User = "www_user";
	public  final String WWW_Pass = "www_pass";
	public  final String AFM_Module = "afm_module";
	public  final String AFM_Product = "afm_product";
	public  final String AFM_Role = "afm_role";
	public  final String AFM_Modules = "afm_modules";
	public  final String AFM_Products = "afm_products";
	public  final String AFM_Activities = "afm_activities";
	public  final String AFM_Tasks = "afm_tasks";
	public  final String AFM_Task = "afm_task";
	public  final String Bl_Id = "bl_id";
	public  final String Address = "address";
	public  final String Zip_Code = "zip_code";
	public  final String City = "city";
	public  final String State = "state";
	public  final String Country = "Country";
	public  final String Count_Em = "count_em";
	public  final String Occupancy = "occupancy";
	public  final String Max_Occupancy = "max_occupancy";
	public  final String Photo = "photo";
	public  final String Comments = "comments";
	public  final String Dp_Id = "dp_id";
	public  final String Fl_Id = "fl_id";
	public  final String Rmstd_Id = "rmstd_id";
	public  final String Rm_Id = "rm_id";
	public  final String Telephone = "telephone";
	public  final String Em_Id = "em_id";
	public  final String Email = "email";
	public  final String Width = "width";
	public  final String Height = "height";
	public  final String MarginTop = "marginTop";
	public  final String MarginRight = "marginRight";
	public  final String MarginLeft = "marginLeft";
	public  final String MarginBottom = "marginBottom";
		
		
	public String SCreate(String table) {

		String r = null;
		switch (table) {
		case TABLE_AFMActivities:
			r = "CREATE TABLE '" + TABLE_AFMActivities + "' ('" + AFM_Module
					+ "' varchar(50) NOT NULL," + "'" + AFM_Product
					+ "' varchar(50) NOT NULL," + "'" + AFM_Activity
					+ "' varchar(50) NOT NULL DEFAULT 'NONE'," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Icon
					+ "' varchar(50) DEFAULT NULL," + "PRIMARY KEY ('"
					+ AFM_Module + "','" + AFM_Product + "','" + AFM_Activity
					+ "')," + "FOREIGN KEY ('" + AFM_Product
					+ "') REFERENCES '" + TABLE_AFMProducts
					+ "', FOREIGN KEY ('" + AFM_Module + "') REFERENCES '"
					+ TABLE_AFMModules + "')";
			break;
		case TABLE_AFMLogin:
			r = "CREATE TABLE '" + TABLE_AFMLogin + "' ('" + WWW_User
					+ "' varchar(50) NOT NULL," + "'" + WWW_Pass
					+ "' varchar(50) NOT NULL," + "'" + AFM_Role
					+ "' varchar(50) NOT NULL," + "PRIMARY KEY ('" + WWW_User
					+ "')," + "FOREIGN KEY ('" + AFM_Role + "') REFERENCES '"
					+ TABLE_AFMRoles + "')";
			break;
		case TABLE_AFMModules:
			r = "CREATE TABLE '" + TABLE_AFMModules + "' ('" + AFM_Module
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Icon
					+ "' varchar(50) DEFAULT NULL," + "PRIMARY KEY ('"
					+ AFM_Module + "'))";
			break;
		case TABLE_AFMProducts:
			r = "CREATE TABLE '" + TABLE_AFMProducts + "' ('" + AFM_Module
					+ "' varchar(50) NOT NULL," + "'" + AFM_Product
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Icon
					+ "' varchar(50) DEFAULT NULL," + "PRIMARY KEY ('"
					+ AFM_Module + "','" + AFM_Product + "'),"
					+ "FOREIGN KEY ('" + AFM_Module + "') REFERENCES '"
					+ TABLE_AFMModules + "')";
			break;
		case TABLE_AFMRoles:
			r = "CREATE TABLE '" + TABLE_AFMRoles + "' ('" + AFM_Role
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + AFM_Modules
					+ "' varchar(1000) DEFAULT NULL," + "'" + AFM_Products
					+ "' varchar(1000) DEFAULT NULL," + "'" + AFM_Activities
					+ "' varchar(1000) DEFAULT NULL," + "'" + AFM_Tasks
					+ "' varchar(1000) DEFAULT NULL," + "PRIMARY KEY ('"
					+ AFM_Role + "'))";
			break;
		case TABLE_AFMTasks:
			r = "CREATE TABLE '" + TABLE_AFMTasks + "' ('" + AFM_Module
					+ "' varchar(50) NOT NULL," + "'" + AFM_Product
					+ "' varchar(50) NOT NULL," + "'" + AFM_Activity
					+ "' varchar(50) NOT NULL," + "'" + AFM_Task
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Icon
					+ "' varchar(50) DEFAULT NULL," + "PRIMARY KEY ('"
					+ AFM_Module + "','" + AFM_Product + "','" + AFM_Activity
					+ "','" + AFM_Task + "')," + "FOREIGN KEY ('" + AFM_Module
					+ "') REFERENCES '" + TABLE_AFMModules
					+ "', FOREIGN KEY ('" + AFM_Product + "') REFERENCES '"
					+ TABLE_AFMProducts + "', FOREIGN KEY ('" + AFM_Activity
					+ "') REFERENCES '" + TABLE_AFMActivities + "')";
			break;
		case TABLE_Bl:
			r = "CREATE TABLE '" + TABLE_Bl + "' (" + "'" + Bl_Id
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Address
					+ "' varchar(100) DEFAULT NULL," + "'" + Zip_Code
					+ "' varchar(50) DEFAULT NULL," + "'" + City
					+ "' varchar(50) DEFAULT NULL," + "'" + State
					+ "' varchar(50) DEFAULT NULL," + "'" + Country
					+ "' varchar(50) DEFAULT NULL," + "'" + Count_Em
					+ "' int(11) NOT NULL DEFAULT '0'," + "'" + Occupancy
					+ "' int(11) NOT NULL DEFAULT '0'," + "'" + Max_Occupancy
					+ "' int(11) NOT NULL DEFAULT '0'," + "'" + Photo
					+ "' blob," + "'" + Comments
					+ "' varchar(1000) DEFAULT NULL," + "PRIMARY KEY ('"
					+ Bl_Id + "'))";
			break;
		case TABLE_Dp:
			r = "CREATE TABLE '" + TABLE_Dp + "' ('" + Dp_Id
					+ "' varchar(50) NOT NULL," + "'" + Count_Em
					+ "' int(11) NOT NULL DEFAULT '0'," + "PRIMARY KEY ('"
					+ Dp_Id + "'))";
			break;
		case TABLE_Fl:
			r = "CREATE TABLE '" + TABLE_Fl + "' (" + "'" + Bl_Id
					+ "' varchar(50) NOT NULL," + "'" + Fl_Id
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Count_Em
					+ "' int(11) NOT NULL DEFAULT '0'," + "PRIMARY KEY ('"
					+ Bl_Id + "','" + Fl_Id + "')," + "FOREIGN KEY ('" + Bl_Id
					+ "') REFERENCES '" + Bl_Id + "')";
			break;
		case TABLE_Rmstd:
			r = "CREATE TABLE '" + TABLE_Rmstd + "' (" + "'" + Rmstd_Id
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Count_Em
					+ "' int(11) NOT NULL DEFAULT '0'," + "PRIMARY KEY ('"
						+ Rmstd_Id + "'))";
			break;
		case TABLE_Rm:
			r = "CREATE TABLE '" + TABLE_Rm + "' (" + "'" + Bl_Id
					+ "' varchar(50) NOT NULL," + "'" + Fl_Id
					+ "' varchar(50) NOT NULL," + "'" + Rm_Id
					+ "' varchar(50) NOT NULL," + "'" + Dp_Id
					+ "' varchar(50) NOT NULL," + "'" + Rmstd_Id
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Count_Em
					+ "' int(11) NOT NULL DEFAULT '0'," + "'" + Telephone
					+ "' tinyint(14) DEFAULT NULL," + "PRIMARY KEY ('" + Bl_Id
					+ "','" + Fl_Id + "','" + Rm_Id + "')," + "FOREIGN KEY ('"
					+ Dp_Id + "') REFERENCES '" + TABLE_Dp + "',"
					+ "FOREIGN KEY ('" + Bl_Id + "') REFERENCES '" + TABLE_Bl
					+ "'," + "FOREIGN KEY ('" + Fl_Id + "') REFERENCES '"
					+ TABLE_Fl + "'," + "FOREIGN KEY ('" + Rm_Id
					+ "') REFERENCES '" + TABLE_Rm + "'," + "FOREIGN KEY ('"
					+ Rmstd_Id + "') REFERENCES '" + TABLE_Rmstd + "')";
			break;
		case TABLE_Em:
			r = "CREATE TABLE '" + TABLE_Em + "' (" + "'" + Em_Id
					+ "' varchar(50) NOT NULL," + "'" + WWW_User
					+ "' varchar(50) NOT NULL," + "'" + Bl_Id
					+ "' varchar(50) NOT NULL," + "'" + Fl_Id
					+ "' varchar(50) NOT NULL," + "'" + Rm_Id
					+ "' varchar(50) NOT NULL," + "'" + Dp_Id
					+ "' varchar(50) NOT NULL," + "'" + Name
					+ "' varchar(50) DEFAULT NULL," + "'" + Telephone
					+ "' int(15) NOT NULL DEFAULT '0'," + "'" + Email
					+ "' varchar(50) DEFAULT NULL," + "PRIMARY KEY ('" + Em_Id
					+ "')," + "FOREIGN KEY ('" + WWW_User + "') REFERENCES '"
					+ WWW_User + "'," + "FOREIGN KEY ('" + Dp_Id
					+ "') REFERENCES '" + TABLE_Dp + "'," + "FOREIGN KEY ('"
					+ Bl_Id + "') REFERENCES '" + TABLE_Bl + "',"
					+ "FOREIGN KEY ('" + Fl_Id + "') REFERENCES '" + TABLE_Fl
					+ "'," + "FOREIGN KEY ('" + Rm_Id + "') REFERENCES '"
					+ TABLE_Rm + "')";
			break;
		}
		return r;
	}
}