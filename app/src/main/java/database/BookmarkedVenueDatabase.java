package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashSet;


/**
 * Created by ASHL7 2/20/2017
 * SQLite database to keep all the venues bookmarked by the user
 */
public class BookmarkedVenueDatabase {

    private static final String TAG = BookmarkedVenueDatabase.class.getName();

    private SQLiteDatabase database;
    private VenueDatabaseHelper dbHelper;
    private Context context;

    private String[] allColumns = {
            VenueDatabaseHelper.COLUMN_ID,
            VenueDatabaseHelper.COLUMN_VENUE_ID,
            VenueDatabaseHelper.COLUMN_VENUE_NAME,
    };


    // Initialize the database
    public BookmarkedVenueDatabase(Context context){
        this.context = context;
        dbHelper = new VenueDatabaseHelper(this.context);
    }


    public void open() {
        try {
            database = dbHelper.getWritableDatabase();   // get a reference to the database
        }
        catch (SQLException e) {
            e.printStackTrace();
            Log.v(TAG, "Opening database failed");
        }
    }


    public void close(){
        dbHelper.close();
    }


    // Add a new venue to database
    public void insertVenue(String venueId, String venueName){
        if(hasVenue(venueId)) {
            Log.d(TAG, "Venue already exists in bookmarked table.");
            return;
        }
        ContentValues values = new ContentValues();
        values.put(VenueDatabaseHelper.COLUMN_VENUE_ID, venueId);
        values.put(VenueDatabaseHelper.COLUMN_VENUE_NAME, venueName);
        database.insert(VenueDatabaseHelper.TABLE_VENUE, null, values);
        Log.d(TAG, "Venue " + venueName + " added to bookmarked table.");
    }



    // Delete a venue from the database
    public void deleteVenue(String venueId) {
        database.delete(VenueDatabaseHelper.TABLE_VENUE,
                VenueDatabaseHelper.COLUMN_VENUE_ID + " = ?",
                new String[]{venueId}) ;
        Log.d(TAG, venueId + " deleted from table.");
    }


    // Get ALL bookmarked venue IDs
    public HashSet<String> getAllBookmarkedI() {
        HashSet<String> IDs = new HashSet<>();
        Cursor cursor = database.query(VenueDatabaseHelper.TABLE_VENUE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            IDs.add(cursorToId(cursor));
            cursor.moveToNext();
        }
        cursor.close();     // make sure to close the cursor
        return IDs;
    }


    // Checks if a contact is in database or not
    public boolean hasVenue(String venueId) {
        String query = "SELECT * FROM " + VenueDatabaseHelper.TABLE_VENUE + " WHERE " +
                VenueDatabaseHelper.COLUMN_VENUE_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{venueId});
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    // Change a cursor(result of a query) to a String object(ID of venue), and return it
    private String cursorToId(Cursor cursor) {
        if (cursor.isBeforeFirst() || cursor.isAfterLast())
            return null;
        return cursor.getString(cursor.getColumnIndex(VenueDatabaseHelper.COLUMN_VENUE_ID));
    }

}
