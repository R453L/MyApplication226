package com.example.bms.myapplication_226;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class JobSchedulerActivity extends AppCompatActivity {

    JobInfo jobInfo;
    JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        findViewById(R.id.btnStartJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJob(v);
            }
        });
    }

    public void startJob(View view){
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,MyScheduleJobService.class);

        JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(0,componentName);

        jobInfoBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobInfoBuilder.setRequiresCharging(true);
        jobInfoBuilder.setRequiresDeviceIdle(false);

        jobInfo = jobInfoBuilder.build();

        jobScheduler.schedule(jobInfo);
    }

    public void stopJob(View view){
        if (jobScheduler != null){
            jobScheduler.cancelAll();
            jobScheduler = null;
            Toast.makeText(this,"Job Cancelled.",Toast.LENGTH_SHORT).show();
        }
    }
}
