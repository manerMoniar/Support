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
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.RequestManager;
import app.support.categories.CategoriesActivity;
import app.support.settings.SettingsActivity;

public class RegisterActivity extends Activity {
	
	EditText email;
	EditText pass;
	EditText name;
	EditText address;
	EditText telephone;
	Button buttonRegister;
	Activity context;
    private String url="http://"+MainActivity.server+"/support/register.php";
    private ProgressDialog loadDialog;
    
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		email = (EditText) findViewById(R.id.editTextRegisterEmail);
		pass = (EditText) findViewById(R.id.editTextRegisterPassword);
		name = (EditText) findViewById(R.id.editTextRegisterName);
		address = (EditText) findViewById(R.id.editTextRegisterAddress);
		telephone = (EditText) findViewById(R.id.editTextRegisterTelephone);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        
        buttonRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String tUser = email.getText().toString();
        		String tPass = pass.getText().toString();
        		String tName = name.getText().toString();
        		String tAddress = address.getText().toString();
        		String tTelephone = telephone.getText().toString();
        		
        		if(checkRegisterData(tUser, tPass, tName, tAddress, tTelephone) == true){

        			new AsyncRegister().execute(tUser, tPass, tName, tAddress, tTelephone);
        			
        		}else{
        			errorData();
        		}
        		
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
	        	intent = new Intent(RegisterActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(RegisterActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(RegisterActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(RegisterActivity.this, SettingsActivity.class);
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
    public boolean checkRegisterData(String email ,String pass, String name, String address, String telephone){
	    if 	(email.equals("") || pass.equals("") || name.equals("") || address.equals("") || telephone.equals("")){
	    	return false;
	    }else{
	    	return true;
	    }
    }
    
    public void errorData(){
    	Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	    vibrator.vibrate(200);
	    Toast.makeText(getApplicationContext(),"Faltan campos por llenar.", Toast.LENGTH_SHORT).show();   	
    }
    
    public boolean registerUser(String email ,String pass, String name, String address, String telephone) {
    	int registerStatus = -1;
    	ArrayList<NameValuePair> parameters= new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("email", email));
    	parameters.add(new BasicNameValuePair("password",pass));
    	parameters.add(new BasicNameValuePair("name",pass));
    	parameters.add(new BasicNameValuePair("address",pass));
    	parameters.add(new BasicNameValuePair("telephone",pass));
    	
		JSONArray jData = new RequestManager(url, parameters).getServerResponse();
		    		
		if (jData!=null && jData.length() > 0){
			try {
					JSONObject jsonChildNode = jData.getJSONObject(0);
					
					//get the content of each tag
					registerStatus = jsonChildNode.optInt("registerStatus");
					
					if (registerStatus == 0){
		    			 return false;
		    		}
		    		else{
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
  	private class AsyncRegister extends AsyncTask< String, Integer, String > {
      	
  		String tUser, tPass, tName, tAddress, tTelephone;
  		
          protected void onPreExecute() {
          	//start progress dialog
          	loadDialog = new ProgressDialog(RegisterActivity.this);
          	loadDialog.setMessage("Cargando...");
          	loadDialog.setIndeterminate(false);
          	loadDialog.setCancelable(false);
          	loadDialog.show();
          }
   
  		protected String doInBackground(String... params) {
  		
  			tUser = params[0];
  			tPass = params[1];
  			tName = params[2];
  			tAddress = params[3];
  			tTelephone = params[4];
  			
  			if (registerUser(tUser, tPass, tName, tAddress, tTelephone) == true){    		    		
      			return "ok";
      		}else{    		
      			return "error";   	          	  
      		}
  		}
         
          protected void onPostExecute(String result) {
          	//hide progress dialog.
          	loadDialog.dismiss();
          	
          	if (result.equals("ok")){
          		Toast.makeText(getApplicationContext(), "Gracias por registrarte, ahora puedes ingresar.", Toast.LENGTH_LONG).show();
          		
          		Intent i=new Intent(RegisterActivity.this, SignInActivity.class);
  				startActivity(i); 
  				
          	}
          	else{
          		Toast.makeText(getApplicationContext(), "Error al registrar cuenta, intenta más tarde.", Toast.LENGTH_LONG).show();
          	}
  		}
  		
      }
}
