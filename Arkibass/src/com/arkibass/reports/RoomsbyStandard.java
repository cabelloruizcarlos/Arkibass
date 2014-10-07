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
public class RoomsbyStandard extends Fragment implements OnItemSelectedListener{

	private View rootView;
	private ListView lv;
	private List<RowItem> rowItems;
	private JSONArray data;
	private DataBaseHelper db;

	private String[] titles = null;
	private String[] descriptions = null;
	private String[] departments = null;
	private String[] employees = null;
	private String[] telephones = null;
		
	public RoomsbyStandard(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.roomsbystandard_layout, container,false);
		
		// Intialize and set the Action Bar to Holo Blue
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#00CCCC")));

		// Spinner filter for the floors
		Spinner spinner = (Spinner) rootView.findViewById(R.id.standardSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.standard_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(spinnerAdapter);
		
		spinner.setOnItemSelectedListener(this);
		
		lv = (ListView) rootView.findViewById(R.id.roomsbystandardList);
		rowItems = new ArrayList<RowItem>();
		
		db = new DataBaseHelper(getActivity());
		
		setListView("Cafe");		
		
		return rootView;
	}

	private void setListView(String standard){
		data = db.RoomsbyStandard(standard);
		rowItems.clear();
		
		if (data.length() > 0){
			titles = new String[data.length()];
			descriptions = new String[data.length()];
			departments = new String[data.length()];
			employees = new String[data.length()];
			telephones = new String[data.length()];
				
			for (int i = 0; i < data.length(); ++i) {
			    try {
			    	JSONObject c;
			    	c = data.getJSONObject(i);
			    	
				    titles[i] = c.getString("room");
				    descriptions[i] = c.getString("name");
				    departments[i] = c.getString("dp_id");
				    employees[i] = c.getString("count_em");
				    telephones[i] = c.getString("telephone");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 // Populate the List
			    RowItem item = new RowItem();
			    item.RowItemSt("standard",titles[i], descriptions[i], departments[i], employees[i], telephones[i]);
				rowItems.add(item);
			}
			// Set the adapter on the ListView
			ListReportAdapter adapter = new ListReportAdapter(
					rootView.getContext(), R.layout.roomsbystandard_row, rowItems);
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
		setListView(parent.getItemAtPosition(position).toString());
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
