package com.example.myfirstapp;

import java.io.IOException;
import android.os.Bundle;
import android.view.Menu;


import com.example.myfirstapp.DatabaseHelper;
import com.example.myfirstapp.Match;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;

public class ShowMatchActivity extends Activity {
	String match = null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_match);
	    
		
		new MyTask().execute("http://www.liverpool.no/");
		
		final Match m1 = new Match(match);
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        m1.save(dbHelper);

            
	    

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
	}

	private class MyTask extends AsyncTask<String, Void, String> {
		
		@Override
		protected void onPreExecute() {
			
		}
		
		@Override
		protected String doInBackground(String... params) {
			Document doc = null;
			try {
				 doc = Jsoup.connect("http://www.liverpool.no/").userAgent("Mozilla/5.0 (Linux; U; Android 2.2; en-gb; GT-P1000 Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1").get();
				 Element content = doc.select(".lfcMatches").first();		
				 match = content.text();
              } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return match;

		}
		@Override
		protected void onPostExecute(String result) {
		TextView textView =  (TextView)findViewById(R.id.show);
        try {
			textView.setText(match);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_match, menu);
		return true;
	}


} 
