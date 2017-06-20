package com.example.nathanwelch.devslopesradio.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.model.Song;

/**
 * Created by Nathan Welch on 5/29/2017.
 */

public class SongsViewHolder extends RecyclerView.ViewHolder {
    private TextView songTitleCard;
    private TextView songAlbumCard;
//    private ImageView playButton;

    public SongsViewHolder(View itemView) {
        super(itemView);
        //grabs references to the image/text for our clicked song
        this.songTitleCard = (TextView) itemView.findViewById(R.id.songTitleCard);
        this.songAlbumCard = (TextView) itemView.findViewById(R.id.songAlbumCard);
//        this.playButton = (ImageView) itemView.findViewById(R.id.playButton);
    }

    public void updateSong(Song song) {
        songTitleCard.setText(song.getSongTitle());
        songAlbumCard.setText(song.getSongAlbum());
    }

}
