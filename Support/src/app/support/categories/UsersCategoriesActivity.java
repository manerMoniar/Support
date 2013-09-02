package app.support.categories;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import app.support.MainActivity;
import app.support.R;
import app.support.RequestManager;
import app.support.categories.CategoriesActivity;
import app.support.home.Adapter;
import app.support.home.ElementList;
import app.support.home.ProfileActivity;
import app.support.users.AccessActivity;

public class UsersCategoriesActivity extends Activity {

	Activity context;
	ListView list;
	private String url="http://192.168.110.219/support/usersCategories.php";
    private ProgressDialog loadDialog;
	ArrayList<ElementList> arrayUsers = new ArrayList<ElementList>();
    ElementList elements;
    String category;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		context = this;
		list = (ListView) findViewById(R.id.listViewHome);
		
		category = (String) getIntent().getExtras().get("idCategory");
		String name = (String) getIntent().getExtras().get("nameCategory");
		
		setTitle("Categorías - " + name);
		
		new AsyncUsers().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categories, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(UsersCategoriesActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(UsersCategoriesActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(UsersCategoriesActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
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
	
	public boolean loadUsers(){
		ArrayList<NameValuePair> parameters= new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("idCategory", category));
		JSONArray jData = new RequestManager(url, parameters).getServerResponse();

		if (jData!=null){
			try {
				for (int i = 0; i < jData.length(); i++) {
					JSONObject jsonChildNode = jData.getJSONObject(i);
					
					//get the content of each tag
				    int idUser = jsonChildNode.optInt("id");
				    String userName = jsonChildNode.optString("nombre");
				    String address = jsonChildNode.optString("direccion");
				    int stars = jsonChildNode.optInt("puntos");
				    int total = jsonChildNode.optInt("total");
				    
				    elements = new ElementList(getResources().getDrawable(R.drawable.person), userName, address, stars, "("+total+")", idUser);
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
				Intent intent = new Intent(UsersCategoriesActivity.this, ProfileActivity.class);
				intent.putExtra("idUsuario", ""+arg3);
	        	startActivity(intent);
			}
			
		});
	}
	
	
	//starting asynchronus task  <input parameters, progress, result>
	private class AsyncUsers extends AsyncTask< String, Integer, String > {
    	
        protected void onPreExecute() {
        	//start progress dialog
        	loadDialog = new ProgressDialog(UsersCategoriesActivity.this);
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
