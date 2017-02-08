package dfreemanRIIS.ETADetroit.viewModel;

import android.databinding.BaseObservable;
import android.view.MenuItem;

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
}
