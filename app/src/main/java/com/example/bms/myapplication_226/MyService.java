package com.example.bms.myapplication_226;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(),"Service Created",Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(this,R.raw.must_be_love);
        mPlayer.setLooping(false);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(getApplicationContext(),"Service Started",Toast.LENGTH_SHORT).show();
        mPlayer.start();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"Service Stopped",Toast.LENGTH_SHORT).show();
        mPlayer.stop();
    }
}
