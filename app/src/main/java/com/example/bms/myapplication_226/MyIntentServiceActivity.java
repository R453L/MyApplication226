package com.example.bms.myapplication_226;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyIntentServiceActivity extends AppCompatActivity {

    public static final String FILTER_ACTION_KEY = "any_key";

    TextView textView;
    EditText editText;
    Button button;
    protected MyReceiver myReceiver;

    @Override
    protected void onStart() {
        setReceiver();
        super.onStart();
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(myReceiver);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intent_service);

        textView = findViewById(R.id.tvIntentService);
        editText = findViewById(R.id.edtIntentService);
        button = findViewById(R.id.btnStartIntentService);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                Intent intent = new Intent(MyIntentServiceActivity.this,MyIntentService.class);
                intent.putExtra("message",message);
                startService(intent);
            }
        });
    }
    void setReceiver(){
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FILTER_ACTION_KEY);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(myReceiver, intentFilter);

    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("broadcastMessage");
            textView.setText(textView.getText()+"\n"+message);
        }
    }
}
