package com.arkibass.reports;

import com.arkibass.R;
import com.arkibass.model.AppInfo;
import com.arkibass.model.DBDefinition;
import com.arkibass.util.AlertDialogManager;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//public class HomeScreen extends Fragment implements Target, OnClickListener {
public class HomeScreen extends Fragment implements Target {

	DBDefinition dbDefinition = new DBDefinition();
	AppInfo appInfo;
	View rootView;

	Button btnGCM;
	TextView lblMessage;
	AsyncTask<Void, Void, Void> mRegisterTask;

	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();

	public HomeScreen() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.homescreen_layout, container,
				false);

		Picasso.with(getActivity())
				.load(dbDefinition.URL_Drawings + "Planos2.jpg").into(this);

//		appInfo = (AppInfo) getActivity().getApplicationContext();

		return rootView;
	}

	@Override
	public void onBitmapFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBitmapLoaded(Bitmap arg0, LoadedFrom arg1) {
		// TODO Auto-generated method stub
		ImageView iV = (ImageView) rootView.findViewById(R.id.imageView1);
		iV.setImageDrawable(new BitmapDrawable(getResources(), arg0));
	}

//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//
//		// Make sure the device has the proper dependencies.
//		// GCMRegistrar.checkDevice(getActivity());
//		//
//		// // Make sure the manifest was properly set - comment out this line
//		// // while developing the app, then uncomment it when it's ready.
//		// GCMRegistrar.checkManifest(getActivity());
//		//
//		getActivity().registerReceiver(mHandleMessageReceiver,
//				new IntentFilter(AppInfo.DISPLAY_MESSAGE_ACTION));
//
//		// Get GCM registration id
//		final String regId = GCMRegistrar.getRegistrationId(getActivity());
//
//		// Check if regid already presents
//		if (regId.equals("")) {
//			// Registration is not present, register now with GCM
//			GCMRegistrar.register(getActivity(), AppInfo.SENDER_ID);
//		} else {
//			// Device is already registered on GCM
//			if (GCMRegistrar.isRegisteredOnServer(getActivity())) {
//				// Skips registration.
//				Toast.makeText(getActivity(), "Already registered with GCM",
//						Toast.LENGTH_LONG).show();
//			} else {
//				// Try to register again, but not in the UI thread.
//				// It's also necessary to cancel the thread onDestroy(),
//				// hence the use of AsyncTask instead of a raw thread.
//				final Context context = getActivity();
//				mRegisterTask = new AsyncTask<Void, Void, Void>() {
//
//					@Override
//					protected Void doInBackground(Void... params) {
//						// Register on our server
//						// On server creates a new user
//						ServerUtilities.register(context, appInfo.getLogin(),
//								"carlos.cabello@hotmail.es", regId);
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
//
//	}
//
//	/**
//	 * Receiving push messages
//	 * */
//	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			String newMessage = intent.getExtras().getString(
//					AppInfo.EXTRA_MESSAGE);
//			// Waking up mobile if it is sleeping
//			WakeLocker.acquire(getActivity());
//
//			/**
//			 * Take appropriate action on this message depending upon your app
//			 * requirement For now i am just displaying it on the screen
//			 * */
//
//			// Showing received message
//			lblMessage.append(newMessage + "\n");
//			Toast.makeText(getActivity(), "New Message: " + newMessage,
//					Toast.LENGTH_LONG).show();
//
//			// Releasing wake lock
//			WakeLocker.release();
//		}
//	};
//
//	@Override
//	public void onDestroy() {
//		if (mRegisterTask != null) {
//			mRegisterTask.cancel(true);
//		}
//		try {
//			getActivity().unregisterReceiver(mHandleMessageReceiver);
//			GCMRegistrar.onDestroy(getActivity());
//		} catch (Exception e) {
//			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
//		}
//		super.onDestroy();
//	}
}
