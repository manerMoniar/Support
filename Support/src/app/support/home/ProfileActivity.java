package app.support.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.users.AccessActivity;

public class ProfileActivity extends Activity{
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
				 
		TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		 
		TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tabInformation);
		spec.setIndicator("Información", null);
		tabs.addTab(spec);
		
		spec=tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tabLocation);
		spec.setIndicator("Ubicación", null);
		tabs.addTab(spec);
		 
		tabs.setCurrentTab(0);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ratingProfile);
		linearLayout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProfileActivity.this, ReviewsActivity.class);
	        	startActivity(intent);
			}
	    });
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(ProfileActivity.this, MainActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(ProfileActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(ProfileActivity.this, AccessActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_settings:
	        	
	            return true;
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
