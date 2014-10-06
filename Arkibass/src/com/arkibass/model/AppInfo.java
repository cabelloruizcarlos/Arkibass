package com.arkibass.model;

import com.arkibass.reports.LocateRoom;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

public class AppInfo extends Application {

	// Office
//	public  final static String SERVER_URL = "http://172.26.66.59/arkibass/GCM/register.php"; 
//	// Home
//	public  final static String SERVER_URL = "http://192.168.233.1/arkibass/GCM/register.php";
	// Ñeko´s home
//	public  final static String SERVER_URL = "http://192.168.1.33/arkibass/GCM/register.php";
//	
//    // Google project id
//    public final static String SENDER_ID = "582873308968"; 

//    /**
//     * Tag used on log messages.
//     */
//    public final static String TAG = "AndroidHive GCM";
//
//    public final static String DISPLAY_MESSAGE_ACTION =
//            "com.androidhive.pushnotifications.DISPLAY_MESSAGE";
//
//    public final static String EXTRA_MESSAGE = "message";
	
	public String login;
	public String pass;
	public String afm_role;
	
	public AppInfo() {
	}

	public AppInfo(String txtLogin, String txtPass, String txtRole) {
		// TODO Auto-generated constructor stub
		login = txtLogin;
		pass = txtPass;
		afm_role = txtRole;
	}

	public AppInfo(String txtLogin, String txtPass) {
		// TODO Auto-generated constructor stub
		login = txtLogin;
		pass = txtPass;
		afm_role = "";
	}

	public void setLogin(String txtUser) {
		// TODO Auto-generated method stub
		login = txtUser;
	}

	public void setPass(String txtPass) {
		// TODO Auto-generated method stub
		pass = txtPass;
	}

	public void setRole(String role) {
		// TODO Auto-generated method stub
		afm_role = role;
	}

	public String getLogin() {
		// TODO Auto-generated method stub
		return login;
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return afm_role;
	}

	/**
	 * This method checks if there's a connected device for the given context.
	 * 
	 * @param context
	 *            is the context to be used.
	 * @return true when the user has an active connection. False otherwise.
	 */
	public boolean hasActiveConnection(Context context) {
		// checking network connection
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean haveConnectedWifi = false;
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
		}
		return haveConnectedWifi;
	}
//	
//	   /**
//     * Notifies UI to display a message.
//     * <p>
//     * This method is defined in the common helper because it's used both by
//     * the UI and the background service.
//     *
//     * @param context application's context.
//     * @param message message to be displayed.
//     */
//    public static void displayMessage(Context context, String message) {
//        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
//        intent.putExtra(EXTRA_MESSAGE, message);
//        context.sendBroadcast(intent);
//    }

	public String getSize(LocateRoom locateRoom, String mode) {
		// TODO Auto-generated method stub
		DisplayMetrics opt = locateRoom.getResources().getDisplayMetrics();
		if (mode.contains("landscape")) {
			if (opt.heightPixels < 1080) {
				return "Large";
			} else {
				return "Tablet";
			}
		} else {
			if (opt.widthPixels < 1080) {
				return "Large";
			} else {
				return "Tablet";
			}
		}

	}

	public String getRotation(Context context) {
		int rotation = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getOrientation();
		switch (rotation) {

		case Surface.ROTATION_0:
			return "portrait";
		case Surface.ROTATION_90:
			return "landscape";
		case Surface.ROTATION_180:
			return "reverse portrait";
		default:
			return "reverse landscape";
		}
	}

	public Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {
			Matrix m = new Matrix();

			m.setRotate(degrees, (float) b.getWidth() / 2,
					(float) b.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
						b.getHeight(), m, true);
				if (b != b2) {
					// This is because Picasso shows this error: Target callback
					// must not recycle bitmap
					// b.recycle();
					b = b2;
				}
			} catch (OutOfMemoryError ex) {
				throw ex;
			}
		}
		return b;
	}
}
