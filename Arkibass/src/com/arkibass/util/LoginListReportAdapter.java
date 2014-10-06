/**
 * 
 */
package com.arkibass.util;

import java.util.List;

import com.arkibass.R;
import com.arkibass.model.LoginRowItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author Carlos
 *
 */
public class LoginListReportAdapter extends ArrayAdapter<LoginRowItem> {

    Context context;

    public LoginListReportAdapter(Context context, int resourceId, List<LoginRowItem> items){
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder{
        
        TextView user;
        TextView password;
        TextView role;
        LinearLayout card;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        LoginRowItem LoginRowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
        	
        	convertView = mInflater.inflate(R.layout.loginlist_row, null);
	    	holder.card = (LinearLayout) convertView.findViewById(R.id.loginlistCard);
	    	
	        holder.user = (TextView)convertView.findViewById(R.id.loginlistUser);
	        holder.password = (TextView)convertView.findViewById(R.id.loginlistPassword);
	        holder.role = (TextView)convertView.findViewById(R.id.loginlistAFMRole);
	        
	        convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.user.setText(LoginRowItem.getUser());
        holder.password.setText(LoginRowItem.getPassword());
        holder.role.setText(LoginRowItem.getRole());
                        
    	Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_animation);
        holder.card.startAnimation(animation);
        
        return convertView;
    }
}
