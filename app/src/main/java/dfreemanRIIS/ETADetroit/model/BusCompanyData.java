package dfreemanRIIS.ETADetroit.model;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

public class BusCompanyData {

    private final String[] placeNameArray = new String[3];

    public BusCompanyData(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor companyCursor = databaseHelper.getCompanies();

        if(companyCursor.moveToFirst()) {
            int i = 0;
            do {
                String newPlace = "" + companyCursor.getString(0);
                placeNameArray[i] = newPlace;
                i++;
            } while (companyCursor.moveToNext());
        }
    }

    public ArrayList<BusCompany> placeList() {
        ArrayList<BusCompany> list = new ArrayList<>();
        for (String aPlaceNameArray : placeNameArray) {
            BusCompany busCompany = new BusCompany();
            busCompany.name = aPlaceNameArray;
            busCompany.imageName = aPlaceNameArray.replaceAll("\\s+", "").toLowerCase();
            list.add(busCompany);
        }
        return (list);
    }
}
