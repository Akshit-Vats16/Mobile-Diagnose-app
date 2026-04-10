package com.example.diagnoseapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SensorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        findViewById(R.id.btnAccel).setOnClickListener(v -> openSensor(Sensor.TYPE_ACCELEROMETER));
        findViewById(R.id.btnGyro).setOnClickListener(v -> openSensor(Sensor.TYPE_GYROSCOPE));
        findViewById(R.id.btnLight).setOnClickListener(v -> openSensor(Sensor.TYPE_LIGHT));
        findViewById(R.id.btnProximity).setOnClickListener(v -> openSensor(Sensor.TYPE_PROXIMITY));
    }

    void openSensor(int type) {
        Intent intent = new Intent(this, SensorDetailActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}