package com.ac.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anna on 2017/12/5.
 */

public class LiveDataActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv);

        LocationLiveData.getInstance(this).observe(this, location -> {
            // update UI
            mTextView.setText(location.getLatitude() + ", " + location.getLongitude());
        });

    }
}
