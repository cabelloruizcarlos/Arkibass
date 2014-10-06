/**
 * 
 */
package com.arkibass.util;

import java.util.List;

import com.arkibass.R;
import com.arkibass.R.anim;
import com.arkibass.R.id;
import com.arkibass.R.layout;
import com.arkibass.model.RowItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author Carlos
 *
 */
public class ListReportAdapter extends ArrayAdapter<RowItem> {

    Context context;

    public ListReportAdapter(Context context, int resourceId, List<RowItem> items){
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder{
        
        TextView name;
        TextView description;
        TextView department;
        TextView standard;
        TextView employee;
        TextView telephone;
        LinearLayout card;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            Object[] result = setHolder(mInflater, rowItem.getReport(),null);
            holder = (ViewHolder) result[0];
            convertView = (View) result[1];
        } else
            holder = (ViewHolder)convertView.getTag();

        fillHolder(holder,rowItem);        
                        
        return convertView;
    }
    
    /*
     * 
     * UTILITIES
     * 
     */
    
    private Object[] setHolder(LayoutInflater mInflater, String report, View convertView){
    	
    	ViewHolder holder= new ViewHolder();
    	switch (report){
    	
    	case "buildingfloor":
    		
    		convertView = mInflater.inflate(R.layout.roomsbybuildingfloor_row, null);
	    	holder.card = (LinearLayout) convertView.findViewById(R.id.roomsbybuildingfloorCard);
	    	
	        holder.name = (TextView)convertView.findViewById(R.id.roomsbybuildingfloorName);
	        holder.description = (TextView)convertView.findViewById(R.id.roomsbybuildingfloorDescription);
	        holder.department= (TextView)convertView.findViewById(R.id.roomsbybuildingfloorDepartment);
	        holder.standard = (TextView)convertView.findViewById(R.id.roomsbybuildingfloorStandard);
	        holder.employee = (TextView)convertView.findViewById(R.id.roomsbybuildingfloorEmployee);
	        holder.telephone = (TextView)convertView.findViewById(R.id.roomsbybuildingfloorTelephone);
	        break;
	        
    	case "department":
    		
    		convertView = mInflater.inflate(R.layout.roomsbydepartment_row, null);
	    	holder.card = (LinearLayout) convertView.findViewById(R.id.roomsbydepartmentCard);
	    	
	        holder.name = (TextView)convertView.findViewById(R.id.roomsbydepartmentName);
	        holder.description = (TextView)convertView.findViewById(R.id.roomsbydepartmentDescription);
	        holder.standard = (TextView)convertView.findViewById(R.id.roomsbydepartmentStandard);
	        holder.employee = (TextView)convertView.findViewById(R.id.roomsbydepartmentEmployee);
	        holder.telephone = (TextView)convertView.findViewById(R.id.roomsbydepartmentTelephone);
	        break;
	        
    	case "standard":
    		
    		convertView = mInflater.inflate(R.layout.roomsbystandard_row, null);
	    	holder.card = (LinearLayout) convertView.findViewById(R.id.roomsbystandardCard);
	    	
	        holder.name = (TextView)convertView.findViewById(R.id.roomsbystandardName);
	        holder.description = (TextView)convertView.findViewById(R.id.roomsbystandardDescription);
	        holder.department = (TextView)convertView.findViewById(R.id.roomsbystandardDepartment);
	        holder.employee = (TextView)convertView.findViewById(R.id.roomsbystandardEmployee);
	        break;
	    default:
	    	holder = null;
	    	break;
    	}
    	
    	convertView.setTag(holder);
    	Object[] result = new Object[]{holder,convertView};
    	
    	return result;
    }
    
    private void fillHolder(ViewHolder holder, RowItem rowItem){
    
    	switch (rowItem.getReport()){
    	
    	case "buildingfloor":
        	holder.name.setText(rowItem.getName());
            holder.description.setText(rowItem.getDesc());
            holder.department.setText(rowItem.getDepartment());
            holder.standard.setText(rowItem.getStandard());
            holder.employee.setText(rowItem.getEmployee());
            holder.telephone.setText(rowItem.getTelephone());
    		break;
    	case "department":
        	holder.name.setText(rowItem.getName());
            holder.description.setText(rowItem.getDesc());
            holder.standard.setText(rowItem.getStandard());
            holder.employee.setText(rowItem.getEmployee());
            holder.telephone.setText(rowItem.getTelephone());
    		break;
    	case "standard":
        	holder.name.setText(rowItem.getName());
            holder.description.setText(rowItem.getDesc());
            holder.department.setText(rowItem.getDepartment());
            holder.employee.setText(rowItem.getEmployee());
    		break;    		
    	default:
    		break;
    	}
    	
    	Animation animation = AnimationUtils.loadAnimation(context, R.anim.card_animation);
        holder.card.startAnimation(animation);
    }
}
