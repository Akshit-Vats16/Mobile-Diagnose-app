package com.example.diagnoseapp;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class StorageActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        tv = findViewById(R.id.storageText);

        String info = "";

        // Internal Storage
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        long totalStorage = (totalBlocks * blockSize) / (1024 * 1024);
        long availableStorage = (availableBlocks * blockSize) / (1024 * 1024);
        long usedStorage = totalStorage - availableStorage;

        info += "Total Storage: " + totalStorage + " MB\n";
        info += "sed Storage: " + usedStorage + " MB\n";
        info += "Available Storage: " + availableStorage + " MB\n\n";

        // RAM Info
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memInfo);

        long totalRam = memInfo.totalMem / (1024 * 1024);
        long availRam = memInfo.availMem / (1024 * 1024);
        long usedRam = totalRam - availRam;

        info += "Total RAM: " + totalRam + " MB\n";
        info += "Used RAM: " + usedRam + " MB\n";
        info += "Available RAM: " + availRam + " MB\n";

        tv.setText(info);
    }
}