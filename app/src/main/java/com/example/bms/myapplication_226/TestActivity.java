package com.example.bms.myapplication_226;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity {

    EditText edtTest;
    Button btnSwitchActivity;
    Button btnResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edtTest.findViewById(R.id.edtTest);
        btnSwitchActivity.findViewById(R.id.btnSwitchActivity);
        btnResultView.findViewById(R.id.btnResultView);
    }
}
