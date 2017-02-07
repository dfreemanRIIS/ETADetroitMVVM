package dfreemanRIIS.ETADetroit.model;

import android.content.Context;

public class BusCompany {

  public String name;
  public String imageName;

  public int getImageResourceId(Context context) {
    return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
  }
}
