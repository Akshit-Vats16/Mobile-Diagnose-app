package com.example.diagnoseapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeviceInfoActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        tv = findViewById(R.id.deviceInfoText);

        String info = "";

        // Device Info
        info += "Brand: " + Build.BRAND + "\n";
        info += "Model: " + Build.MODEL + "\n";
        info += "Manufacturer: " + Build.MANUFACTURER + "\n\n";

        // OS Info
        info += "Android Version: " + Build.VERSION.RELEASE + "\n";
        info += "SDK: " + Build.VERSION.SDK_INT + "\n\n";

        // Battery Info
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        android.content.Intent batteryStatus = registerReceiver(null, iFilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float) scale;

        info += "Battery: " + batteryPct + "%\n\n";

        // RAM Info
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memInfo);

        long totalRam = memInfo.totalMem / (1024 * 1024);
        long availRam = memInfo.availMem / (1024 * 1024);

        info += "Total RAM: " + totalRam + " MB\n";
        info += "Available RAM: " + availRam + " MB\n\n";

        // Network Info
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            info += "Network: Connected\n";
            info += "Type: " + activeNetwork.getTypeName() + "\n";
        } else {
            info += "Network: Not Connected\n";
        }

        tv.setText(info);
    }
}