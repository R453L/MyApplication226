package com.example.bms.myapplication_226;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DynamicPermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_permission);

        findViewById(R.id.btnCheckPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v;
                if(checkPermission()){
                    Snackbar.make(view,"Permission already granted.",Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(view,"Please request permission.",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btnRequestPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v;
                if(!checkPermission()){
                    requestPermission();
                }else{
                    Snackbar.make(view,"Permission already granted.",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA);
        int result1= ContextCompat.checkSelfPermission(getApplicationContext(),ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        //String[] perms = {"android.permission.FINE_LOCATION", "android.permission.CAMERA"};
        String perms[] = {CAMERA,ACCESS_FINE_LOCATION};

        ActivityCompat.requestPermissions(this,perms,PERMISSION_REQUEST_CODE);
    }

    private void showMessageOkCancel(String msg, DialogInterface.OnClickListener okListener){
        new AlertDialog.Builder(DynamicPermissionActivity.this)
                .setMessage(msg)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0){
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean locationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if(cameraAccepted && locationAccepted){
                    Snackbar.make(view,"Permission Granted for Camera and Location.",Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(view,"Permission Denied for Camera and Location.",Snackbar.LENGTH_SHORT).show();

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)){

                            showMessageOkCancel("You need to allow to access both permissions.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                requestPermissions(new String[]{CAMERA,ACCESS_FINE_LOCATION},
                                                        PERMISSION_REQUEST_CODE);
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        }

    }
}
