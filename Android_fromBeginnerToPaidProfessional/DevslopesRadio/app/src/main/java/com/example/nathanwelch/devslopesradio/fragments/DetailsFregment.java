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

    private String stationPic;
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
        Log.d("CREATE DETAILS", "newInstance");
        DetailsFregment fragment = new DetailsFregment();
        Bundle args = new Bundle();
        args.putString(ARG_STATION_PIC, station.getImgUri());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CREATE DETAILS", "onCreate");
        if (getArguments() != null) {
            stationPic = getArguments().getString(ARG_STATION_PIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("CREATE DETAILS", "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_fregment, container, false);
        songPlayed = (ImageView)view.findViewById(R.id.songPlayed);

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_songs);
//        recyclerView.setHasFixedSize(true);
//        Log.d("  onCreateView", "created recycler view");
//        SongsAdapter adapter;
//        adapter = new SongsAdapter(DataService.getInstance().getSongList());
//        Log.d("  onCreateView", "made adapter");

        String uri = stationPic;
        int resource = songPlayed.getResources().getIdentifier(uri, null, songPlayed.getContext().getPackageName());
        songPlayed.setImageResource(resource);

//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);
//        Log.d("  onCreateView", "end create");

        return view;
    }
}
