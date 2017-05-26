package com.example.nathanwelch.herome.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;

import com.example.nathanwelch.herome.Fragments.BackstoryFragment;
import com.example.nathanwelch.herome.Fragments.MainFragment;
import com.example.nathanwelch.herome.Fragments.PickPowerFragment;
import com.example.nathanwelch.herome.R;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentInteractionListener, PickPowerFragment.PickPowerInteractionListener, BackstoryFragment.BackstoryInteractionListener {

    public void sendText(String text) {
        BackstoryFragment backstoryFragment = (BackstoryFragment) getSupportFragmentManager().findFragmentById(R.id.backstoryFragment);
        backstoryFragment.updateText(text);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this manages all the fragments created by the app
        FragmentManager manager = getSupportFragmentManager();
        //finds the container that will be replaced by the fragment
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = new MainFragment();
            /* anytime the manager is used, you
                  -> start by "beginning" a transaction
                  -> end by "committing" the transaction  */
            // ".add~~~"" will add the transaction to the screen
            /*
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                    -> This and the line below do the same thing.
                       This is just split up to better show what is happening
             */
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }
    }

    public void loadPickPowerScreen() {
        PickPowerFragment pickPowerFragment = new PickPowerFragment();
        /*  begins a transaction
            replaces the old transaction from the fragment_container with the new one (pickPowerFragment)
            then adds the old one to the back stack
            and commits everything
         */
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pickPowerFragment).addToBackStack(null).commit();
    }

    public void loadBackstoryScreen() {
        BackstoryFragment backstoryFragment = new BackstoryFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, backstoryFragment).addToBackStack(null).commit();
    }

    public void loadMainScreen() {
        MainFragment mainFragment = new MainFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainFragment).addToBackStack(null).commit();
    }


//    public void loadBackstoryScreen(String enabledBtn) {
//        Intent intent = new Intent(MainActivity.this, BackstoryFragment.class);
//        intent.putExtra(MainActivity.EXTRA_ITEM_TITLE, enabledBtn);
//        startActivity(intent);
//    }


    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPickPowerFragmentInteraction(String button) {

    }

    @Override
    public void onBackstoryFragmentInteraction(Uri uri) {

    }
}
