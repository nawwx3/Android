package com.example.nathanwelch.devslopesradio.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.adapters.SongsAdapter;
import com.example.nathanwelch.devslopesradio.model.Station;
import com.example.nathanwelch.devslopesradio.services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFregment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFregment extends Fragment {

    private static final String ARG_STATION_PIC = "station_pic";
    private static final String ARG_STATION_NAME = "station_name";

    private String stationPic;
    private String stationName;
    private TextView playlist;
    private ImageView songPlayed;
    public DetailsFregment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param station the station that was clicked.
     * @return A new instance of fragment DetailsFregment.
     */

    // TODO: Rename and change types and number of parameters
    public static DetailsFregment newInstance(Station station) {
        Log.d("DETAILS_FRAGMENT", "newInstance");
        DetailsFregment fragment = new DetailsFregment();
        Bundle args = new Bundle();
        args.putString(ARG_STATION_PIC, station.getImgUri());
        args.putString(ARG_STATION_NAME, station.getStationTitle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DETAILS_FRAGMENT", "onCreate");
        if (getArguments() != null) {
            stationPic = getArguments().getString(ARG_STATION_PIC);
            stationName = getArguments().getString(ARG_STATION_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DETAILS_FRAGMENT", "onCreateView  -> " + stationName);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_fregment, container, false);
        songPlayed = (ImageView)view.findViewById(R.id.songPlayed);
        playlist = (TextView) view.findViewById(R.id.playlist);

        //set playlist picture
        String uri = stationPic;
        int resource = songPlayed.getResources().getIdentifier(uri, null, songPlayed.getContext().getPackageName());
        songPlayed.setImageResource(resource);
        //set playlist name
        playlist.setText(stationName);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_songs);
        recyclerView.setHasFixedSize(true);
        Log.d("DETAILS_FRAGMENT", "onCreateView..createdRecyclerView");
        SongsAdapter adapter;
        adapter = new SongsAdapter(DataService.getInstance().getSongList());
        Log.d("DETAILS_FRAGMENT", "onCreateView..madeAdapter");

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Log.d("DETAILS_FRAGMENT", "onCreateView..endCreate");

        return view;
    }
}
