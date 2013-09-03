package app.support.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.users.AccessActivity;

public class SettingsActivity extends Activity {
	
	Activity contexto;
	ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
        contexto = this;
        lista = (ListView) findViewById(R.id.listViewSettings);
        
        ListSettings[] elementos = {
        		new ListSettings("Califica la App", getResources().getDrawable(R.drawable.ic_cal), 1),
        		new ListSettings("Compartir", getResources().getDrawable(R.drawable.ic_f), 2),
        		new ListSettings("Compartir", getResources().getDrawable(R.drawable.ic_t), 3),
        		new ListSettings("Acerca de..", getResources().getDrawable(R.drawable.ic_info), 4)
        };
        AdapterSettings adapter = new AdapterSettings(contexto, elementos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        		int identifier = (int) id;
        		switch(identifier){
        			case 1:
        				Toast.makeText(getApplicationContext(), "Calificar App", Toast.LENGTH_SHORT).show();
        				break;
        			case 2:
        				Toast.makeText(getApplicationContext(), "Compartir en facebook", Toast.LENGTH_SHORT).show();
        				break;
        			case 3:
        				Toast.makeText(getApplicationContext(), "Compartir en twitter", Toast.LENGTH_SHORT).show();
        				break;
        			case 4:
	        			Intent i = new Intent(contexto, AboutActivity.class);
						startActivity(i);
        			break;
        		}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;
	    switch (item.getItemId()) {
	        case R.id.menu_home:
	        	intent = new Intent(SettingsActivity.this, MainActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(SettingsActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(SettingsActivity.this, AccessActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        case R.id.menu_settings:
	        	intent = new Intent(SettingsActivity.this, SettingsActivity.class);
	        	startActivity(intent);
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
