package com.arkibass;

import com.arkibass.util.DataBaseHelper;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MapsActivity extends Activity implements OnMarkerClickListener{

	private DataBaseHelper dbHelper = new DataBaseHelper(this);
		
	Button btnBack,btnOk;
	TextView tvName,tvAddress,tvCity,tvState,tvCountry;
	
	// Google Map
	private GoogleMap googleMap;
	
	public MapsActivity(){};
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps_layout);
		
		getActionBar().show();
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#00CCCC")));
		
		try {
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			MapsInitializer.initialize(getApplicationContext());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onStart()
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();		

		String[] result = dbHelper.getBuildingLocations();
		Double[] buildingLocations = new Double[] {
				Double.parseDouble(result[1]),
				Double.parseDouble(result[2]) };

		// lets place the markers
		// In the demo there is only one building

		// Adding a marker
		MarkerOptions marker = new MarkerOptions().position(
				new LatLng(buildingLocations[0], buildingLocations[1]));
		marker.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
		googleMap.addMarker(marker);
		googleMap.setOnMarkerClickListener(this);

		// Move the camera to last building with a zoom level
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(buildingLocations[0],
						buildingLocations[1])).zoom(13).build();

		googleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
		EasyTracker.getInstance(this).activityStart(this);
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mapsFragment)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(this.getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		initilizeMap();
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.maps_dialog);
		dialog.setTitle(R.string.maps);
		
		btnBack = (Button) dialog.findViewById(R.id.mapsDialogButtonBack);
		btnOk = (Button) dialog.findViewById(R.id.mapsDialogButtonOK);
		tvName = (TextView) dialog.findViewById(R.id.mapsDialogName);
		tvAddress = (TextView) dialog.findViewById(R.id.mapsDialogAddress);
		tvCity = (TextView) dialog.findViewById(R.id.mapsDialogCity);
		tvState = (TextView) dialog.findViewById(R.id.mapsDialogState);
		tvCountry = (TextView) dialog.findViewById(R.id.mapsDialogCountry);
		
		String[] data = dbHelper.getBuildingDetails();
		
		tvName.setText(data[0]);
		tvAddress.setText(data[1]);
		tvCity.setText(data[2]);
		tvState.setText(data[3]);
		tvCountry.setText(data[4]);
		
		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	dialog.dismiss();}
		});
		
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	finish();}
		});		
		
		if (arg0.getId().equals("m0")){	
			dialog.show();
			return true;
		}		
		return false;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	
}