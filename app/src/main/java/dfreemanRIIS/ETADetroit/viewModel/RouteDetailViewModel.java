package dfreemanRIIS.ETADetroit.viewModel;

import android.content.Context;
import android.database.Cursor;

import dfreemanRIIS.ETADetroit.model.DatabaseHelper;

public class RouteDetailViewModel {

    private final Context context;

    public RouteDetailViewModel(Context context) {
        this.context = context;
    }

    public Cursor getRouteDetail(String thisRoute) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getRouteDetails(thisRoute);
    }

    public Cursor getRouteStops(String thisRouteNumber) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper.getRouteStops(thisRouteNumber);
    }
}
