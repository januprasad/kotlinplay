package com.example.work_manager_example.location;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.work_manager_example.R;
import com.example.work_manager_example.databinding.ActivityLocationBinding;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.POST_NOTIFICATIONS;

public class LocationActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;

    private static final String TAG = "LocationUpdate";

    ActivityLocationBinding locationBinding;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationBinding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(locationBinding.getRoot());
        if (!checkLocationPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_COARSE_LOCATION,
                            ACCESS_FINE_LOCATION,
                            POST_NOTIFICATIONS
                    },
                    PERMISSION_REQUEST_CODE);
        }


        try {
            boolean og = isWorkScheduled(WorkManager.getInstance(this).getWorkInfosByTag(TAG).get());
            Log.v("App", og + "");
            if (isWorkScheduled(WorkManager.getInstance(this).getWorkInfosByTag(TAG).get())) {
                locationBinding.appCompatButtonStart.setText(getString(R.string.button_text_stop));
                locationBinding.message.setText(getString(R.string.message_worker_running));
                locationBinding.logs.setText(getString(R.string.log_for_running));
            } else {
                locationBinding.appCompatButtonStart.setText(getString(R.string.button_text_start));
                locationBinding.message.setText(getString(R.string.message_worker_stopped));
                locationBinding.logs.setText(getString(R.string.log_for_stopped));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        locationBinding.appCompatButtonStart.setOnClickListener(v -> {
            if (locationBinding.appCompatButtonStart.getText().toString().equalsIgnoreCase(getString(R.string.button_text_start))) {
                // START Worker
                PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(LocationWorker.class,
                        15, TimeUnit.MINUTES)
                        .addTag(TAG)
                        .build();
                WorkManager.getInstance(this).enqueueUniquePeriodicWork("Location",
                        ExistingPeriodicWorkPolicy.UPDATE, periodicWork);

                Toast.makeText(LocationActivity.this, "Location Worker Started : " + periodicWork.getId(), Toast.LENGTH_SHORT).show();

                locationBinding.appCompatButtonStart.setText(getString(R.string.button_text_stop));
                locationBinding.message.setText(periodicWork.getId().toString());
                locationBinding.logs.setText(getString(R.string.log_for_running));
            } else {
                WorkManager.getInstance(this).cancelAllWorkByTag(TAG);
                locationBinding.appCompatButtonStart.setText(getString(R.string.button_text_start));
                locationBinding.message.setText(getString(R.string.message_worker_stopped));
                locationBinding.logs.setText(getString(R.string.log_for_stopped));
            }
        });
    }

    private boolean isWorkScheduled(List<WorkInfo> workInfos) {
        boolean running = false;
        if (workInfos == null || workInfos.isEmpty()) return false;
        for (WorkInfo workStatus : workInfos) {
            running = workStatus.getState() == WorkInfo.State.RUNNING |
                    workStatus.getState() == WorkInfo.State.ENQUEUED;
        }
        return running;
    }

    /**
     * All about permission
     */
    private boolean checkLocationPermission() {
        int result3 = ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION);
        int result4 = ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);
        int result5 = ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS);
        return result3 == PackageManager.PERMISSION_GRANTED &&
                result4 == PackageManager.PERMISSION_GRANTED &&
                result5 == PackageManager.PERMISSION_GRANTED
                ;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean coarseLocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean fineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean postNotification = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                if (coarseLocation && fineLocation && postNotification)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}