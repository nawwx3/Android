package com.example.nathanwelch.devslopesradio.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.model.Station;

/**
 * Created by Nathan Welch on 5/24/2017.
 */

public class StationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView mainImage;
    private TextView titleTextView;

    public StationsViewHolder(View itemView) {
        super(itemView);

        //grabs references to the image/text on our card
        this.mainImage = (ImageView) itemView.findViewById(R.id.main_image);
        this.titleTextView = (TextView) itemView.findViewById(R.id.main_text);
    }

    //will update the UI after new data has been passed to it
    public void updateUI(Station station) {
        String uri = station.getImgUri();
        int resource = mainImage.getResources().getIdentifier(uri, null, mainImage.getContext().getPackageName());

        mainImage.setImageResource(resource);
        titleTextView.setText(station.getStationTitle());
    }
}
