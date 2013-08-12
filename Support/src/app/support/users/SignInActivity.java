package app.support.users;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;

public class SignInActivity extends Activity implements OnClickListener {

	TextView etv;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		etv = (TextView) findViewById(R.id.textViewForget);
		etv.setOnClickListener(this);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(SignInActivity.this, ForgotActivity.class);
		startActivity(intent);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(SignInActivity.this, MainActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(SignInActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(SignInActivity.this, AccessActivity.class);
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
