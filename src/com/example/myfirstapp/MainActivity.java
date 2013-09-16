package com.example.myfirstapp;


import com.example.myfirstapp.Match;
import com.example.myfirstapp.DatabaseHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
    }
    
    public void onResume() {
    	super.onResume();
    	setListAdapter(
        		new ArrayAdapter<Match>(this,
        				android.R.layout.simple_list_item_1,
        				Match.getAll(new DatabaseHelper(this))));
               
    }
    
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
        }
    
    
    public void sendMessage (View view) {
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }

    public void showRecentMatch (View view) {
    	Intent intent = new Intent(this, ShowMatchActivity.class);
    	startActivity(intent);
    }
    
    public void showNextMatch (View view) {
    	Intent intent = new Intent(this, ShowNextMatchActivity.class);
    	startActivity(intent);
    }
    
    
}
