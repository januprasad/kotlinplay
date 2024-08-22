package com.example.work_manager_example.location

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.work_manager_example.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale

class LocationWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun doWork(): Result {
        requestLocationUpdates()
        return success()
    }

    private fun requestLocationUpdates() {
        val locationRequest =
            LocationRequest
                .Builder(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    10000L,
                ).setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(5000L)
                .setMaxUpdateDelayMillis(20000L)
                .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.v("App", "Access denied permission")
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private val locationCallback =
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                if (location != null) {
                    // Use the updated location data
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Update UI with the location data
                    Log.v("App", "$latitude $longitude")
                    sendLocationNotification(location)
                }
            }
        }

    private fun sendLocationNotification(location: Location) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = context.getString(R.string.app_name)
            val description: String = context.getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(context.getString(R.string.app_name), name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager: NotificationManager =
                context.getSystemService<NotificationManager>(
                    NotificationManager::class.java,
                )
            notificationManager.createNotificationChannel(channel)
        }

        val builder: NotificationCompat.Builder =
            NotificationCompat
                .Builder(context, context.getString(R.string.app_name))
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setContentTitle("New Location Update")
                .setContentText(
                    "You are at " +
                        getCompleteAddressString(
                            location.latitude,
                            location.longitude,
                        ),
                ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(
                    NotificationCompat.BigTextStyle().bigText(
                        "You are at " +
                            getCompleteAddressString(
                                location.latitude,
                                location.longitude,
                            ),
                    ),
                )

        val notificationManager = NotificationManagerCompat.from(context)

        // notificationId is a unique int for each notification that you must define
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.v("App", "Access denied permission")
            return
        }
        notificationManager.notify(1001, builder.build())

        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun getCompleteAddressString(
        LATITUDE: Double,
        LONGITUDE: Double,
    ): String {
        var strAdd = ""
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null) {
                val returnedAddress = addresses[0]
                val strReturnedAddress = StringBuilder()

                for (i in 0..returnedAddress.maxAddressLineIndex) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return strAdd
    }
}
