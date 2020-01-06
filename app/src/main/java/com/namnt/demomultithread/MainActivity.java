package com.namnt.demomultithread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.namnt.demomultithread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setNumber(10);


        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                mainBinding.setNumber(msg.arg1);
            }
        };
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        mainBinding.setClick(new ClickListener(10,mainBinding,handler));

    }

}
