package com.example.nathanwelch.devslopesradio.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.model.Song;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SongFragment extends Fragment {
    private static final String ARG_SONG_TITLE = "song_title";
    private static final String ARG_SONG_ALBUM = "song_album";

    private TextView songTitle;
    private TextView songAlbum;

    private String title;
    private String album;

    public SongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param song is the song to appear.
     * @return A new instance of fragment SongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SongFragment newInstance(Song song) {
        SongFragment fragment = new SongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SONG_TITLE, song.getSongTitle());
        args.putString(ARG_SONG_ALBUM, song.getSongAlbum());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_SONG_TITLE);
            album = getArguments().getString(ARG_SONG_ALBUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        songTitle = (TextView) view.findViewById(R.id.songTitleFrag);
        songAlbum = (TextView) view.findViewById(R.id.songAlbumFrag);
        Log.d("songFrag", title);
        Log.d("songFrag", album);

        songTitle.setText(title);
        songAlbum.setText(album);

        return view;
    }

}
