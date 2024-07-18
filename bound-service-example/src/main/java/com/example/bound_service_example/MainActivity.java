package com.example.bound_service_example;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyBoundService myBoundService;
    boolean isBound = false;
    private final ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder binderBridge = (MyBoundService.MyBinder) service;
            myBoundService = binderBridge.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            myBoundService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        startService(intent);
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, String.valueOf(myBoundService.randomGenerator()), Toast.LENGTH_SHORT).show();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 3000);

        /**
         * forground service
         */

        Intent intent = new Intent(this, MyForegroundService.class);
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(boundServiceConnection);
            isBound = false;


        }
    }

}
