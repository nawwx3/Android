package com.example.nathanwelch.devslopesradio.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.activities.MainActivity;
import com.example.nathanwelch.devslopesradio.holders.SongsViewHolder;
import com.example.nathanwelch.devslopesradio.model.Song;

import java.util.ArrayList;

/**
 * Created by Nathan Welch on 5/29/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {

    private ArrayList<Song> songs;

    public SongsAdapter(ArrayList<Song> songs) { this.songs = songs; }

    @Override
    public int getItemCount() { return songs.size(); }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, final int position) {
        Log.d("SONGS_ADAPTER", "onBindViewHolder");

        final Song song = songs.get(position);
        Log.d("SONGS_ADAPTER_out", "onBindViewHolder_t   " + song.getSongTitle());
        Log.d("SONGS_ADAPTER_out", "onBindViewHolder_a  " + song.getSongAlbum());

        holder.updateSong(song);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the details screen
                MainActivity.getMainActivity().loadSongScreen(song);
            }
        });
    }

//    @Override
//    public SongsViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
//        View songCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_song, parent, false);
//        return new SongsViewHolder(songCard);
//    }
    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("SONGS_ADAPTER", "onCreateViewHolder");
        View songCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_station, parent, false);
        return new SongsViewHolder(songCard);
    }
}
