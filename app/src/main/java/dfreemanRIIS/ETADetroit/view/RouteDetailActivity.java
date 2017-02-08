package dfreemanRIIS.ETADetroit.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.databinding.DataBindingUtil;

import dfreemanRIIS.ETADetroit.R;
import dfreemanRIIS.ETADetroit.databinding.ActivityRouteDetailBinding;
import dfreemanRIIS.ETADetroit.viewModel.RouteDetailViewModel;

public class RouteDetailActivity extends Activity {

    public static final String EXTRA_ROUTE_NAME = "route_name";
    private String allDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);
        ActivityRouteDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_detail);

        //New RouteDetailViewModel
        RouteDetailViewModel routeDetailViewModel = new RouteDetailViewModel(this);
        String thisRoute = (String)getIntent().getExtras().get(EXTRA_ROUTE_NAME);
        Cursor details = routeDetailViewModel.getRouteDetail(thisRoute);

        if(details.moveToFirst()) {
            allDetails = "Company: " + details.getString(1)
                    + "\nRoute name: " + details.getString(2)
                    + "\nRoute number: " + details.getString(3)
                    + "\nDirection 1: " + details.getString(4)
                    + "\nDirection 2: " + details.getString(5)
                    + "\nDays active: " + details.getString(6);
            binding.setAllDetails(allDetails);
        }

        CursorAdapter listAdapter = routeDetailViewModel.getRouteStops(details.getString(3));
        ListView listStops = (ListView) findViewById(R.id.listStops);
        listStops.setAdapter(listAdapter);
    }
}
