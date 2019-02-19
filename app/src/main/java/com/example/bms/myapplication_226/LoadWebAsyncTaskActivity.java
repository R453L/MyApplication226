package com.example.bms.myapplication_226;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class LoadWebAsyncTaskActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_web_async_task);

        editText = findViewById(R.id.edtURL);
        editText.setText("https://");
        Selection.setSelection(editText.getText(),editText.getText().length());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("https://")){
                    editText.setText("https://");
                    Selection.setSelection(editText.getText(),editText.getText().length());
                }
            }
        });

        findViewById(R.id.btnLoadWebPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadWebPageAsync task = new LoadWebPageAsync();
                task.execute(editText.getText().toString());
            }
        });
        findViewById(R.id.btnDummy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoadWebAsyncTaskActivity.this,
                        "Clicked!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    class LoadWebPageAsync extends AsyncTask<String,Void,String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(
                    LoadWebAsyncTaskActivity.this,
                    "Loading...",
                    "Please wait...");
        }

        @Override
        protected String doInBackground(final String... strings) {
            final WebView webView = findViewById(R.id.webView);
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.setWebViewClient(new MyBrowser());
                    webView.getSettings().setLoadsImagesAutomatically(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                    webView.loadUrl(strings[0]);
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }
    class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
