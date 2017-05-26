package com.httvc.androiddemo.ui.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.httvc.androiddemo.R;
import com.httvc.androiddemo.api.RestPool;

public class StarcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starcast);
        RestPool.getInstance().getService().starcast("756571ec84bad655");
    }
}
