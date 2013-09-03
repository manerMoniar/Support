package app.support.settings;

import app.support.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterSettings extends BaseAdapter {
	
	Activity activity;
	ListSettings elemento;
	ListSettings[] elementos;
	
	public AdapterSettings(Activity activity, ListSettings[] elementos) {
		super();
		this.activity = activity;
		this.elementos = elementos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		if (elementos.length > 0) {
			count = elementos.length;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return elementos[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return elementos[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		
		if( v == null){
			LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.list_settings, null);
		}

		elemento = elementos[position];
		
		ImageView iv = (ImageView) v.findViewById(R.id.imageViewIconoLista);
		iv.setImageDrawable(elemento.getIcon());
		
		TextView tv = (TextView) v.findViewById(R.id.textViewLista);
		tv.setText(elemento.getCont());
		
		return v;
	}

}