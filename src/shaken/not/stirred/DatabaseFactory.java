package shaken.not.stirred;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseFactory{
	
	private static SQLiteDatabase myDB = null;
	static final String DB_FULL_PATH = "shakennotstirred";
	static final String TABLE_RECIPES_NAME = "recipes";
	static final String TABLE_INGREDIENTS_NAME = "ingredients";
	
	public static SQLiteDatabase getInstance(Activity callerActivity) {
		if(myDB == null){
		DatabaseFactory.myDB = callerActivity.openOrCreateDatabase(DB_FULL_PATH, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		   /* Create a Table in the Database. */
		   myDB.execSQL("CREATE TABLE IF NOT EXISTS "
//		 + TableName
		 + " (Field1 VARCHAR, Field2 INT(3));");
		 
		   /* Insert data to a Table*/
		   myDB.execSQL("INSERT INTO "
//		 + TableName
		 + " (Field1, Field2)"
		 + " VALUES ('Saranga', 22);");
		}
		return myDB;
	}


	

	/**
	 * Check if the database exist
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	public boolean checkDataBase() {
	    SQLiteDatabase checkDB = null;
	    try {
	        checkDB = SQLiteDatabase.openDatabase(DB_FULL_PATH, null,
	                SQLiteDatabase.OPEN_READONLY);
	        checkDB.close();
	    } catch (SQLiteException e) {
	        // database doesn't exist yet.
	    }
	    return checkDB != null ? true : false;
	}
}
