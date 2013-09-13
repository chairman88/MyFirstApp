package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Represents a single note.
 *
 */
public class Match {
	
	long id;
	String matchid;
	
	
	private Match() {}
	
	public Match(final String matchid) {
		this.matchid = matchid;
		
	}
	
	public void save(DatabaseHelper dbHelper) {
		final ContentValues values = new ContentValues();
		values.put(MATCHID, this.matchid);
		
		
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		this.id = db.insert(LFCMATCHES_TABLE_NAME, null, values);
		db.close();
	}
	
	public static Match[] getAll(final DatabaseHelper dbHelper) {
		 final List<Match> matches = new ArrayList<Match>();
		 final SQLiteDatabase db = dbHelper.getWritableDatabase();
		 final Cursor c = db.query(LFCMATCHES_TABLE_NAME,
				 new String[] { ID, MATCHID}, null, null, null, null, null);
		 // make sure you start from the first item
		 c.moveToFirst();
		 while (!c.isAfterLast()) {
			 final Match match = cursorToMatch(c);
		     matches.add(match);
		     c.moveToNext();
		 }
		 // Make sure to close the cursor
		 c.close();
		 return matches.toArray(new Match[matches.size()]);
	}
	
	public static Match cursorToMatch(Cursor c) {
		final Match match = new Match();
		match.setMatchid(c.getString(c.getColumnIndex(MATCHID)));
		return match;
	}

	
	public String getMatchid() {
		return matchid;
	}

	public void setMatchid(String matchid) {
		this.matchid = matchid;
	}

	
	
	public String toString() {
		return this.matchid;
	}
	
	
	
	public static final String LFCMATCHES_TABLE_NAME = "lfcmatches";
	// column names
	static final String ID = "id"; // 
	static final String MATCHID = "matchid";
	// SQL statement to create our table
	public static final String LFCMATCHES_CREATE_TABLE = "CREATE TABLE " + Match.LFCMATCHES_TABLE_NAME + " ("
							+ Match.ID + " INTEGER PRIMARY KEY,"
							+ Match.MATCHID + " TEXT,"
							+ ");";
	
}

	