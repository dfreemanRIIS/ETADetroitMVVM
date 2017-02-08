package dfreemanRIIS.ETADetroit.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import dfreemanRIIS.ETADetroit.R;
import dfreemanRIIS.ETADetroit.view.MainActivity;

public class MainViewModel extends BaseObservable {

    public static void toggle() {
        MenuItem item = MainActivity.menu.findItem(R.id.action_toggle);
        if (MainActivity.isListView) {
            MainActivity.mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            MainActivity.isListView = false;
        } else {
            MainActivity.mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            MainActivity.isListView = true;
        }
    }

    public static void createMain() {
        MainActivity.isListView = true;
        MainActivity.mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
    }

    public static void travelListAdapterClick(Context mainActivityContext, View v, int position) {
        MainActivity.transitionIntent = new Intent(mainActivityContext, CompanyViewModel.class);
        MainActivity.transitionIntent.putExtra(CompanyViewModel.EXTRA_PARAM_ID, position);
    }
}
