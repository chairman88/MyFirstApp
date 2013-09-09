package com.example.myfirstapp;

import java.io.IOException;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShowMatchActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_match);
	
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_match, menu);
		return true;
	}


public String showRecentMatch() throws IOException
{
Document doc = Jsoup.connect("http://www.liverpool.no/").get();
String match = doc.title();
TextView textView = new TextView(this);
textView.setTextSize(40);
textView.setText(match);

// Set the text view as the activity layout
setContentView(textView);

return match;

    }

} 
