package dfreemanRIIS.ETADetroit.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.Menu;
import android.view.MenuItem;

import dfreemanRIIS.ETADetroit.view.MainActivity;

public class MainViewModel extends BaseObservable{

    private Context context;
    private MainActivity mainActivity;
    public boolean isListView;

    public MainViewModel(Context context) {
        mainActivity = new MainActivity();
        this.context = context;
    }

    private void setUpActionBar() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private void toggle() {
        if (isListView) {
            isListView = false;
        } else {
            isListView = true;
        }
    }
}
