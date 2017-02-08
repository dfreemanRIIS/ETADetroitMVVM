package dfreemanRIIS.ETADetroit.view;

import android.content.Context;
import android.database.Cursor;

import dfreemanRIIS.ETADetroit.model.DatabaseHelper;

public class CompanyActivity {

    private final Context context;

    public CompanyActivity(Context context) {
        this.context = context;
    }

    public Cursor getAllRoutes(String name) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getAllRoutes(name);
    }
}
