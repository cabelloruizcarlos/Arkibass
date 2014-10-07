/**
 * 
 */
package com.arkibass.reports;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.arkibass.R;
import com.arkibass.model.RowItem;
import com.arkibass.util.DataBaseHelper;
import com.arkibass.util.ListReportAdapter;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author Carlos
 * 
 */
public class RoomsbyBuildingFloor extends Fragment implements OnItemSelectedListener{

	private View rootView;
	private ListView lv;
	private List<RowItem> rowItems;
	private JSONArray data;
	private DataBaseHelper db;

	private String[] names = null;
	private String[] descriptions = null;
	private String[] departments = null;
	private String[] standards = null;
	private String[] employees = null;
	private String[] telephones = null;
		
	public RoomsbyBuildingFloor(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.roomsbybuildingfloor_layout, container,false);
		
		// Spinner filter for the floors
		Spinner spinner = (Spinner) rootView.findViewById(R.id.floorSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.floors_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(spinnerAdapter);
		
		spinner.setOnItemSelectedListener(this);
		
		lv = (ListView) rootView.findViewById(R.id.rmidbyblidflidList);
		rowItems = new ArrayList<RowItem>();
		
		db = new DataBaseHelper(getActivity());
		
		setListView("Fl00");		
		
		return rootView;
	}

	private void setListView(String floor){
		data = db.RoomsbyBuildingFloor(floor);
		rowItems.clear();
		
		if (data.length() > 0){
			names = new String[data.length()];
			descriptions = new String[data.length()];
			departments = new String[data.length()];
			standards = new String[data.length()];
			employees = new String[data.length()];
			telephones = new String[data.length()];
				
			for (int i = 0; i < data.length(); ++i) {
			    try {
			    	JSONObject c;
			    	c = data.getJSONObject(i);
			    	
				    names[i] = c.getString("CEU-" + floor);
				    descriptions[i] = c.getString("name");
				    departments[i] = c.getString("dp_id");
				    standards[i] = c.getString("rmstd_id");
				    employees[i] = c.getString("count_em");
				    telephones[i] = c.getString("telephone");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 // Populate the List
			    RowItem item = new RowItem("buildingfloor",names[i], descriptions[i], departments[i],standards[i], employees[i], telephones[i]);
				rowItems.add(item);
			}
			// Set the adapter on the ListView
			ListReportAdapter adapter = new ListReportAdapter(
					rootView.getContext(), R.layout.roomsbybuildingfloor_row, rowItems);
			adapter.notifyDataSetChanged();
			lv.setAdapter(adapter);
		}else{
			Toast.makeText(getActivity(), "There is no data found", Toast.LENGTH_LONG).show();
		}
		
		lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		String floor = "";
		switch (parent.getItemAtPosition(position).toString()){
			
		case "Ground Floor":
			floor = "Fl00";
			break;
		case "First Floor":
			floor = "Fl01";
			break;
		case "Second Floor":
			floor = "Fl02";
			break;		
		default:
			break;
		}
		setListView(floor);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
