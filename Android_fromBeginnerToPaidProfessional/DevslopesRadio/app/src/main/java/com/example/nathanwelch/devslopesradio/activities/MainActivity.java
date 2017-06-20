package com.example.nathanwelch.devslopesradio.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.fragments.MainFragment;
import com.example.nathanwelch.devslopesradio.fragments.PlaylistSongBarMerge;
import com.example.nathanwelch.devslopesradio.fragments.SongFragment;
import com.example.nathanwelch.devslopesradio.model.Song;
import com.example.nathanwelch.devslopesradio.model.Station;

public class MainActivity extends AppCompatActivity implements SongFragment.Communicator {

    private static MainActivity mainActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    private static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setMainActivity(this);

        FragmentManager fm = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.container_main);

        if(mainFragment == null) {
            mainFragment = MainFragment.newInstance("blah", "blah");
            fm.beginTransaction().add(R.id.container_main, mainFragment).commit();
        }
    }

    public void loadPlaylistScreen(Station selectedStation) {
        setContentView(R.layout.activity_main);
        MainActivity.setMainActivity(this);

        PlaylistSongBarMerge playlistSongBarMerge;
        playlistSongBarMerge = PlaylistSongBarMerge.newInstance(selectedStation);
//        PlaylistFragment playlistFragment;
//        playlistFragment = PlaylistFragment.newInstance(selectedStation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, playlistSongBarMerge).addToBackStack(null).commit();
    }

    public void loadSong(Song song) {
        Log.d("MAIN_ACTIVITY", "loadSongScreen");

        setContentView(R.layout.activity_main);
        MainActivity.setMainActivity(this);

        SongFragment songFragment = SongFragment.newInstance(song);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, songFragment).addToBackStack(null).commit();
    }

    @Override
    public void respond(Song song) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SongFragment songFragment = (SongFragment) fragmentManager.findFragmentById(R.id.songBar_Fragment);
        songFragment.changeSong(song);
    }
}
