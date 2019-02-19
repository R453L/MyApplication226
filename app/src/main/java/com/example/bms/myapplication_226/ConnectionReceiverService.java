package com.example.bms.myapplication_226;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectionReceiverService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s;
        if(intent.getAction().equals("k")){
            Toast.makeText(context,"some action is received.",Toast.LENGTH_SHORT).show();
        }else{
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
            if(isConnected){
                try{
                    Toast.makeText(context,"Network is connected.",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(context,"Network is changed or reconnected.",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
