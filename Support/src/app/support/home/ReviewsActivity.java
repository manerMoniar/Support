package app.support.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import app.support.MainActivity;
import app.support.R;
import app.support.categories.CategoriesActivity;
import app.support.users.AccessActivity;

public class ReviewsActivity extends Activity{
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviews);		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		RatingBar rate = (RatingBar) findViewById(R.id.ratingBarReviews);
		rate.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Rating: "+ rating, Toast.LENGTH_SHORT).show();
			}
		});
			
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
