package com.example.nathanwelch.devslopesradio.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nathanwelch.devslopesradio.R;
import com.example.nathanwelch.devslopesradio.fragments.PlaylistFragment;
import com.example.nathanwelch.devslopesradio.fragments.MainFragment;
import com.example.nathanwelch.devslopesradio.fragments.SongFragment;
import com.example.nathanwelch.devslopesradio.model.Song;
import com.example.nathanwelch.devslopesradio.model.Station;

public class MainActivity extends AppCompatActivity {

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    private static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    private static MainActivity mainActivity;

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

        PlaylistFragment playlistFragment;
        playlistFragment = PlaylistFragment.newInstance(selectedStation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, playlistFragment).addToBackStack(null).commit();
    }

    public void loadSongScreen(Song song) {
        Log.d("MAIN_ACTIVITY", "loadSongScreen");

        setContentView(R.layout.activity_main);
        MainActivity.setMainActivity(this);

        SongFragment songFragment = SongFragment.newInstance(song);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, songFragment).addToBackStack(null).commit();
    }
}
