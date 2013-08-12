package app.support.categories;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.support.R;

public class AdapterCategories extends BaseAdapter {
	
	Activity activity;
	ElementCategoryList element;
	ElementCategoryList[] elements;
	
	
	public AdapterCategories(Activity activity, ElementCategoryList[] elements) {
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
			v = li.inflate(R.layout.activity_element_category, null);
		}
		
		element = elements[position];
		
		TextView name = (TextView) v.findViewById(R.id.textViewCategory);
		name.setText(element.getCategory());
		
		/*ImageView iv = (ImageView) v.findViewById(R.id.imageViewArrow);
		iv.setImageDrawable(R.drawable.arrow);*/
		
		return v;
	}

}
