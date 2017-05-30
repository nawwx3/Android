package com.example.nathanwelch.devslopesradio.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.activities.MainActivity;
import com.example.nathanwelch.devslopesradio.holders.StationsViewHolder;
import com.example.nathanwelch.devslopesradio.model.Station;

import java.util.ArrayList;

/**
 * Created by Nathan Welch on 5/24/2017.
 */

public class StationsAdapter extends RecyclerView.Adapter<StationsViewHolder>{

    private ArrayList<Station> stations;

    public StationsAdapter(ArrayList<Station> stations) { this.stations = stations; }

    @Override
    public int getItemCount() { return stations.size(); }

    @Override
    public void onBindViewHolder(StationsViewHolder holder, final int position) {
        final Station station = stations.get(position);
        Log.d("STATIONS_ADAPTER", "onBindViewHolder " + station.getStationTitle());

        holder.updateUI(station);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the details screen
                MainActivity.getMainActivity().loadDetailsScreen(station);
            }
        });
    }

    @Override
    public StationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("STATIONS_ADAPTER", "onCreateViewHolder ");

        //       can't inflate from the here, have to do it from the parent -> ( from(parent.getContext() )
        View stationCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_station, parent, false);
        return new StationsViewHolder(stationCard);
    }
}
