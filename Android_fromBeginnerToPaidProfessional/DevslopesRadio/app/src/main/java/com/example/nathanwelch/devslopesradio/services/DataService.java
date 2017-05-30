package com.example.nathanwelch.devslopesradio.services;

import com.example.nathanwelch.devslopesradio.model.Song;
import com.example.nathanwelch.devslopesradio.model.Station;

import java.util.ArrayList;

/**
 * Created by Nathan Welch on 5/24/2017.
 */

public class DataService {
    private static final DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {

    }

    public ArrayList<Station> getFeaturedStations() {
        // *** pretend we just downloaded featured stations from the internet ***

        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station("Flight Plan (Tunes for Travel)", "flightplanmusic"));
        list.add(new Station("Two-Wheelin' (Biking Playlist)", "bicyclemusic"));
        list.add(new Station("Kids Jams (Music for Children)", "kidsmusic"));
        list.add(new Station("Kids Jams (Music for Children)", "kidsmusic"));

        return list;
    }

    public ArrayList<Station> getRecentStations() {

        ArrayList<Station> list = new ArrayList<>();
        list.add(new Station("Some A' Them Oldies", "vinylmusic"));
        list.add(new Station("Music With the Peeps", "socialmusic"));
        list.add(new Station("Dese Important", "keymusic"));

        return list;
    }

    public ArrayList<Station> getPartyStations() {
        ArrayList<Station> list = new ArrayList<>();
        return list;
    }

    public ArrayList<Song> getSongList() {
        ArrayList<Song> list = new ArrayList<>();
        list.add(new Song("title1", "album1"));
        list.add(new Song("title2", "album2"));
        list.add(new Song("title3", "album3"));
        list.add(new Song("title4", "album4"));
        return list;
    }
}

