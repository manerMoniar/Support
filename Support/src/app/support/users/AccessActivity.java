package app.support.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import app.support.R;

public class AccessActivity extends Activity implements OnClickListener{
	
	Button btnRegister, btnSignIn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_access);
		
		btnRegister = (Button) findViewById(R.id.buttonRegister);		
		btnSignIn = (Button) findViewById(R.id.buttonSignIn);
		
		btnRegister.setOnClickListener(this);
		btnSignIn.setOnClickListener(this);
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_user, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		Log.i("ActionBar", ""+v.getId());
		switch(v.getId()){
	       case R.id.buttonSignIn :
	    	   intent = new Intent(AccessActivity.this, SignInActivity.class);
 	    	   startActivity(intent);
	    	   break;
	       case R.id.buttonRegister :
	    	   intent = new Intent(AccessActivity.this, RegisterActivity.class);
	    	   startActivity(intent);
	    	   break;
		} 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	            return true;
	        case R.id.menu_categories:
	            return true;
	        case R.id.menu_user:
	        	Intent intent = new Intent(AccessActivity.this, AccessActivity.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}