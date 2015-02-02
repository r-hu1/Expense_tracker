/*


 */



package ca.ualberta.cs.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;

// Array of options --> ArrayAdapter --> ListView

// List View: {views: claim_items.xml}

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        creatnewclaimButton();

        
        
    }



	private void creatnewclaimButton() {
		// TODO Auto-generated method stub
    	// 1. get reference to button
    	Button messageButton = (Button) findViewById(R.id.creatclaim);
    	
    	// 2. set click listener to run code
    	messageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "Creating new claim", Toast.LENGTH_SHORT). show();
				Intent intent = new Intent(MainActivity.this, CreatNewClaimActivity.class);
		    	startActivity(intent);
			}
		});
    	
    		
    	
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void editClaim(MenuItem menu){
    	 Toast.makeText(this, "Edit Claim", Toast.LENGTH_SHORT). show();
    	 Intent intent = new Intent(MainActivity.this, CreatNewClaimActivity.class);
    	 startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
