package app.support.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import app.support.R;

public class AdapterReview extends BaseAdapter {
	
	Activity activity;
	ElementReview element;
	ElementReview[] elements;
	
	public AdapterReview(Activity activity, ElementReview[] elements) {
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
			v = li.inflate(R.layout.activity_element_reviews, null);

			  if (position % 2 == 0)
				  v.setBackgroundResource(R.drawable.odd);
	          else
	              v.setBackgroundResource(R.drawable.even);

		}
		
		element = elements[position];
		
		RatingBar rating = (RatingBar) v.findViewById(R.id.ratingBarReviews);
		rating.setRating(element.getStars());
		
		TextView user = (TextView) v.findViewById(R.id.textViewNameUserReview);
		user.setText(element.getNameUserReview());
		
		TextView userComment = (TextView) v.findViewById(R.id.textViewUserComment);
		userComment.setText(element.getUserComment());
		
		return v;
	}

}
