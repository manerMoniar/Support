package app.support.categories;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.RequestManager;
import app.support.users.AccessActivity;

public class CategoriesActivity extends Activity{
	
		Activity context;
		ListView list;
	    private String url="http://192.168.110.219/support/categories.php";
	    private ProgressDialog loadDialog;
	    ArrayList<ElementCategoryList> arrayCategories = new ArrayList<ElementCategoryList>();
	    ElementCategoryList elements;
	    
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_categories);
			
			context = this;
			list = (ListView) findViewById(R.id.listViewCategories);
			
			new AsyncCategories().execute(); 
		}
		
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
		        	intent = new Intent(CategoriesActivity.this, MainActivity.class);
		        	startActivity(intent);
		        	finish();
		            return true;
		        case R.id.menu_categories:
		        	intent = new Intent(CategoriesActivity.this, CategoriesActivity.class);
		        	startActivity(intent);
		        	finish();
		            return true;
		        case R.id.menu_user:
		        	intent = new Intent(CategoriesActivity.this, AccessActivity.class);
		        	startActivity(intent);
		        	finish();
		            return true;
		        case R.id.menu_settings:
		        	
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
		
		public boolean loadCategories(){
			ArrayList<NameValuePair> parameters= new ArrayList<NameValuePair>();
			JSONArray jData = new RequestManager(url, parameters).getServerResponse();

    		if (jData!=null && jData.length() > 0){
				try {
					for (int i = 0; i < jData.length(); i++) {
						JSONObject jsonChildNode = jData.getJSONObject(i);
						
						//get the content of each tag
					    int idCategory = jsonChildNode.optInt("id");
					    String categoryName = jsonChildNode.optString("nombre");
					    
					    elements = new ElementCategoryList(categoryName, idCategory);
						arrayCategories.add(elements);
					}
					return true;
				}catch (JSONException e) {
					Log.e("#EXCEPTION: loadCategories()", e.getMessage());
					return false;
				}		         
    		}else{
    			Log.e("Error", "Data");
	    		return false;
    		}
		}
		
		public void setCategories(){
			
			AdapterCategories adapter = new AdapterCategories(context, arrayCategories);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener(){
				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub					
					TextView v=(TextView) arg1.findViewById(R.id.textViewCategory);

					Intent intent = new Intent(CategoriesActivity.this, UsersCategoriesActivity.class);
					intent.putExtra("idCategory", ""+arg3);
					intent.putExtra("nameCategory", ""+v.getText());
		        	startActivity(intent);
				}
				
			});
		}
		
		//starting asynchronus task  <input parameters, progress, result>
		private class AsyncCategories extends AsyncTask< String, Integer, String > {
	    	
	        protected void onPreExecute() {
	        	//start progress dialog
	        	loadDialog = new ProgressDialog(CategoriesActivity.this);
	        	loadDialog.setMessage("Cargando...");
	        	loadDialog.setIndeterminate(false);
	        	loadDialog.setCancelable(false);
	        	loadDialog.show();
	        }
	 
			protected String doInBackground(String... params) {
				if (loadCategories() == true){    		    		
	    			return "ok";
	    		}else{    		
	    			return "error";   	          	  
	    		}
			}
	       
	        protected void onPostExecute(String result) {
	        	//hide progress dialog.
	        	loadDialog.dismiss();
	        	
	        	if (result.equals("ok")){
	        		setCategories();
	        	}
	        	else{
	        		Toast.makeText(getApplicationContext(), "Ups! hubo un error. Inténtalo más tarde.", Toast.LENGTH_LONG).show();
	        	}
			}
			
	    }
}