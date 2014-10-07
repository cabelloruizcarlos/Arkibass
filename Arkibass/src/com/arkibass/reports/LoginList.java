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
import com.arkibass.model.LoginRowItem;
import com.arkibass.model.RoleRowItem;
import com.arkibass.model.RowItem;
import com.arkibass.util.DataBaseHelper;
import com.arkibass.util.ListReportAdapter;
import com.arkibass.util.LoginListReportAdapter;
import com.arkibass.util.RolesListReportAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * @author Carlos
 *
 */
public class LoginList extends Fragment {

	private View rootView;
	private ListView lv;
	private List<LoginRowItem> rowItems;
	private String[][] data;
	private DataBaseHelper db;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		rootView = inflater.inflate(R.layout.loginlist_layout, container,false);
		
		lv = (ListView) rootView.findViewById(R.id.loginlistList);
		rowItems = new ArrayList<LoginRowItem>();
		
		db = new DataBaseHelper(getActivity());
		
		setListView();		
		
		return rootView;
	}
	
	private void setListView(){
		data = db.getLoginsList();
		rowItems.clear();
		
		if (data[1].length > 0){
							
			for (int i = 0; i < data[1].length; ++i) {

			 // Populate the List
			    LoginRowItem item = new LoginRowItem(data[0][i],data[1][i], data[2][i]);
				rowItems.add(item);
			}
			// Set the adapter on the ListView
			LoginListReportAdapter adapter = new LoginListReportAdapter(
					rootView.getContext(), R.layout.roleslist_row, rowItems);
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
	
}
