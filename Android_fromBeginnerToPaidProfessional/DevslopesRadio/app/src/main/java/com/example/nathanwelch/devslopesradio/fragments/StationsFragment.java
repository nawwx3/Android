package com.example.nathanwelch.devslopesradio.fragments;


import android.graphics.Rect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.adapters.StationsAdapter;
import com.example.nathanwelch.devslopesradio.services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StationsFragment extends Fragment {

    private static final String ARG_STATION_TYPE = "station_type";

    public static final int STATION_TYPE_FEATURED = 0;
    public static final int STATION_TYPE_RECENT = 1;
    public static final int STATION_TYPE_PARTY = 2;

    private int stationType;

    public StationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param stationType The Radio Stations Type.
     * @return A new instance of fragment StationsFragment.
     */

    //create a new instance and the stationType is passed in from mainFragment
    public static StationsFragment newInstance(int stationType) {
        StationsFragment fragment = new StationsFragment();
        Bundle args = new Bundle();
        //the type is set in "args"
        args.putInt(ARG_STATION_TYPE, stationType);
        //then we set the args here
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //so the arguments are grabbed and then saved so we can use them
            stationType = getArguments().getInt(ARG_STATION_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations, container, false);

        //on the fragment (recycler_stations) is where the recyclerView will be placed
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_stations);
        //the size will not be changing so there will be a fixed size -> also helps to run faster
        recyclerView.setHasFixedSize(true);

        StationsAdapter adapter;
        if(stationType == STATION_TYPE_FEATURED) {
            adapter = new StationsAdapter(DataService.getInstance().getFeaturedStations());
        } else if(stationType == STATION_TYPE_RECENT) {
            adapter = new StationsAdapter(DataService.getInstance().getRecentStations());
        } else {
            adapter = new StationsAdapter(DataService.getInstance().getFeaturedStations());
        }

        recyclerView.addItemDecoration(new HorizontalSpaceItemDecorator(30));

        recyclerView.setAdapter(adapter);

        //this is how the orientation of the RecyclerView is set
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}

class HorizontalSpaceItemDecorator extends RecyclerView.ItemDecoration {
    private final int spacer;

    public HorizontalSpaceItemDecorator(int spacer) {
        this.spacer = spacer;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = spacer;
    }

}



