package app.support.users;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.settings.SettingsActivity;

public class ForgotActivity extends Activity {
			
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(ForgotActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(ForgotActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(ForgotActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(ForgotActivity.this, SettingsActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
