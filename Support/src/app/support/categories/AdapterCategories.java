package app.support.categories;

import java.util.ArrayList;

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
	ArrayList<ElementCategoryList> elements;
	
	public AdapterCategories(Activity activity, ArrayList<ElementCategoryList> elements) {
		super();
		this.activity = activity;
		this.elements = elements;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return elements.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		
		if(v == null){
			LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.activity_element_category, null);
		}
		
		element = elements.get(position);
		
		TextView name = (TextView) v.findViewById(R.id.textViewCategory);
		name.setText(element.getCategory());
		
		return v;
	}

}
