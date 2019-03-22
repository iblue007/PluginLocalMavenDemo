package com.xqx.demo1;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DemoMainActivity extends AppCompatActivity {

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable mCallback = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.xqx.testdemo","com.xqx.testdemo.MainActivity"));
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);
        handler.postDelayed(mCallback,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mCallback);
    }
}
