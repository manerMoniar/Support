package app.support.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import app.support.R;

public class SignInActivity extends Activity implements OnClickListener {

	TextView etv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		etv = (TextView) findViewById(R.id.textViewForget);
		etv.setOnClickListener(this);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_user, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(SignInActivity.this, ForgotActivity.class);
		startActivity(intent);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	            return true;
	        case R.id.menu_categories:
	            return true;
	        case R.id.menu_user:
	        	Intent intent = new Intent(SignInActivity.this, AccessActivity.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
