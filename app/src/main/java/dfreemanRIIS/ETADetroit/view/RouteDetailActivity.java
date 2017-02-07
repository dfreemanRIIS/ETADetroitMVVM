package dfreemanRIIS.ETADetroit.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.database.Cursor;

import dfreemanRIIS.ETADetroit.R;
import dfreemanRIIS.ETADetroit.controller.Controller;

public class RouteDetailActivity extends Activity {

    public static final String EXTRA_ROUTE_NAME = "route_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        //Get info from controller
        Controller controller = new Controller();
        String thisRoute = (String)getIntent().getExtras().get(EXTRA_ROUTE_NAME);
        Cursor details = controller.getRouteDetails(thisRoute, this);

        if(details.moveToFirst()) {
            TextView textView1 = (TextView)findViewById(R.id.listDetails);
            String placeHolder1 = "Company: " + details.getString(1)
                    + "\nRoute name: " + details.getString(2)
                    + "\nRoute number: " + details.getString(3)
                    + "\nDirection 1: " + details.getString(4)
                    + "\nDirection 2: " + details.getString(5)
                    + "\nDays active: " + details.getString(6);
            textView1.setText(placeHolder1);
        }

		Cursor stops = controller.getRouteStops(details.getString(3), this);
        CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                stops,
                new String[]{"stop_name"},
                new int[]{android.R.id.text1},
                0);

        ListView listStops = (ListView) findViewById(R.id.listStops);
        listStops.setAdapter(listAdapter);
    }
}
