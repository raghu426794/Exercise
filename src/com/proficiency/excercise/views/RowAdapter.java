package com.proficiency.excercise.views;
import java.util.ArrayList;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.proficiency.excercise.R;
import com.proficiency.excercise.model.Row;

/**
 * Custom Adapter class to show the custom view with data associated with it
 */
public class RowAdapter extends BaseAdapter {
	private ArrayList<Row> listData;
	private Activity activity;
	private AQuery aquery;
	
	public RowAdapter(Activity activity, ArrayList<Row> listData) {
		this.listData = listData;
		this.activity = activity;
		removeNullValues(listData);
	}

	/**
	 * remove the items if they are null
	 * @param listData
	 */
	private void removeNullValues(ArrayList<Row> listData) {
		for(int i = 0; i < listData.size(); i++) {
			Row rowItem = listData.get(i);
			String title = rowItem.getTitle();
			String desc = rowItem.getDescription();
			String imagePath = rowItem.getImageHref();
			if(title == null && desc == null && imagePath == null) {
				listData.remove(i);
				i--;
			}
		}
	}
	
	@Override
	public int getCount() {
		if(listData != null) {
			return listData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(listData != null) {
			return listData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getViewTypeCount() {
		return getCount();
	}
	
	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = activity.getLayoutInflater().inflate(R.layout.list_view_row, null);
			RowAdapterViewHolder viewHolder = new RowAdapterViewHolder();
			viewHolder.title = (TextView)convertView.findViewById(R.id.row_title);
			viewHolder.description = (TextView)convertView.findViewById(R.id.row_description);
			viewHolder.imageView = (ImageView)convertView.findViewById(R.id.row_image);
			convertView.setTag(viewHolder);
		}
		
		Row rowItem = listData.get(position);
		RowAdapterViewHolder viewHolder = (RowAdapterViewHolder)convertView.getTag();
		viewHolder.title.setText("");
		viewHolder.description.setText("");
		viewHolder.imageView.setImageResource(0);
		if(rowItem != null) {
			String title = rowItem.getTitle();
			if(title != null) {
				viewHolder.title.setText(title);
			} 
			String description = rowItem.getDescription();
			if(description != null) {
				viewHolder.description.setText(description);
			}
			String imagePath = rowItem.getImageHref();
			if(imagePath != null) {
				if(aquery == null) {
					aquery = new AQuery(activity);
				}
				aquery.id(viewHolder.imageView).image(imagePath, true, true, 0, R.drawable.ic_launcher);
			}
		}
		return convertView;
	}
	
	/**
	 * @author 426794
	 * static class for viewholder pattern for adapter
	 *
	 */
	static class RowAdapterViewHolder {
		TextView title;
		TextView description;
		ImageView imageView;
	}

}
