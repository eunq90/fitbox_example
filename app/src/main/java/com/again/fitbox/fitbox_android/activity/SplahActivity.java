package com.again.fitbox.fitbox_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.again.fitbox.fitbox_android.R;
import com.again.fitbox.fitbox_android.util.FitPreference;

public class SplahActivity extends AppCompatActivity {

    public static Context mContext;
    FitPreference pref;

     public static Context getAppContext() { return SplahActivity.mContext; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        mContext = getApplicationContext();


        pref = new FitPreference(mContext);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 3000);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
