package app.support.users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import app.support.MainActivity;
import app.support.R;

public class SplashActivity extends Activity {
	
	 	private final int DURACION_SPLASH = 2000; // 2 segundos
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 	        
	        SharedPreferences prefs = getSharedPreferences("Preferences",Context.MODE_PRIVATE);	 
        	boolean correo = prefs.getBoolean("splash", false);
        	
        	if(correo){
        		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
	        	startActivity(intent);
        	}
        	else{
        		SharedPreferences.Editor editor = prefs.edit();
        		editor.putBoolean("splash", true);
        		editor.commit();
        		
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
}
