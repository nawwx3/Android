package com.example.nathanwelch.bootcamplocator.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.nathanwelch.bootcamplocator.R;
import com.example.nathanwelch.bootcamplocator.fragment.MainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener{
    final int PERMISSION_LOCATION = 111;
    private GoogleApiClient mGoogleApiClient;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();



        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.container_main);

        if(mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.container_main, mainFragment).commit();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    //is called when Google Play Services has been connected
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //checking for permission
        //ACCESS_FINE_LOCATION(1) is more accurate than ACCESS_COURSE_LOCATION(2) -> 1 includes 2, but not vice versa
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //if it does not exist it will request permission
            //but nothing is popping up on the screen

            /*  PERMISSION_LOCATION is just a number so that we know what permission to check for/that we are working with
                defined as a constant in MainActivity
             */
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
            Log.v("DONKEY", "Requesting Permissions");
        } else {
            startLocationServices();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("DONKEY" , "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
        mainFragment.setUserMarker(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    protected void onStart() {
        //when app is started -> connects to the client
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        //when app is stopped -> disconnects so its not running in the background wasting battery
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /*  takes the permission code from the "requestPermissions()" call
        then inside we check the if the permission is being requested is permission_x
        then do this stuff
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode) {
            case PERMISSION_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if permissions have been granted &&
                    //the first permissions have been granted
                    startLocationServices();
                    Log.v("DONKEY", "Permission Granted");
                } else {
                    //show a dialog saying something like, "I can't show your location - you denied permission, silly goose!"
                    Log.v("DONKEY", "Permission Denied");
                }
            }
        }
    }

    public void startLocationServices() {
        Log.v("DONKEY", "starting location services called");

        /*Google Play Services requires explicit consent from the user to be able to use its features
          So, we throw in the try-catch block. This will throw an error if the user rejected permission
        */
        try {
            Log.v("DONKEY", "Requesting location updates");

            /*PRIORITY_LOW_POWER -> doesn't use very much power, but is less accurate
              can use other power settings (_MEDIUM_ and _HIGH_ which use more power but are
              also more accurate -> HIGH is accurate to a few feet
            */
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            //this -> THIS class is going to listen for the update
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
        } catch (SecurityException exception) {
            //Show dialogue to users showing they can't get location unless they give app permissions
            Log.v("DONKEY", exception.toString());
        }
    }


}
