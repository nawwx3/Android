package com.example.nathanwelch.bootcamplocator.holders;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathanwelch.bootcamplocator.R;
import com.example.nathanwelch.bootcamplocator.model.Devslopes;

import org.w3c.dom.Text;

/**
 * Created by Nathan Welch on 6/19/2017.
 */

public class LocationsViewHolder extends RecyclerView.ViewHolder{

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationsViewHolder(View itemView) {
        super(itemView);

        locationImage = (ImageView)itemView.findViewById(R.id.location_image);
        locationTitle = (TextView)itemView.findViewById(R.id.location_title);
        locationAddress = (TextView)itemView.findViewById(R.id.location_address);
    }

    public void updateUI(Devslopes location) {
        String uri = location.getImgURL();
        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTitle());
        locationAddress.setText(location.getLocationAddress());
    }
}
