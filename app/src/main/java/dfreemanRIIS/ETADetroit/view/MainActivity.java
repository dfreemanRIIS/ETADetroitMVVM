package dfreemanRIIS.ETADetroit.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.util.Pair;
import android.widget.Toolbar;

import dfreemanRIIS.ETADetroit.R;
import dfreemanRIIS.ETADetroit.adapters.TravelListAdapter;
import dfreemanRIIS.ETADetroit.viewModel.MainViewModel;

public class MainActivity extends Activity {

    public static Menu menu;
    public static boolean isListView;
    public static StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Toolbar toolbar;
    public static Intent transitionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel.createMain();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        TravelListAdapter mAdapter = new TravelListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();
    }

    private final TravelListAdapter.OnItemClickListener onItemClickListener = new TravelListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            MainViewModel.travelListAdapterClick(MainActivity.this, v, position);
            ImageView placeImage = (ImageView) v.findViewById(R.id.placeImage);
            LinearLayout placeNameHolder = (LinearLayout) v.findViewById(R.id.placeNameHolder);
            View navigationBar = findViewById(android.R.id.navigationBarBackground);
            View statusBar = findViewById(android.R.id.statusBarBackground);
            Pair<View, String> imagePair = Pair.create((View) placeImage, "tImage");
            Pair<View, String> holderPair = Pair.create((View) placeNameHolder, "tNameHolder");
            Pair<View, String> navPair = Pair.create(navigationBar,
                    Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            Pair<View, String> statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME);
            Pair<View, String> toolbarPair = Pair.create((View)toolbar, "tActionBar");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, imagePair, holderPair, navPair, statusPair, toolbarPair);
            ActivityCompat.startActivity(MainActivity.this, transitionIntent, options.toBundle());
        }
    };
    
    private void setUpActionBar() {
        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        MainViewModel.toggle();
    }
}
