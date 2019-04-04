package com.example.bms.myapplication_226;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyScheduleJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this,"Job Started.",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,JobSchedulerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Job Scheduler Notification");
        builder.setContentText("Scheduled job has started successfully.");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        notificationManager.notify(0,builder.build());

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
