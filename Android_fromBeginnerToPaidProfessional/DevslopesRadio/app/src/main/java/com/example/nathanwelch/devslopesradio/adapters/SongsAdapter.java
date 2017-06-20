package com.example.nathanwelch.devslopesradio.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.activities.MainActivity;
import com.example.nathanwelch.devslopesradio.fragments.SongFragment;
import com.example.nathanwelch.devslopesradio.holders.SongsViewHolder;
import com.example.nathanwelch.devslopesradio.model.Song;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Nathan Welch on 5/29/2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {

    SongFragment.Communicator comm;
    private ArrayList<Song> songs;
    private ImageView playButton;
    private TextView songTitle;
    private TextView songAlbum;

    public SongsAdapter(ArrayList<Song> songs) { this.songs = songs; }

    @Override
    public int getItemCount() { return songs.size(); }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, final int position) {
        Log.d("SONGS_ADAPTER", "onBindViewHolder");

//        playButton = (ImageView) holder.itemView.findViewById(R.id.playButton);
        final Song song = songs.get(position);
        songTitle = (TextView) holder.itemView.findViewById(R.id.songTitle);
        songAlbum = (TextView) holder.itemView.findViewById(R.id.songAlbum);

        Log.d("SONGS_ADAPTER_out", "onBindViewHolder_t   " + song.getSongTitle());
        Log.d("SONGS_ADAPTER_out", "onBindViewHolder_a  " + song.getSongAlbum());

        holder.updateSong(song);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the song screen
                Log.d("SONGS_ADAPTER_out", "onClick   " + song.getSongTitle());

                comm.respond(song);

//                songTitle.setText(song.getSongTitle());
//                songAlbum.setText(song.getSongAlbum());
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
        View songCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_song, parent, false);
        return new SongsViewHolder(songCard);
    }
}
