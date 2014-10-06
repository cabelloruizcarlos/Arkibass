package com.arkibass;

import java.util.List;

import org.brickred.socialauth.Photo;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;

import com.android.volley.RequestQueue;

import com.arkibass.model.DBDefinition;
import com.arkibass.model.AppInfo;
import com.arkibass.util.DataBaseHelper;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText etLogin, etPass;
	Button btnLogin;
	DataBaseHelper db;
	CheckBox chkRemember;
	String txtLogin, txtPass;
	Boolean check;

	RequestQueue reqQueue;
	DBDefinition dbdefinition;
	AppInfo appinfo = new AppInfo();
	
	// SocialAuth Component
	SocialAuthAdapter adapter;
	Profile profileMap;
	List<Photo> photosList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().hide();
		
		dbdefinition = new DBDefinition();
		Button share = (Button) findViewById(R.id.sharebutton);

		share.setBackgroundResource(R.drawable.button_gradient);
				
		if (appinfo.hasActiveConnection(this)){	
			// get Instance of Database Helper and Model
			db = new DataBaseHelper(this);
							
			// Add it to Library
			adapter = new SocialAuthAdapter(new ResponseListener());

			// Add providers
//			adapter.addProvider(Provider.GOOGLEPLUS, R.drawable.googleplus);
			adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
			adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
			adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
//			adapter.addProvider(Provider.YAHOO, R.drawable.yahoo);
//			adapter.addProvider(Provider.YAMMER, R.drawable.yammer);

			// Providers require setting user call Back url
			adapter.addCallBack(Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
//			adapter.addCallBack(Provider.YAMMER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");

			// Enable Provider
			adapter.enable(share);
			
			// Get References of Views
			etLogin = (EditText) findViewById(R.id.editTextLogin);
			etPass = (EditText) findViewById(R.id.editTextPassword);
			btnLogin = (Button) findViewById(R.id.buttonSignIn);
			
			btnLogin.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					appinfo = new AppInfo(etLogin.getText().toString().trim(),
							etPass.getText().toString().trim());
					appinfo.setRole(db.getRole(appinfo.login));
	
					((AppInfo) getApplication()).setLogin(appinfo.login);
					((AppInfo) getApplication()).setPass(appinfo.pass);
					((AppInfo) getApplication()).setRole(appinfo.afm_role);
	
					// fetch the Password from the server for respective user name
					String storedPassword = db.getCredentials(appinfo.login);
	
					// check if the Stored password matches with Password entered by
					// the user
					if (appinfo.pass.equals(storedPassword)) {
						Intent i = new Intent(getApplicationContext(),
								MainActivity.class);
	
						startActivity(i);
						finish();
	
					} else {
						Toast.makeText(LoginActivity.this,
								"User Name or Password does not match",
								Toast.LENGTH_LONG).show();
					}
				}
			});
			
		}else{
			Toast.makeText(this, "You need wifi conection to run the app", Toast.LENGTH_LONG).show();
			finish();
		}
	}
	/**
	 * Listens Response from Library
	 * 
	 */

	private final class ResponseListener implements DialogListener {
		@Override
		public void onComplete(Bundle values) {

			Log.d("ShareButton", "Authentication Successful");

			// Get name of provider after authentication
			final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
			Log.d("ShareButton", "Provider Name = " + providerName);
			Toast.makeText(LoginActivity.this, providerName + " connected", Toast.LENGTH_LONG).show();

			// Login by third parties by default are Assistant for the app
			((AppInfo) getApplication()).setLogin("Assistant");
			((AppInfo) getApplication()).setPass("tae");
			((AppInfo) getApplication()).setRole("AFMAssistant");
			
			Intent i = new Intent(getApplicationContext(),
					MainActivity.class);

			startActivity(i);
			finish();
		}
		@Override
		public void onError(SocialAuthError error) {
			Log.d("ShareButton", "Authentication Error: " + error.getMessage());
		}

		@Override
		public void onCancel() {
			Log.d("ShareButton", "Authentication Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Share-Button", "Dialog Closed by pressing Back Key");
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
}
