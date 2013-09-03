package app.support;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import app.support.categories.CategoriesActivity;
import app.support.home.Adapter;
import app.support.home.ElementList;
import app.support.home.ProfileActivity;
import app.support.settings.SettingsActivity;
import app.support.users.AccessActivity;

public class MainActivity extends Activity {

	Activity context;
	ListView list;
	public static String server = "www.mawd1tic.com";
	private String url="http://"+server+"/support/home.php";
    private ProgressDialog loadDialog;
	public static ArrayList<ElementList> arrayUsers = new ArrayList<ElementList>();
    ElementList elements;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		list = (ListView) findViewById(R.id.listViewHome);
		arrayUsers = new ArrayList<ElementList>();
		
		new AsyncUsers().execute();
	}

	@Override
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
	        	intent = new Intent(MainActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(MainActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(MainActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(MainActivity.this, SettingsActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public boolean loadUsers(){
		ArrayList<NameValuePair> parameters= new ArrayList<NameValuePair>();
		JSONArray jData = new RequestManager(url, parameters).getServerResponse();

		if (jData!=null && jData.length() > 0){
			try {
				for (int i = 0; i < jData.length(); i++) {
					JSONObject jsonChildNode = jData.getJSONObject(i);
					
					//get the content of each tag
				    int idUser = jsonChildNode.optInt("id");
				    String userName = jsonChildNode.optString("nombre");
				    String address = jsonChildNode.optString("direccion");
				    int stars = jsonChildNode.optInt("puntos");
				    int total = jsonChildNode.optInt("total");
				    String email = jsonChildNode.optString("email");
				    String telephone = jsonChildNode.optString("telefono");
				    
				    elements = new ElementList(getResources().getDrawable(R.drawable.person), userName, address, stars, "("+total+")", email, telephone, idUser);
				    arrayUsers.add(elements);
				}
				
				return true;
			}catch (JSONException e) {
				Log.e("#EXCEPTION: loadUsers()", e.getMessage());
				return false;
			}		         
		}else{
			Log.e("Error", "Data");
    		return false;
		}
	}
	
	public void setUsers(){
		Adapter adapter = new Adapter(context, arrayUsers);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
				intent.putExtra("idUsuario", ""+arg2);
	        	startActivity(intent);
			}
			
		});
	}
	
	
	//starting asynchronus task  <input parameters, progress, result>
	private class AsyncUsers extends AsyncTask< String, Integer, String > {
    	
        protected void onPreExecute() {
        	//start progress dialog
        	loadDialog = new ProgressDialog(MainActivity.this);
        	loadDialog.setMessage("Cargando...");
        	loadDialog.setIndeterminate(false);
        	loadDialog.setCancelable(false);
        	loadDialog.show();
        }
 
		protected String doInBackground(String... params) {
			if (loadUsers() == true){    		    		
    			return "ok";
    		}else{    		
    			return "error";   	          	  
    		}
		}
       
        protected void onPostExecute(String result) {
        	//hide progress dialog.
        	loadDialog.dismiss();
        	
        	if (result.equals("ok")){
        		setUsers();
        	}
        	else{
        		Toast.makeText(getApplicationContext(), "Ups! hubo un error. Inténtalo más tarde.", Toast.LENGTH_LONG).show();
        	}
		}
		
    }

}
