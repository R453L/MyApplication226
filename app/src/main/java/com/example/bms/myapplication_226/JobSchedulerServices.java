package com.example.bms.myapplication_226;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobSchedulerServices extends JobService {
    public static final String TAG = "JobSchedulServices";
    Button btn;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Params: "+params.getJobId());

        Toast.makeText(this,"Job Scheduler Started.",Toast.LENGTH_SHORT).show();

        if(params.getJobId()==0){
            //Notification
            Intent intent = new Intent(this,JobSchedulerServicesActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("Job Scheduler Notification");
            builder.setContentText("Job Scheduler Notification When the device is charging!");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            notificationManager.notify(0,builder.build());

            jobFinished(params,false);
        }else if(params.getJobId()==1){
            MediaPlayer mp;
            MediaMetadataRetriever mmr;

            mp = new MediaPlayer();

            mp.reset();
            mp = MediaPlayer.create(this,R.raw.mini_world);
            mp.start();
        }

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
