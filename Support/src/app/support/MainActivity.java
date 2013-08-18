package app.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import app.support.categories.CategoriesActivity;
import app.support.home.Adapter;
import app.support.home.ElementList;
import app.support.home.ProfileActivity;
import app.support.users.AccessActivity;

public class MainActivity extends Activity {

	Activity context;
	ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		list = (ListView) findViewById(R.id.listViewHome);
		
		ElementList[] elements = {
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_1),
								getResources().getString(R.string.direccion_1),
								4, getResources().getString(R.string.count_1), 1),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_2),
								getResources().getString(R.string.direccion_2),
								4, getResources().getString(R.string.count_2), 2),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_3),
								getResources().getString(R.string.direccion_3),
								5, getResources().getString(R.string.count_3), 3),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_4),
								getResources().getString(R.string.direccion_4),
								3, getResources().getString(R.string.count_4), 4),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_5),
								getResources().getString(R.string.direccion_5),
								2, getResources().getString(R.string.count_5), 5),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_6),
								getResources().getString(R.string.direccion_6),
								4, getResources().getString(R.string.count_6), 6),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_7),
								getResources().getString(R.string.direccion_7),
								5, getResources().getString(R.string.count_7), 7),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_8),
								getResources().getString(R.string.direccion_8),
								2, getResources().getString(R.string.count_8), 8),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_9),
								getResources().getString(R.string.direccion_9),
								1, getResources().getString(R.string.count_9), 9),
				new ElementList(getResources().getDrawable(R.drawable.person), 
								getResources().getString(R.string.nombre_10),
								getResources().getString(R.string.direccion_10),
								3, getResources().getString(R.string.count_10), 10),
		};
		
		Adapter adapter = new Adapter(context, elements);
		
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
	        	startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Log.i("ActionBar", "Test!");
		
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(MainActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(MainActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(MainActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
