package dfreemanRIIS.ETADetroit.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import dfreemanRIIS.ETADetroit.R;

public class TodoCursorAdapter extends CursorAdapter {

    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView routeNumberView = (TextView) view.findViewById(R.id.routeNumber);
        TextView routeNameView = (TextView) view.findViewById(R.id.routeName);


        int routeNumber = cursor.getInt(cursor.getColumnIndexOrThrow("route_number"));
        String routeName = cursor.getString(cursor.getColumnIndexOrThrow("route_name"));

        routeNumberView.setText(String.valueOf(routeNumber));
        routeNameView.setText(routeName);
    }
}