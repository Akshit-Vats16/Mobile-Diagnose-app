package com.example.diagnoseapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnInfo).setOnClickListener(v ->
                startActivity(new Intent(this, DeviceInfoActivity.class)));

        findViewById(R.id.btnSensors).setOnClickListener(v ->
                startActivity(new Intent(this, SensorListActivity.class)));

        findViewById(R.id.btnStorage).setOnClickListener(v ->
                startActivity(new Intent(this, StorageActivity.class)));

        findViewById(R.id.btnCallLogs).setOnClickListener(v ->
                startActivity(new Intent(this, CallLogActivity.class)));
    }
}