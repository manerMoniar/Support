package app.support.categories;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import app.support.MainActivity;
import app.support.R;
import app.support.users.AccessActivity;

public class CategoriesActivity extends Activity{
	
		Activity context;
		ListView list;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_categories);
			
			context = this;
			list = (ListView) findViewById(R.id.listViewCategories);
			
			ElementCategoryList[] elements = {
					new ElementCategoryList(getResources().getString(R.string.cat_printers), 1),
					new ElementCategoryList(getResources().getString(R.string.cat_cameras), 2),
					new ElementCategoryList(getResources().getString(R.string.cat_cellphones), 3),
					new ElementCategoryList(getResources().getString(R.string.cat_laptops), 4),
					new ElementCategoryList(getResources().getString(R.string.cat_audio), 5),
					new ElementCategoryList(getResources().getString(R.string.cat_plotters), 6),
			};
			
			AdapterCategories adapter = new AdapterCategories(context, elements);
			
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(context)
						.setTitle("Categoría")
						.setMessage(arg3+"")
						.setIcon(android.R.drawable.ic_dialog_alert)
						.setPositiveButton(android.R.string.yes, null)
						.show();
				}
				
			});
			
		}
		
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.categories, menu);
			return true;
		}
			
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			Intent intent;
		    switch (item.getItemId()) {
		        case R.id.menu_home:
		        	intent = new Intent(CategoriesActivity.this, MainActivity.class);
		        	startActivity(intent);
		            return true;
		        case R.id.menu_categories:
		        	intent = new Intent(CategoriesActivity.this, CategoriesActivity.class);
		        	startActivity(intent);
		            return true;
		        case R.id.menu_user:
		        	intent = new Intent(CategoriesActivity.this, AccessActivity.class);
		        	startActivity(intent);
		            return true;
		        case R.id.menu_settings:
		        	
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
}
