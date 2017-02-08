package dfreemanRIIS.ETADetroit.viewModel;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

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

    public CursorAdapter getRouteStops(String thisRouteNumber) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor stops = databaseHelper.getRouteStops(thisRouteNumber);
        return new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_1,
                stops,
                new String[]{"stop_name"},
                new int[]{android.R.id.text1},
                0);
    }

    public String getAllDetailsString(String thisRoute) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor details = databaseHelper.getRouteDetails(thisRoute);
        if(details.moveToFirst()) {
            String allDetails = "Company: " + details.getString(1)
                    + "\nRoute name: " + details.getString(2)
                    + "\nRoute number: " + details.getString(3)
                    + "\nDirection 1: " + details.getString(4)
                    + "\nDirection 2: " + details.getString(5)
                    + "\nDays active: " + details.getString(6);
            return allDetails;
        }
        return null;
    }
}
