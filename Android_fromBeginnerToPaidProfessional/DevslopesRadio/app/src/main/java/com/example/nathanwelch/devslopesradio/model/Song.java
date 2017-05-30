package com.example.nathanwelch.devslopesradio.model;

/**
 * Created by Nathan Welch on 5/29/2017.
 */

public class Song {

    private String songTitle;
    private String songAlbum;
    private String playButton;

    private final static String DRAWABLE = "drawable/";

    //use this when trying to implement a picture
//    public Song(String title, String album, String imgUri) {
    public Song(String title, String album) {
        this.songTitle = title;
        this.songAlbum = album;
//        this.imgUri = imgUri;
    }

    public String getSongTitle() { return songTitle; }
    public String getSongAlbum() { return songAlbum; }
//    public String getImgUri() { return DRAWABLE + imgUri; }

}
