package app.support.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.users.AccessActivity;

public class ReviewsActivity extends Activity{
	
	Activity context;
	ListView list;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviews);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		RatingBar rate = (RatingBar) findViewById(R.id.ratingBarReview);
		rate.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				TextView textRate = (TextView) findViewById(R.id.textViewRate);
				textRate.setText(getResources().getString(R.string.your_review));
				Toast.makeText(getApplicationContext(), "Tu evaluación: " + rating, Toast.LENGTH_SHORT).show();
			}
		});
		
		context = this;
		list = (ListView) findViewById(R.id.listViewReviews);
		
		ElementReview[] elements = {
			    new ElementReview(5, getResources().getString(R.string.nombre_2), 
			    		getResources().getString(R.string.comentario_1), 1),
	    		new ElementReview(1, getResources().getString(R.string.nombre_3), 
			    		getResources().getString(R.string.comentario_2), 2),
	    		new ElementReview(4, getResources().getString(R.string.nombre_4), 
			    		getResources().getString(R.string.comentario_3), 3),
	    		new ElementReview(3, getResources().getString(R.string.nombre_5), 
			    		getResources().getString(R.string.comentario_4), 4),
	    		new ElementReview(1, getResources().getString(R.string.nombre_6), 
			    		getResources().getString(R.string.comentario_5), 5),
	    		new ElementReview(4, getResources().getString(R.string.nombre_7), 
			    		getResources().getString(R.string.comentario_6), 6),
			    		
		};
		
		AdapterReview adapter = new AdapterReview(context, elements);
		
		list.setAdapter(adapter);
			
	}
	
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
	        	intent = new Intent(ReviewsActivity.this, MainActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_categories:
	        	intent = new Intent(ReviewsActivity.this, CategoriesActivity.class);
	        	startActivity(intent);
	            return true;
	        case R.id.menu_user:
	        	intent = new Intent(ReviewsActivity.this, AccessActivity.class);
	        	startActivity(intent);
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
	
}
