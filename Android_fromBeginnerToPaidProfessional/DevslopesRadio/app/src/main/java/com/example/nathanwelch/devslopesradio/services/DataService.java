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

    private DataService() {}

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
        list.add(new Station("Dese Important", "keymusic"));
        list.add(new Station("Some A' Them Oldies", "vinylmusic"));
        list.add(new Station("Flight Plan (Tunes for Travel)", "flightplanmusic"));
        return list;
    }

    public ArrayList<Song> getSongList() {
        ArrayList<Song> list = new ArrayList<>();
        list.add(new Song("Song Title 1", "Song Album 1"));
        list.add(new Song("Song Title 2", "Song Album 2"));
        list.add(new Song("Song Title 3", "Song Album 3"));
        list.add(new Song("Song Title 4", "Song Album 4"));
        list.add(new Song("Song Title 5", "Song Album 5"));
        list.add(new Song("Song Title 6", "Song Album 6"));
        list.add(new Song("Song Title 7", "Song Album 7"));
        list.add(new Song("Song Title 8", "Song Album 8"));
        list.add(new Song("Song Title 9", "Song Album 9"));
        list.add(new Song("Song Title 10", "Song Album 10"));
        return list;
    }
}

