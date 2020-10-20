package com.example.bms.myapplication_226;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobSchedulerServicesActivity extends AppCompatActivity {

    JobInfo jobInfo;
    JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler_services);

        findViewById(R.id.btnJobNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJobNotification();
            }
        });
        findViewById(R.id.btnJobNotificationCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopJob();
            }
        });
        findViewById(R.id.btnJobMusic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJobMusic();
            }
        });
        findViewById(R.id.btnJobMusicStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopJob();
            }
        });
    }
    public void startJobNotification(){
        jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,JobSchedulerServices.class);

        JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(0,componentName);

        //jobInfoBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobInfoBuilder.setRequiresCharging(true);
        jobInfoBuilder.setRequiresDeviceIdle(false);

        jobInfo = jobInfoBuilder.build();
        jobScheduler.schedule(jobInfo);
    }
    public void stopJob(){
        if(jobScheduler!=null){
            jobScheduler.cancelAll();
            jobScheduler = null;
            Toast.makeText(this,"Scheduled job cancelled.",Toast.LENGTH_SHORT).show();
        }
    }
    public void startJobMusic(){
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,JobSchedulerServices.class);

        JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(1,componentName);

        jobInfoBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobInfoBuilder.setRequiresCharging(false);
        jobInfoBuilder.setRequiresDeviceIdle(false);

        jobInfo = jobInfoBuilder.build();
        jobScheduler.schedule(jobInfo);
    }
}
