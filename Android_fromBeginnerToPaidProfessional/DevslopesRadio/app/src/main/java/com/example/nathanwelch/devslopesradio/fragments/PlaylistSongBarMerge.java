package com.example.nathanwelch.devslopesradio.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nathanwelch.devslopesradio.R;

/**
 * Created by Nathan Welch on 6/2/2017.
 */

public class PlaylistSongBarMerge extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.merge_playlist_songbar, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstatnceState) {
        super.onActivityCreated(savedInstatnceState);

    }

}
