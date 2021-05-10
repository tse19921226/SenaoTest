package com.example.senaotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private ExecutorService executorService;
    private DataSync dataSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSync();
    }

    private void dataSync(){
        dataSync = new DataSync();
        dataSync.setmCtx(getApplicationContext());
        dataSync.execute(DataManager.senaoUrl);
    }
}