package app.support.users;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.RequestManager;
import app.support.categories.CategoriesActivity;
import app.support.home.ElementList;
import app.support.settings.SettingsActivity;

public class SignInActivity extends Activity {

	TextView etv;
	EditText user;
	EditText pass;
	Button buttonSignIn;
	public static ElementList element;
	private String url="http://"+MainActivity.server+"/support/login.php";
    private ProgressDialog loadDialog;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		user = (EditText) findViewById(R.id.editTextEmail);
        pass = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        
        buttonSignIn.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String tUser = user.getText().toString();
        		String tPass = pass.getText().toString();

        		if(checkLoginData(tUser, tPass) == true){

        			new AsyncLogin().execute(tUser, tPass);
        			
        		}else{
        			errorLogin();
        		}
				
			}
        	
        });
		
		etv = (TextView) findViewById(R.id.textViewForget);
		
		etv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SignInActivity.this, ForgotActivity.class);
				startActivity(intent);
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
	        	intent = new Intent(SignInActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(SignInActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(SignInActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(SignInActivity.this, SettingsActivity.class);
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
	
	//Validate blank fields
    public boolean checkLoginData(String user ,String pass ){
	    if 	(user.equals("") || pass.equals("")){
	    	return false;
	    }else{
	    	return true;
	    }
    }
    
    public void errorLogin(){
    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast.makeText(getApplicationContext(),"Introduce correo electrónico y contraseña.", Toast.LENGTH_SHORT).show();   	
    }
	
    public boolean loginStatus(String username ,String password ) {
    	int logStatus=-1;
    	ArrayList<NameValuePair> parameters= new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("email", username));
    	parameters.add(new BasicNameValuePair("password",password));
    	
		JSONArray jData = new RequestManager(url, parameters).getServerResponse();
		    		
		if (jData!=null && jData.length() > 0){
			try {
					JSONObject jsonChildNode = jData.getJSONObject(0);
					
					//get the content of each tag
					logStatus = jsonChildNode.optInt("logStatus");
					
					if (logStatus == 0){
		    			 return false;
		    		}
		    		else{
		    			int idUser = jsonChildNode.optInt("id");
					    String userName = jsonChildNode.optString("nombre");
					    String address = jsonChildNode.optString("direccion");
					    int stars = jsonChildNode.optInt("puntos");
					    int total = jsonChildNode.optInt("total");
					    String email = jsonChildNode.optString("email");
					    String telephone = jsonChildNode.optString("telefono");
					    
					    element = new ElementList(getResources().getDrawable(R.drawable.person), userName, address, stars, "("+total+")", email, telephone, idUser);
		    			return true;
		    		}
			}catch (JSONException e) {
				Log.e("#EXCEPTION: loadCategories()", e.getMessage());
				return false;
			}		         
		}else{
			Log.e("Error", "Data");
    		return false;
		}
    	
    }
    
    //starting asynchronus task  <input parameters, progress, result>
	private class AsyncLogin extends AsyncTask< String, Integer, String > {
    	
		String tUser , tPass;
		
        protected void onPreExecute() {
        	//start progress dialog
        	loadDialog = new ProgressDialog(SignInActivity.this);
        	loadDialog.setMessage("Cargando...");
        	loadDialog.setIndeterminate(false);
        	loadDialog.setCancelable(false);
        	loadDialog.show();
        }
 
		protected String doInBackground(String... params) {
		
			tUser = params[0];
			tPass = params[1];
			
			if (loginStatus(tUser, tPass) == true){    		    		
    			return "ok";
    		}else{    		
    			return "error";   	          	  
    		}
		}
       
        protected void onPostExecute(String result) {
        	//hide progress dialog.
        	loadDialog.dismiss();
        	
        	if (result.equals("ok")){
        		Intent i=new Intent(SignInActivity.this, MyProfileActivity.class);
				startActivity(i); 
				finish();
        	}
        	else{
        		Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos.", Toast.LENGTH_LONG).show();
        	}
		}
		
    }
}
