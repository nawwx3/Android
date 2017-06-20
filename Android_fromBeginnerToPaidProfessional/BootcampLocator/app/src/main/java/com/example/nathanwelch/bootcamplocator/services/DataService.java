package com.example.nathanwelch.bootcamplocator.services;

import com.example.nathanwelch.bootcamplocator.model.Devslopes;

import java.util.ArrayList;

/**
 * Created by Nathan Welch on 6/19/2017.
 */

public class DataService {
    private static final DataService Instance = new DataService();

    public static DataService getInstance() {
        return Instance;
    }

    private DataService() {
    }

    public ArrayList<Devslopes> getBootcampLocationsWithin10MilesOfZip(int zipcode) {
        //pretend we are downloading data from the server

        ArrayList<Devslopes> list = new ArrayList<>();
        list.add(new Devslopes(38.798746f, -90.5025365f, "Jaycee Park", "2805 Elm St, St Charles, MO 63301, USA", "park"));
        list.add(new Devslopes(38.8050601f, -90.4924525f, "Coverdell Elementary School", "2475 W Randolph St, St Charles, MO 63301, USA", "coverdell"));
        list.add(new Devslopes(38.802447f, -90.489507f, "Borromeo Cemetery", "W Randolph St, St Charles, MO 63301, USA", "borromeo"));
        return list;
    }

}
