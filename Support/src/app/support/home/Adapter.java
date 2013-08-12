package app.support.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import app.support.R;

public class Adapter extends BaseAdapter {

	Activity activity;
	ElementList element;
	ElementList[] elements;
	
	
	public Adapter(Activity activity, ElementList[] elements) {
		super();
		this.activity = activity;
		this.elements = elements;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		if(elements.length > 0){
			count = elements.length;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return elements[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return elements[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		
		if(v == null){
			LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.activity_element, null);
		}
		
		element = elements[position];
		
		ImageView iv = (ImageView) v.findViewById(R.id.imageViewPerson);
		iv.setImageDrawable(element.getIcon());
		
		TextView name = (TextView) v.findViewById(R.id.textViewName);
		name.setText(element.getName());
		
		TextView address = (TextView) v.findViewById(R.id.textViewAddress);
		address.setText(element.getAddress());
		
		RatingBar rating = (RatingBar) v.findViewById(R.id.ratingBarHome);
		rating.setRating(element.getStars());
		
		TextView countStars = (TextView) v.findViewById(R.id.textViewCountStars);
		countStars.setText(element.getCountStars());
		
		return v;
	}

}
