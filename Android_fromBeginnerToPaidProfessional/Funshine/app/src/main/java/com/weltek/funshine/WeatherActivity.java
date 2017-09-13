package com.weltek.funshine;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.weltek.funshine.model.DailyWeatherReport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.country;
import static android.R.attr.x;

public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener{

    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORD = "?lat=";//"?lat=9.9687&lon=76.299";
    final String URL_UNITS = "&units=imperial";
    final String URL_API_KEY = "&APPID=31e0228c785a2069b5830e349d865a40";

    private final int PERMISSION_LOCATION = 111;
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<DailyWeatherReport> weatherReportList = new ArrayList<>();

    private ImageView weatherIcon;
    private ImageView weatherIconMini;
    private TextView weatherDate;
    private TextView currentTemp;
    private TextView lowTemp;
    private TextView cityCountry;
    private TextView weatherDescription;

    WeatherAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView)findViewById(R.id.weatherIcon);
        weatherIconMini = (ImageView)findViewById(R.id.weatherIconMini);
        weatherDate = (TextView)findViewById(R.id.weatherDate);
        currentTemp = (TextView)findViewById(R.id.currentTemp);
        lowTemp = (TextView)findViewById(R.id.lowTemp);
        cityCountry = (TextView)findViewById(R.id.cityCountry);
        weatherDescription = (TextView)findViewById(R.id.weatherDescription);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.content_weather_reports);
        mAdapter = new WeatherAdapter(weatherReportList);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    public void downloadWeatherData(Location location) {
        final String fullCoords = URL_COORD + location.getLatitude() + "&lon=" + location.getLongitude();
        final String url = URL_BASE + fullCoords + URL_UNITS + URL_API_KEY;

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.v("NEWW", "Enter onResponse");


                String month = "";
                String day = "0";

                try {
                    JSONObject city = response.getJSONObject("city");
                    String cityName = city.getString("name");
                    String country = city.getString("country");

                    JSONArray list = response.getJSONArray("list");


                    for(int x = 0; x < 35; x++){
//                    if(weatherReportList.size() < 5) {

                        JSONObject obj = list.getJSONObject(x);

                        JSONObject main = obj.getJSONObject("main");
                        Double currentTemp = main.getDouble("temp");
                        Double maxTemp = main.getDouble("temp_max");
                        Double minTemp = main.getDouble("temp_min");

                        JSONArray weatherArr = obj.getJSONArray("weather");
                        JSONObject weather = weatherArr.getJSONObject(0);
                        String weatherType = weather.getString("main");

                        String rawDate = obj.getString("dt_txt");

                        String[] monthDayTime = rawDate.split("-");
                        String[] dayTime = monthDayTime[2].split(" ");
                        Log.v("STUCK", "printing monthDayTime: " + monthDayTime[0] + " " + monthDayTime[1] + " " + monthDayTime[2]);
                        Log.v("STUCK", "printing dayTime: " + dayTime[0] +  " " + dayTime[1]);


                        //checks that the day for each of the entries is different
                        if(!day.equals(dayTime[0])  ) {
                            day = dayTime[0];
                            rawDate = monthDayTime[1] + " " + day;
                            Log.v("STUCK", "printing rawDate: " + rawDate);
                            DailyWeatherReport report = new DailyWeatherReport(cityName, country, currentTemp.intValue(), maxTemp.intValue(), minTemp.intValue(), weatherType, rawDate);
                            Log.v("JSON", "printing from class " + report.getWeather() + " country: " + report.getCityName() );

                            weatherReportList.add(report);
                        }


                    }

                    Log.v("JSON", "name " + cityName + " country: " + country );
                } catch (JSONException e) {
                    Log.v("JSON", "EXC: " + e.getLocalizedMessage());
                }

                updateUI();
                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("FUN", "Err: " + error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }



//    private ImageView weatherIcon;
//    private ImageView weatherIconMini;
//    private TextView weatherDate;
//    private TextView currentTemp;
//    private TextView lowTemp;
//    private TextView cityCountry;
//    private TextView weatherDescription;

    public void updateUI() {

        if(weatherReportList.size() > 0) {
            DailyWeatherReport report = weatherReportList.get(0);

            switch (report.getWeather()) {
                case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_RAIN:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_SNOW:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                    break;
//                case DailyWeatherReport.WEATHER_TYPE_WIND:
                //NO ICON FOR WIND
//                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.));
//                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
//                    break;
                default:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
            }
            weatherDate.setText(report.getFormattedDate());
            currentTemp.setText(Integer.toString(report.getCurrentTemp()) + "°");
            lowTemp.setText(Integer.toString(report.getMintTenp()) + "°");
            cityCountry.setText(report.getCityName() + ", " + report.getCountry());
            weatherDescription.setText(report.getWeather());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        downloadWeatherData(location);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
        } else {
            startLocationServices();
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void startLocationServices() {
        try {
            LocationRequest request = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, request, this);
        } catch (SecurityException ex) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_LOCATION: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationServices();
                } else {
                    //show dialogue says can't run, no permissions
                    Toast.makeText(this, "I can't run your location - denied permissions", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public class WeatherAdapter extends RecyclerView.Adapter<WeatherReportsViewHolder> {
        private ArrayList<DailyWeatherReport> mDailyWeatherReports;

        public WeatherAdapter(ArrayList<DailyWeatherReport> mDailyWeatherReports) {
            this.mDailyWeatherReports = mDailyWeatherReports;
        }

        @Override
        public int getItemCount() {
            return mDailyWeatherReports.size();
        }

        @Override
        public void onBindViewHolder(WeatherReportsViewHolder holder, int position) {
            DailyWeatherReport report = mDailyWeatherReports.get(position);
            holder.updateUI(report);
        }

        @Override
        public WeatherReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);
            return new WeatherReportsViewHolder(card);
        }
    }

    public class WeatherReportsViewHolder extends RecyclerView.ViewHolder {
        private ImageView lweatherIcon;
        private TextView lweatherDate;
        private TextView lweatherDescription;
        private TextView ltempHigh;
        private TextView ltempLow;

        public WeatherReportsViewHolder(View itemView) {
            super(itemView);

            lweatherIcon = (ImageView)itemView.findViewById(R.id.list_weather_icon);
            lweatherDate = (TextView)itemView.findViewById(R.id.list_weather_day);
            lweatherDescription = (TextView)itemView.findViewById(R.id.list_weather_description);
            ltempHigh = (TextView)itemView.findViewById(R.id.list_weather_temp_high);
            ltempLow = (TextView)itemView.findViewById(R.id.list_weather_temp_low);

        }

        public void updateUI(DailyWeatherReport report) {

            lweatherDate.setText(report.getFormattedDate());
            lweatherDescription.setText(report.getWeather());
            ltempHigh.setText(Integer.toString(report.getMaxTemp()));
            ltempLow.setText(Integer.toString(report.getMintTenp()));

            switch (report.getWeather()) {
                case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy_mini));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_RAIN:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy_mini));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_SNOW:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow_mini));
                    break;
//                case DailyWeatherReport.WEATHER_TYPE_WIND:
                //NO ICON FOR WINDY
//                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.));
//                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
//                    break;
                default:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.sunny_mini));
            }
        }
    }
}
