package com.example.mockito;

import android.content.Context;
import android.location.LocationManager;

class EditChecker {

    public static boolean hazNoError(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean locationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return locationEnabled;
    }
}