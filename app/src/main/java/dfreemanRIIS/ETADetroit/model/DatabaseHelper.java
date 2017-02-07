package dfreemanRIIS.ETADetroit.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "ETADetroitDatabase.db";
    private static final int DB_VERSION = 1;
    private final Context context;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public Cursor getRouteDetails(String routeName) {

        try {
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(context);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
            String[] thisRouteName = {routeName};

            return db.query("routes",
                    new String[]{"_id", "company", "route_name", "route_number", "direction1", "direction2", "days_active"},
                    "route_name = ?", thisRouteName, null, null, null);

        } catch (Exception e) {
            System.out.println("DatabaseHelper getRouteDetails BROKE");
            return null;
        }
    }

    public Cursor getAllRoutes(String companyName) {
        try {
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(context);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
            String[] thisCompanyName = {companyName};

            return db.query("routes",
                    new String[]{"_id", "route_name", "route_number"},
                    "company = ?", thisCompanyName, null, null, null);

        } catch (SQLiteException e) {
            System.out.println("DatabaseHelper getAllRoutes BROKE");
            return null;
        }
    }

    public Cursor getRouteStops(String route_id) {
        try {
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(context);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

            return db.query("stop_orders",
                    new String[]{"_id", "stop_name"},
                    "route_id = ? AND stop_day = ?", new String[] {route_id, "Weekday"}, null, null, null);

        } catch (SQLiteException e) {
            System.out.println("DatabaseHelper getRouteStops BROKE");
            return null;
        }
    }

    public Cursor getCompanies() {
        try {
            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(context);
            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();

            return db.query(true, "routes",
                    new String[]{"company"},
                    null, null, null, null, null, null);

        } catch (SQLiteException e) {
            System.out.println("DatabaseHelper getCompanies BROKE");
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}