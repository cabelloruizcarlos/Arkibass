package com.arkibass;

import java.util.ArrayList;
import java.util.List;

import com.arkibass.model.DBDefinition;
import com.arkibass.model.AppInfo;
import com.arkibass.reports.HomeScreen;
import com.arkibass.reports.LocateRoom;
import com.arkibass.reports.LoginList;
import com.arkibass.reports.RolesList;
import com.arkibass.reports.RoomsbyBuildingFloor;
import com.arkibass.reports.RoomsbyDepartment;
import com.arkibass.reports.RoomsbyStandard;
import com.arkibass.util.AlertDialogManager;
import com.arkibass.util.CustomDrawerAdapter;
import com.arkibass.util.DataBaseHelper;
import com.arkibass.util.DrawerItem;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private CustomDrawerAdapter adapter;

	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;
	// Alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();

	List<DrawerItem> dataList;
	DataBaseHelper db;
	DBDefinition dbDefinition;
	AppInfo appInfo;
	String role;

	TextView lblMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().show();
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#00CCCC")));
		getActionBar().setTitle(R.string.app_name_caps);

//		lblMessage = (TextView) findViewById(R.id.lblMessage);

		db = new DataBaseHelper(this);
		dbDefinition = new DBDefinition();
		appInfo = (AppInfo) getApplication();
		role = appInfo.getRole();

		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Add Drawer Item to dataList
		if (!role.equals(dbDefinition.SYSTEM_ADMINISTRATOR)) {

			dataList.add(new DrawerItem("Space Management")); // adding a
			// header to
			// the list
			if (!role.equals(dbDefinition.HR_ASISTANT)) {
				dataList.add(new DrawerItem("Room Occupancy")); // adding a
																// header to the
																// list

				dataList.add(new DrawerItem("Reports")); // adding a header to
															// the list
				dataList.add(new DrawerItem("Rooms Occupied by Building&Floor",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Rooms Occupied by Department",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Rooms Occupied by Standard",
						R.drawable.ic_menu));

				dataList.add(new DrawerItem("Room Inventory")); // adding a
																// header to the
																// list
				dataList.add(new DrawerItem("Locate Room", R.drawable.ic_menu));

				dataList.add(new DrawerItem("Reports")); // adding a header to
															// the list
				dataList.add(new DrawerItem("Rooms by Building&Floor",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Rooms by Department",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Rooms by Standard",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Rooms Standards by B&F&D",
						R.drawable.ic_menu));
			}
			if (role.equals(dbDefinition.HR_MANAGER)) {
				dataList.add(new DrawerItem("Department Inventory")); // adding
																		// a
																		// header
																		// to
																		// the
																		// list
				dataList.add(new DrawerItem("Allocate Department",
						R.drawable.ic_menu));

				dataList.add(new DrawerItem("Reports")); // adding a header to
															// the list
				dataList.add(new DrawerItem("Department by Building&Floor",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Department by Room",
						R.drawable.ic_menu));
			}
			if ((!role.equals(dbDefinition.HR_ASISTANT))
					&& (!role.equals(dbDefinition.ASSISTANT))) {


				dataList.add(new DrawerItem("Personnel Occupancy")); // adding a
																		// header
																		// to
																		// the
																		// list
				dataList.add(new DrawerItem("Allocate Employee",
						R.drawable.ic_menu));

				dataList.add(new DrawerItem("Reports")); // adding a header to
															// the list
				dataList.add(new DrawerItem("Free Rooms by Building&Floor",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Free Rooms by Department",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Free Rooms by Standard",
						R.drawable.ic_menu));
			}

			if (!role.equals(dbDefinition.ASSISTANT)) {
				dataList.add(new DrawerItem("Personnel Inventory"));// adding a
																	// header to
																	// the list

				dataList.add(new DrawerItem("Manage Employee"));// adding a
																// header to the
																// list

				if (role.equals(dbDefinition.HR_MANAGER)) {
					dataList.add(new DrawerItem("Create Employee",
							R.drawable.ic_menu));
				}
				dataList.add(new DrawerItem("Locate Employee",
						R.drawable.ic_menu));

				dataList.add(new DrawerItem("Reports"));// adding a header to
														// the list
				dataList.add(new DrawerItem("Employee by Building&Floor",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Employee by Department",
						R.drawable.ic_menu));
				dataList.add(new DrawerItem("Employee by Standard",
						R.drawable.ic_menu));
			}
		} else {
			dataList.add(new DrawerItem("System Administrator")); // adding a
																	// header to
																	// the list

			dataList.add(new DrawerItem("Manage Roles")); // adding a header to
															// the list
			dataList.add(new DrawerItem("Roles", R.drawable.ic_menu));

			dataList.add(new DrawerItem("Manage Login")); // adding a header to
															// the list
			dataList.add(new DrawerItem("Create Login", R.drawable.ic_menu));
			dataList.add(new DrawerItem("Logins", R.drawable.ic_menu));
		}

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		SelectItem("Allocate Employee", 3);

//		// Check if GCM configuration is set
//		if (AppInfo.SERVER_URL == null || AppInfo.SENDER_ID == null
//				|| AppInfo.SERVER_URL.length() == 0
//				|| AppInfo.SENDER_ID.length() == 0) {
//			// GCM sernder id / server url is missing
//			alert.showAlertDialog(this, "Configuration Error!",
//					"Please set your Server URL and GCM Sender ID", false);
//			// stop executing code by return
//			return;
//		}

		// Make sure the device has the proper dependencies.
//		GCMRegistrar.checkDevice(this);
//
//		// Make sure the manifest was properly set - comment out this line
//		// while developing the app, then uncomment it when it's ready.
//		GCMRegistrar.checkManifest(this);

//		registerReceiver(mHandleMessageReceiver, new IntentFilter(
//				AppInfo.DISPLAY_MESSAGE_ACTION));
//
//		// Get GCM registration id
//		final String regId = GCMRegistrar.getRegistrationId(this);
//
//		// Check if regid already presents
//		if (regId.equals("")) {
//			// Registration is not present, register now with GCM
//			GCMRegistrar.register(this, AppInfo.SENDER_ID);
//		} else {
//			// Device is already registered on GCM
//			if (GCMRegistrar.isRegisteredOnServer(this)) {
//				// Skips registration.
//				Toast.makeText(getApplicationContext(),
//						"Already registered with GCM", Toast.LENGTH_LONG)
//						.show();
//			} else {
//				// Try to register again, but not in the UI thread.
//				// It's also necessary to cancel the thread onDestroy(),
//				// hence the use of AsyncTask instead of a raw thread.
//				final Context context = this;
//				mRegisterTask = new AsyncTask<Void, Void, Void>() {
//
//					@Override
//					protected Void doInBackground(Void... params) {
//						// Register on our server
//						// On server creates a new user
//						ServerUtilities.register(context, appInfo.getLogin(), "cabello.ruiz@hotmail.es" , regId);
//						return null;
//					}
//
//					@Override
//					protected void onPostExecute(Void result) {
//						mRegisterTask = null;
//					}
//
//				};
//				mRegisterTask.execute(null, null, null);
//			}
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void SelectItem(String opt, int position) {

		Fragment fragment = null;
		switch (opt) {

		case "Rooms by Department":
			fragment = new RoomsbyDepartment();
			break;
		case "Rooms by Building&Floor":
			fragment = new RoomsbyBuildingFloor();
			break;
		case "Locate Employee":
			fragment = new HomeScreen();
			break;
		case "Locate Room":
			fragment = new LocateRoom();
			break;
		case "Allocate Employee":
			fragment = new HomeScreen();
			break;
		case "Free Rooms by Building&Floor":
			fragment = new HomeScreen();
			break;
		case "Free Rooms by Department":
			fragment = new HomeScreen();
			break;
		case "Free Rooms by Standard":
			fragment = new HomeScreen();
			break;
		case "Create Employee":
			fragment = new LocateRoom();
			break;
		case "Employee by Building&Floor":
			fragment = new HomeScreen();
			break;
		case "Employee by Department":
			fragment = new HomeScreen();
			break;
		case "Employee by Standard":
			fragment = new HomeScreen();
			break;
		case "Rooms Occupied by Building&Floor":
			fragment = new HomeScreen();
			break;
		case "Rooms Occupied by Department":
			fragment = new HomeScreen();
			break;
		case "Rooms Occupied by Standard":
			fragment = new HomeScreen();
			break;
		case "Rooms by Standard":
			fragment = new RoomsbyStandard();
			break;
		case "Rooms Standards by B&F&D":
			fragment = new HomeScreen();
			break;
		case "Allocate Department":
			fragment = new HomeScreen();
			break;
		case "Department by Building&Floor":
			fragment = new HomeScreen();
			break;
		case "Department by Room":
			fragment = new HomeScreen();
			break;
		case "Roles":
			fragment = new RolesList();
			break;
		case "Create Login":
			fragment = new HomeScreen();
			break;
		case "Logins":
			fragment = new LoginList();
			break;
		default:
			fragment = new HomeScreen();
			break;
		}
		if (fragment != null) {
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();

			mDrawerList.setItemChecked(position, true);
			setTitle(dataList.get(position).getItemName());

			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		Intent i;
		switch (item.getItemId()) {
		case R.id.logout:
			i = new Intent(getApplicationContext(), LoginActivity.class);

			startActivity(i);
			finish();
			break;
		case R.id.maps:
			i = new Intent(getApplicationContext(), MapsActivity.class);

			startActivity(i);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (dataList.get(position).getTitle() == null) {
				SelectItem(dataList.get(position).getItemName(), position);
			}

		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
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

//	/**
//	 * Receiving push messages
//	 * */
//	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			String newMessage = intent.getExtras().getString(
//					AppInfo.EXTRA_MESSAGE);
//			// Waking up mobile if it is sleeping
//			WakeLocker.acquire(getApplicationContext());
//
//			/**
//			 * Take appropriate action on this message depending upon your app
//			 * requirement For now i am just displaying it on the screen
//			 * */
//
//			// Showing received message
//			lblMessage.append(newMessage + "\n");
//			Toast.makeText(getApplicationContext(),
//					"New Message: " + newMessage, Toast.LENGTH_LONG).show();
//
//			// Releasing wake lock
//			WakeLocker.release();
//		}
//	};
	
	
}
