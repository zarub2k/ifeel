package com.cloudskol.ifeel.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloudskol.ifeel.R;

public class SplashActivity extends AppCompatActivity {
    private static int TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        navigateToMain();
    }

    private void navigateToMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, TIMEOUT);

    }
}
