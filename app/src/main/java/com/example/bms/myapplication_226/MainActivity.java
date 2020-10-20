package com.example.bms.myapplication_226;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null) updateUI(user);
        else {

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.AnonymousBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.icon_8)
                            .setTheme(R.style.MyTheme)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                updateUI(user);

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Toast.makeText(getApplicationContext(),"Sign In failed!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        Toast.makeText(getApplicationContext(),"Signed In",Toast.LENGTH_SHORT).show();

        TextView tvUser = findViewById(R.id.tvUser);
        tvUser.setText("Welcome, "+user.getDisplayName());

        findViewById(R.id.btnRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyclerViewActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnSurfaceView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySurfaceView mSurfaceView;
                mSurfaceView = new MySurfaceView(MainActivity.this);
                setContentView(mSurfaceView);
            }
        });
        findViewById(R.id.btnService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ServiceActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnBoundService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BoundServiceActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnIntentService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyIntentServiceActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnConnectionService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ConnectionReceiverActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnAsyncTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnAsyncTaskLoadWebPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoadWebAsyncTaskActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnDynamicPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DynamicPermissionActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnMusicActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListViewMusicActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnPersonRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecyclerViewAddRemoveActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnTestActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnDatabaseActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DatabaseActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnJobScheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,JobSchedulerActivity.class));
                //finish();
            }
        });
        findViewById(R.id.btnJobSchedulerServices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,JobSchedulerServicesActivity.class));
                //finish();
            }
        });
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_signout]
    }

}
