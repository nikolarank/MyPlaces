package rs.elfak.mosis.nikola.myplaces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nikola on 2017-04-02.
 */

public class MyPlacesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table" + MyPlacesDBAdapter.DATABASE_TABLE + " ("
            + MyPlacesDBAdapter.PLACE_ID + "integer primary key autoincrement,"
            + MyPlacesDBAdapter.PLACE_NAME + "text unique not null, "
            + MyPlacesDBAdapter.PLACE_DESCRIPTION + "text, "
            + MyPlacesDBAdapter.PLACE_LONG + " text, "
            + MyPlacesDBAdapter.PLACE_LAT + " text);";

    public MyPlacesDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DATABASE_CREATE);
        }catch (SQLiteException e){
            Log.v("MyPlacesDatabeseHelper", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + MyPlacesDBAdapter.DATABASE_NAME);
        onCreate(db);
    }
}
