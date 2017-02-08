package dfreemanRIIS.ETADetroit.viewModel;

import android.content.Context;
import android.database.Cursor;

import dfreemanRIIS.ETADetroit.model.DatabaseHelper;

public class CompanyViewModel {

    private final Context context;

    public CompanyViewModel(Context context) {
        this.context = context;
    }

    public Cursor getAllRoutes(String name) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getAllRoutes(name);
    }
}
