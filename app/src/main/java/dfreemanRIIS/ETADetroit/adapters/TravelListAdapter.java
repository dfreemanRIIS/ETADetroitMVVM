package dfreemanRIIS.ETADetroit.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dfreemanRIIS.ETADetroit.model.BusCompany;
import dfreemanRIIS.ETADetroit.model.BusCompanyData;
import dfreemanRIIS.ETADetroit.R;

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.ViewHolder> {

    private final Context mContext;
    private OnItemClickListener mItemClickListener;

    public TravelListAdapter(Context context) {
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final LinearLayout placeHolder;
        public final LinearLayout placeNameHolder;
        public final TextView placeName;
        public final ImageView placeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }

    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener =  mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        BusCompanyData busCompanyData = new BusCompanyData(mContext);
        return busCompanyData.placeList().size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BusCompany busCompany = new BusCompanyData(mContext).placeList().get(position);
        holder.placeName.setText(busCompany.name);
        Picasso.with(mContext).load(busCompany.getImageResourceId(mContext)).into(holder.placeImage);

        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), busCompany.getImageResourceId(mContext));

        Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
          public void onGenerated(Palette palette) {
            int bgColor = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
            holder.placeNameHolder.setBackgroundColor(bgColor);
          }
        });
    }
}
