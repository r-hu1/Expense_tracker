/*  Copyright (c) 2008-2009 Google Inc. 


Licensed under the Apache License, Version 2.0 (the "License"); 

you may not use this file except in compliance with the License. 

You may obtain a copy of the License at 


  http://www.apache.org/licenses/LICENSE-2.0 


Unless required by applicable law or agreed to in writing, software 

distributed under the License is distributed on an "AS IS" BASIS, 

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 

See the License for the specific language governing permissions and 

limitations under the License. */




package ca.ualberta.cs.expensetracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreatNewClaimActivity extends Activity {
	
	private static final String FILENAME = "file.sav";
	private EditText bodyTextAdd;
	private EditText bodyTextDelete;
	private ListView oldClaimList;
	private ArrayList<String> claimlist;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_new_claim);
		
		bodyTextAdd = (EditText) findViewById(R.id.textToAddClaim);
		Button addButton = (Button) findViewById(R.id.AddClaim);
		oldClaimList = (ListView) findViewById(R.id.viewAddedClaim);
		Button deleteButton = (Button) findViewById(R.id.Delete_Expense);
		bodyTextDelete = (EditText) findViewById(R.id.textToDeleteClaim);
		
		addButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyTextAdd.getText().toString();
				claimlist.add(0, text);
				adapter.notifyDataSetChanged();
				saveInFile(text, new Date(System.currentTimeMillis()));
				bodyTextAdd.setText("");
			}
			
		});
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(RESULT_OK);
				String text = bodyTextDelete.getText().toString();
				claimlist.remove(text);
				adapter.notifyDataSetChanged();
				saveInFile(text, new Date(System.currentTimeMillis()));
				bodyTextDelete.setText("");
				
				
			}
		});
		
		
	}
	
	@Override
	protected void onStart()  {

		super.onStart();
		claimlist = loadFromFile();
		adapter = new ArrayAdapter<String>(this,
				R.layout.claim_list_linear, R.id.textInLinear,claimlist);
		ListView oldClaimList = (ListView) findViewById(R.id.viewAddedClaim);
    	oldClaimList.setAdapter(adapter);
    	
	}
	

	
    private ArrayList<String> loadFromFile() {
		// TODO Auto-generated method stub
    	Gson gson = new Gson();
		ArrayList<String> claimlist = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			//https://sites.google.com/site/gson/gson-user-guide 2015-01-30
			Type dataType = new TypeToken<ArrayList<String>>() {}.getType();
			InputStreamReader isr = new InputStreamReader(fis);
			claimlist = gson.fromJson(isr, dataType);
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (claimlist == null){
			claimlist = new ArrayList<String>();
		}
		return claimlist;

	}

	protected void saveInFile(String text, Date date) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(claimlist,osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creat_new_claim, menu);
		return true;
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
	public void addClaimButtonActivity(View v){
		Toast.makeText(this,"Adding a claim", Toast.LENGTH_SHORT).show();
		EditText textview = (EditText) findViewById(R.id.textToAddClaim);
		
	}
}
 