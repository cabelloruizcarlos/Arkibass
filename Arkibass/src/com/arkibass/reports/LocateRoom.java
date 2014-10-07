package com.arkibass.reports;

import com.arkibass.R;
import com.arkibass.model.AppInfo;
import com.arkibass.model.DBDefinition;
import com.arkibass.model.RoomLocation;
import com.arkibass.util.DataBaseHelper;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;
import android.app.Dialog;
import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.Toast;

public class LocateRoom extends Fragment implements OnClickListener, ToolTipView.OnToolTipViewClickedListener, Target{

	private View rootView;
	private Button btn,dialogOKButton,dialogCancelButton;	
	private EditText etDialog;
	private int width, height, marginLeft, marginTop, marginRight, marginBottom, resId, statusBar, actionBar, heightSize, widthSize;
	private String mode,room;
	private String[][] roomInfo;
	private Bitmap LBackGround,PBackGround;
	private boolean imageLoad,firstloadspinner;
		
	private DBDefinition dbdefinition = new DBDefinition();
	private RoomLocation roomLocation = new RoomLocation();
	private DataBaseHelper dbhelper = new DataBaseHelper(getActivity());
	private AppInfo appinfo;
	private Spinner spinner;

	// Color thing
	private final Handler handler = new Handler();
	private Boolean blue = false;
	
	private ToolTipRelativeLayout mToolTipFrameLayout;
	private ToolTipView mToolTipView;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.locateroom_layout, container,false);
		appinfo = (AppInfo) getActivity().getApplicationContext();
		
		imageLoad = false;
		// Set a dialog to make the user choose a room
		Picasso.with(getActivity()).load(dbdefinition.URL_Drawings + "fl00.jpg").into(this);
		

		final Dialog dialog = new Dialog(getActivity());
		dialog.setContentView(R.layout.roomslocation_dialog);
		firstloadspinner=true;
		dialog.setTitle("             Locate room");
		spinner = (Spinner) dialog.findViewById(R.id.dialogSpinner);
		dialogOKButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		dialogCancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);		
		roomInfo = dbhelper.getRoomNames();
		String[] data = new String[roomInfo[0].length];
		for (int i=0;i < roomInfo[0].length; i++)
			data[i] = roomInfo[0][i];
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,data);
		// Apply the adapter to the spinner
		spinner.setAdapter(spinnerAdapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position,
					long id) {
				if (!firstloadspinner){
					boolean gotcha = true;
					for (int i=0;(i< (roomInfo[0].length))&&gotcha;i++){
						if (roomInfo[0][i].equals(parent.getItemAtPosition(position).toString())){
							room = roomInfo[1][i];
							gotcha = false;
						}
					}
					if (gotcha){	room = "";}
					
				}else{ firstloadspinner=false;}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub				
			}
		});
		
		dialogOKButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (imageLoad)
				{ 
					WaitingForResponse(room,false);
					dialog.dismiss();
				}
			}
		});
		dialogCancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {	dialog.dismiss();}
		});		
		dialog.show();			

		return rootView;
	}

	private void WaitingForResponse(String room,boolean change){
		
		if ((room == null)|| room.equals("")){
			Toast.makeText(getActivity(), "Please write a room id", Toast.LENGTH_LONG).show();
		}else{
			/*
			 * To put the button in the right place with the right size I used:
			 * 
			 *  	getDisplayMetrics() to obtain the info about the whole screen
			 *  		opt.widthPixels to obtain the width of the screen
			 *  		heightSize have the height of the screen that is the result of substract the space of the Status bar and the Action bar
			 *  
			 *  I calculate the width and the height of the button in the same way and I apply the info to the button using the layout param named params
			 *   
			 *  The exactly value of the size and position of the button is calculated for all the screen´s size with this formula:
			 *  		 *  	(width of the screen * percentage of the screen) / 100 
			 *  	(height of the screen * percentage of the screen) / 100
			 *  
			 *  	Where the percentage is a value retrieve from the database and store in the object named roomLocation.
			 */
			roomLocation = dbhelper.getRoomLocation(room);
			if (roomLocation.getHeight() != null){
			
				mode = appinfo.getRotation(getActivity());
				DisplayMetrics opt = getActivity().getResources().getDisplayMetrics();
			
				resId = getActivity().getResources().getIdentifier("status_bar_height","dimen","android");
				statusBar = getResources().getDimensionPixelSize(resId);
				actionBar = getActivity().getActionBar().getHeight();			
				
			
				if ((mode.contains("portrait")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
						(mode.contains("landscape")&&appinfo.getSize(this,mode).equals("Tablet"))){
					heightSize = opt.heightPixels - statusBar - actionBar;
					widthSize = opt.widthPixels;
					
					width = (widthSize * Integer.parseInt(roomLocation.getWidth()))/100; 			
					height = (heightSize * Integer.parseInt(roomLocation.getHeight()))/100;
					marginLeft = (widthSize * Integer.parseInt(roomLocation.getMarginLeft()))/100;
					marginTop = (heightSize * Integer.parseInt(roomLocation.getMarginTop()))/100;
					marginRight = (widthSize * Integer.parseInt(roomLocation.getMarginRight()))/100;
					marginBottom = (heightSize * Integer.parseInt(roomLocation.getMarginBottom()))/100;
				}
				
				if ((mode.contains("landscape")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
						((mode.contains("portrait")&&appinfo.getSize(this,mode).equals("Tablet")))){
					heightSize = opt.heightPixels - statusBar - actionBar;
					widthSize = opt.widthPixels;
					
					width = (widthSize * Integer.parseInt(roomLocation.getHeight()))/100; 			
					height = (heightSize * Integer.parseInt(roomLocation.getWidth()))/100;
					marginLeft = (widthSize * Integer.parseInt(roomLocation.getMarginTop()))/100;
					marginTop = (heightSize * Integer.parseInt(roomLocation.getMarginRight()))/100;
					marginRight = (widthSize * Integer.parseInt(roomLocation.getMarginBottom()))/100;
					marginBottom = (heightSize * Integer.parseInt(roomLocation.getMarginLeft()))/100;
				}		
				
				LayoutParams params = new LayoutParams(width, height);
				btn = (Button) rootView.findViewById(R.id.button1);	
				
				btn.setBackgroundColor(Color.TRANSPARENT);
				params.setMargins(marginLeft, marginTop,marginRight, marginBottom);					
				btn.setLayoutParams(params);
				btn.setOnClickListener(this);
								
				mToolTipFrameLayout = (ToolTipRelativeLayout) rootView.findViewById(R.id.activity_main_tooltipframelayout);
					
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						addToolTipView(btn);
					}
				}, 700);
			}else {	Toast.makeText(getActivity(), "This space does not exist", Toast.LENGTH_LONG).show();}
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (blue){ btn.setBackgroundColor(Color.TRANSPARENT);blue = false;				
		}else{	btn.setBackgroundColor(getResources().getColor(R.color.logo));blue = true;}			

		// For the tooltip
		if (mToolTipView == null) {
            addToolTipView(v);
        } else {
            mToolTipView.remove();
            mToolTipView = null;
        }
	}
	
    private void addToolTipView(View btn) {
    	String description = dbhelper.getRoomDetails(roomLocation.getRm_id());
    	
        ToolTip toolTip = new ToolTip()
                .withText(description)
                .withColor(getResources().getColor(R.color.holo_green))
        		.withAnimationType(ToolTip.AnimationType.FROM_TOP);

        mToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, btn);
        mToolTipView.setOnToolTipViewClickedListener(this);
    }
    
	@Override
	public void onToolTipViewClicked(ToolTipView toolTipView) {
		// TODO Auto-generated method stub
		if (mToolTipView == toolTipView) {
            mToolTipView = null;
		}
	}
    
	private Drawable.Callback drawableCallback = new Drawable.Callback() {
		@Override
		public void invalidateDrawable(Drawable who) {
			getActivity().getActionBar().setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
			handler.postAtTime(what, when);
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
			handler.removeCallbacks(what);
		}
	};

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		RelativeLayout l = (RelativeLayout) rootView.findViewById(R.id.locateroomLayout);
		mode = appinfo.getRotation(getActivity());
		
		if ((mode.contains("landscape")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
				((mode.contains("portrait")&&appinfo.getSize(this,mode).equals("Tablet")))){
			l.setBackground(new BitmapDrawable(getResources(),LBackGround));
		}
		if ((mode.contains("portrait")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
				((mode.contains("landscape")&&appinfo.getSize(this,mode).equals("Tablet")))){
			l.setBackground(new BitmapDrawable(getResources(),PBackGround));
		}
		WaitingForResponse(room,true);
		if (mToolTipView != null) {
            mToolTipView.remove();
            mToolTipView = null;
        }
	}

	@Override
	public void onBitmapLoaded(Bitmap arg0, LoadedFrom arg1) {
		// TODO Auto-generated method stub
		RelativeLayout l = (RelativeLayout) rootView.findViewById(R.id.locateroomLayout);
		mode = appinfo.getRotation(getActivity());
		LBackGround = appinfo.rotate(arg0, 270);
		PBackGround = arg0;		
		
		if ((mode.contains("landscape")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
				((mode.contains("portrait")&&appinfo.getSize(this,mode).equals("Tablet")))){
			l.setBackground(new BitmapDrawable(getResources(),LBackGround));
		}
		if ((mode.contains("portrait")&&(!appinfo.getSize(this,mode).equals("Tablet")))||
				((mode.contains("landscape")&&appinfo.getSize(this,mode).equals("Tablet")))){
			l.setBackground(new BitmapDrawable(getResources(),PBackGround));
		}
		imageLoad = true;		
	}
	
	public void onBitmapFailed() {
		// TODO Auto-generated method stub
		
	}
}
