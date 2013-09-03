package app.support.users;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.settings.SettingsActivity;

public class MyProfileActivity extends Activity{
	
	Activity context;
	ListView list;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);		

		Toast.makeText(getApplicationContext(), "Bienvenido !", Toast.LENGTH_LONG).show();
		
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
		
		TextView tName = (TextView) findViewById(R.id.textViewNameProfile);
		tName.setText(SignInActivity.element.getName());
		
		TextView tEmail = (TextView) findViewById(R.id.textViewEmailProfile);
		tEmail.setText(SignInActivity.element.getEmail());
		
		TextView tTelephone = (TextView) findViewById(R.id.textViewTelProfile);
		tTelephone.setText(SignInActivity.element.getTelephone());
		
		RatingBar rStars = (RatingBar) findViewById(R.id.ratingBarProfile);
		rStars.setRating(SignInActivity.element.getStars());
		
		TextView tTotal = (TextView) findViewById(R.id.textViewTotal);
		tTotal.setText(SignInActivity.element.getCountStars());
		
		TextView tAddress = (TextView) findViewById(R.id.textViewAddressProfile);
		tAddress.setText(SignInActivity.element.getAddress());
		
		Button bLogout = (Button) findViewById(R.id.buttonLogout);
		bLogout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 
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
	        	intent = new Intent(MyProfileActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(MyProfileActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(MyProfileActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(MyProfileActivity.this, SettingsActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
