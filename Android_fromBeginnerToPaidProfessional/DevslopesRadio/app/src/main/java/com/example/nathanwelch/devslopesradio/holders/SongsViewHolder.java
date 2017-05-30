package com.example.nathanwelch.devslopesradio.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.model.Song;

/**
 * Created by Nathan Welch on 5/29/2017.
 */

public class SongsViewHolder extends RecyclerView.ViewHolder {
    private TextView songTitle;
    private TextView songAlbum;
    private TextView songImage;

    public SongsViewHolder(View itemView) {
        super(itemView);
        Log.d("SONGS_VIEW_HOLDER", "SongsViewHolder !!!!!!!  -> " + itemView);


        //grabs references to the image/text for our clicked song
        this.songTitle = (TextView) itemView.findViewById(R.id.main_text);
//        this.songAlbum = (TextView) itemView.findViewById(R.id.songAlbumFrag);
    }

    public void updateSong(Song song) {

        Log.d("SONGS_VIEW_HOLDER_out", "updateSong  -> " + song.getSongTitle());
//        Log.d("SONGS_VIEW_HOLDER_out", "updateSong  -> " + song.getSongAlbum());

        songTitle.setText(song.getSongTitle());
//        songAlbum.setText(song.getSongAlbum());
    }
}
