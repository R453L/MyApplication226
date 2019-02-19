package com.example.bms.myapplication_226;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        editText = findViewById(R.id.edtTimeToSleep);
        textView = findViewById(R.id.tvResult);

        findViewById(R.id.btnStartAsyncTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask = new MyTask();
                myTask.execute(editText.getText().toString());
            }
        });
    }
    private class MyTask extends AsyncTask<String,Void,String>{
        String result;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(
                    AsyncTaskActivity.this,
                    "Progress Dialog",
                    "Wait for "+
                            editText.getText().toString() +
                            " seconds.");
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                int time = Integer.parseInt(strings[0]);
                Thread.sleep(time*1000);
                result = "Slept for "+time+" seconds.";
            } catch (InterruptedException e) {
                e.printStackTrace();
                result = e.getMessage();
            } catch (Exception e){
                e.printStackTrace();
                result = e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            textView.setText(result);
        }
    }
}
