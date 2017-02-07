package dfreemanRIIS.ETADetroit.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.transition.Transition;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import dfreemanRIIS.ETADetroit.R;
import dfreemanRIIS.ETADetroit.adapters.TodoCursorAdapter;
import dfreemanRIIS.ETADetroit.adapters.TransitionAdapter;
import dfreemanRIIS.ETADetroit.controller.Controller;
import dfreemanRIIS.ETADetroit.model.BusCompany;
import dfreemanRIIS.ETADetroit.model.BusCompanyData;

public class CompanyActivity extends Activity {

    public static final String EXTRA_PARAM_ID = "place_id";
    private ImageView mImageView;
    private TextView mTitle;
    private LinearLayout mTitleHolder;
    private LinearLayout mRevealView;
    private BusCompany mBusCompany;
    private int defaultColor;
    private Cursor allRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        ListView mList = (ListView) findViewById(R.id.list);
        BusCompanyData busCompanyData = new BusCompanyData(this);
        mBusCompany = busCompanyData.placeList().get(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        mImageView = (ImageView) findViewById(R.id.placeImage);
        mTitle = (TextView) findViewById(R.id.textView);
        mTitleHolder = (LinearLayout) findViewById(R.id.placeNameHolder);
        mRevealView = (LinearLayout) findViewById(R.id.llEditTextHolder);
        defaultColor = getResources().getColor(R.color.primary_dark);
        mRevealView.setVisibility(View.INVISIBLE);

        //Get info from controller
        Controller controller = new Controller();
        allRoutes = controller.getAllRoute(mBusCompany.name, this);

        CursorAdapter listAdapter = new TodoCursorAdapter(this, allRoutes);

        mList.setAdapter(listAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (allRoutes.moveToPosition(position)) {
                    Intent intent = new Intent(CompanyActivity.this, RouteDetailActivity.class);
                    intent.putExtra(RouteDetailActivity.EXTRA_ROUTE_NAME, allRoutes.getString(1));
                    startActivity(intent);
                }
            }
        });

        loadPlace();
        windowTransition();
        getPhoto();
    }

    private void loadPlace() {
        mTitle.setText(mBusCompany.name);
        mImageView.setImageResource(mBusCompany.getImageResourceId(this));
    }

    private void windowTransition() {
        getWindow().getEnterTransition().addListener(new TransitionAdapter() {
        @Override
        public void onTransitionEnd(Transition transition) {
            getWindow().getEnterTransition().removeListener(this);
        }
        });
    }

    private void getPhoto() {
        Bitmap photo = BitmapFactory.decodeResource(getResources(), mBusCompany.getImageResourceId(this));
        colorize(photo);
    }

    private void colorize(Bitmap photo) {
        Palette mPalette = Palette.generate(photo);
        applyPalette(mPalette);
    }

    private void applyPalette(Palette mPalette) {
        getWindow().setBackgroundDrawable(new ColorDrawable(mPalette.getDarkMutedColor(defaultColor)));
        mTitleHolder.setBackgroundColor(mPalette.getMutedColor(defaultColor));
        mRevealView.setBackgroundColor(mPalette.getLightVibrantColor(defaultColor));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CompanyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}