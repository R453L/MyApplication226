package com.example.bms.myapplication_226;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConnectionReceiverActivity extends AppCompatActivity {

    ConnectionReceiverService receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_receiver);

        receiver = new ConnectionReceiverService();
        intentFilter = new IntentFilter("k");

        findViewById(R.id.btnConnectionReceiverStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("k");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
