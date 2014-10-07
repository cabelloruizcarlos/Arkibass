package com.arkibass.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.arkibass.R;
import com.arkibass.model.DBDefinition;
import com.arkibass.model.RoomLocation;
import com.arkibass.model.RowItem;

import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelper {

	// Database Structure Definition
	private DBDefinition dbDefinition;
	private JSONParser jParser;

	public DataBaseHelper(Context context) {
		super();

		this.dbDefinition = new DBDefinition();
		this.jParser = new JSONParser();
	}

	public String getCredentials(String userName) {
		String str_wwww_pass = "";
		JSONObject json = jParser.getJSONFromUrlByGet(dbDefinition.URL_Login
				+ "?login=" + userName);
		try {
			str_wwww_pass = json.getString(dbDefinition.WWW_Pass);
		} catch (JSONException e) {
			Log.e("Get Credentials", e.getMessage());
		}

		return str_wwww_pass;
	}

	public String getRole(String userName) {

		String str_afm_role = "";
		JSONObject json = jParser.getJSONFromUrlByGet(dbDefinition.URL_Login
				+ "?login=" + userName);

		try {
			str_afm_role = json.getString(dbDefinition.AFM_Role);
		} catch (JSONException e) {
			Log.e("Get Credentials", e.getMessage());
		}

		return str_afm_role;
	}

	public String[][] getRoomNames() {
		// TODO Auto-generated method stub
		JSONArray json = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "roomnames.php");
		String[][] roominfo = null;

		try {
			if (json.length() > 0) {

				String[][] data = new String[2][json.length()];
				for (int i = 0; i < json.length(); i++) {
					JSONObject item = json.getJSONObject(i);
					data[0][i] = item.getString("name");
					data[1][i] = item.getString("rm_id");
				}
				roominfo = data;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return roominfo;
	}

	public String getRoomDetails(String rm_id) {

		String roominfo = "";
		JSONObject json = jParser.getJSONFromUrlByGet(dbDefinition.URL
				+ "roomsdetails.php?room=" + rm_id);

		try {
			roominfo = json.getString("description");
		} catch (JSONException e) {
			Log.e("Get Room Details", e.getMessage());
		}

		return roominfo;
	}

	public RoomLocation getRoomLocation(String room) {

		RoomLocation roomLocation = new RoomLocation();
		JSONObject json = jParser.getJSONFromUrlByGet(dbDefinition.URL
				+ "roomslocation.php?room=" + room);

		try {
			roomLocation.setRm_id(room);
			roomLocation.setHeight(json.getString(dbDefinition.Height));
			roomLocation.setWidth(json.getString(dbDefinition.Width));
			roomLocation.setMarginBottom(json
					.getString(dbDefinition.MarginBottom));
			roomLocation.setMarginLeft(json.getString(dbDefinition.MarginLeft));
			roomLocation.setMarginRight(json
					.getString(dbDefinition.MarginRight));
			roomLocation.setMarginTop(json.getString(dbDefinition.MarginTop));

		} catch (JSONException e) {
			Log.e("Get Room Location", e.getMessage());
		}

		return roomLocation;
	}

	/*
	 * REPORTS
	 */
	public JSONArray RoomsbyBuildingFloor(String floor) {

		JSONArray result = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "roomsbybuildingfloor.php?floor=" + floor);

		return result;
	}

	public JSONArray RoomsbyDepartment(String department) {

		JSONArray result = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "roomsbydepartment.php?department=" + department);

		return result;
	}

	public JSONArray RoomsbyStandard(String standard) {

		JSONArray result = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "roomsbystandard.php?standard=" + standard);

		return result;
	}
	
	public String[] getBuildingLocations() {
		// TODO Auto-generated method stub
		JSONArray json = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "buildinglocations.php");
		String[] buildinginfo = new String[3];

		try {
			if (json.length() > 0) {

				JSONObject item = json.getJSONObject(0);
				buildinginfo[0] = item.getString("bl_id");
				buildinginfo[1] = item.getString("latitude");
				buildinginfo[2] = item.getString("longitude");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return buildinginfo;
	}

	public String[] getBuildingDetails() {

		String[] roominfo = new String[5];
		JSONObject json = jParser.getJSONFromUrlByGet(dbDefinition.URL + "buildingdetails.php");

		try {
			roominfo[0] = json.getString("name");
			roominfo[1] = json.getString("address");
			roominfo[2] = json.getString("city");
			roominfo[3] = json.getString("state");
			roominfo[4] = json.getString("country");

		} catch (JSONException e) {
			Log.e("Get Building Details", e.getMessage());
		}

		return roominfo;
	}
	
	public String[][] getRolesList() {
		// TODO Auto-generated method stub
		JSONArray json = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "afm_roles.php");
		String[][] data = null;

		try {
			if (json.length() > 0) {

				data = new String[6][json.length()];
				for (int i = 0; i < json.length(); i++) {
					JSONObject item = json.getJSONObject(i);
					data[0][i] = item.getString("afm_role");
					data[1][i] = item.getString("name");
					data[2][i] = item.getString("afm_modules");
					data[3][i] = item.getString("afm_products");
					data[4][i] = item.getString("afm_activities");
					data[5][i] = item.getString("afm_tasks");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String[][] getLoginsList() {
		// TODO Auto-generated method stub
		JSONArray json = jParser.getJSONArrayFromUrlByGet(dbDefinition.URL
				+ "afm_login.php");
		String[][] data = null;

		try {
			if (json.length() > 0) {

				data = new String[3][json.length()];
				for (int i = 0; i < json.length(); i++) {
					JSONObject item = json.getJSONObject(i);
					data[0][i] = item.getString("www_user");
					data[1][i] = item.getString("www_pass");
					data[2][i] = item.getString("afm_role");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
}
