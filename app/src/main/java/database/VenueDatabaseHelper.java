package database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASHL7 2/20/2017
 * SQLite Database to save the book
 */
public class VenueDatabaseHelper extends SQLiteOpenHelper  {

    private static final String TAG = VenueDatabaseHelper.class.getName();

    private static final String DB_NAME = "venues.sqlite";    //or .db name of the file
    private static final int VERSION = 1;

    public static final String TABLE_VENUE = "venue";      //name of the table
    public static final String COLUMN_ID = "_id";   //primary key of the table
    public static final String COLUMN_VENUE_ID = "venue_id";
    public static final String COLUMN_VENUE_NAME = "venue_name";



    //Create the database
    public VenueDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        Log.d(TAG, "database instance created.");
    }


    //create the venue table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_VENUE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VENUE_ID + " TEXT, " +
                COLUMN_VENUE_NAME + " TEXT" +
                ");";
        db.execSQL(createQuery);
        Log.d(TAG, TABLE_VENUE + " table created.");
    }


    // Upgrade the table, delete the older version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENUE);
        onCreate(db);
    }

}
