package com.example.diagnoseapp;

import android.content.Context;
import android.hardware.*;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SensorDetailActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_detail);

        tv = findViewById(R.id.sensorData);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        int type = getIntent().getIntExtra("type", Sensor.TYPE_ACCELEROMETER);
        sensor = sensorManager.getDefaultSensor(type);

        if (sensor == null) {
            tv.setText("❌ No Sensor Available on this device");
        } else {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String data = "X: " + event.values[0];

        if (event.values.length > 1)
            data += "\nY: " + event.values[1];

        if (event.values.length > 2)
            data += "\nZ: " + event.values[2];

        tv.setText(data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}