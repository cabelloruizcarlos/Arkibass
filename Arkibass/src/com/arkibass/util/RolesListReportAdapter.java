/**
 * 
 */
package com.arkibass.util;

import java.util.List;

import com.arkibass.R;
import com.arkibass.model.RoleRowItem;

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
public class RolesListReportAdapter extends ArrayAdapter<RoleRowItem> {

    Context context;

    public RolesListReportAdapter(Context context, int resourceId, List<RoleRowItem> items){
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder{
        
        TextView role;
        TextView name;
        TextView restriction;
        LinearLayout card;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        RoleRowItem RoleRowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
        	
        	convertView = mInflater.inflate(R.layout.roleslist_row, null);
	    	holder.card = (LinearLayout) convertView.findViewById(R.id.roleslistCard);
	    	
	        holder.role = (TextView)convertView.findViewById(R.id.roleslistAFMRole);
	        holder.name = (TextView)convertView.findViewById(R.id.roleslistName);
	        holder.restriction = (TextView)convertView.findViewById(R.id.roleslistRestrictions);
	        
	        convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.role.setText(RoleRowItem.getRole());
        holder.name.setText(RoleRowItem.getName());
        holder.restriction.setText(RoleRowItem.getRestrictions());
                        
    	Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_animation);
        holder.card.startAnimation(animation);
        
        return convertView;
    }
}
