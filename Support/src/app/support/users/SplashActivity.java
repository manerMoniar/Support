package app.support.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import app.support.MainActivity;
import app.support.R;

public class SplashActivity extends Activity {
	
	 	private final int DURACION_SPLASH = 2000; // 2 segundos
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 	                		
		        setContentView(R.layout.activity_splash);
		 
		        new Handler().postDelayed(new Runnable(){
		            public void run(){
		            	
			        	Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			        	startActivity(intent);
			        	finish();
			        	
		            };
		        }, DURACION_SPLASH);
	    }
}
