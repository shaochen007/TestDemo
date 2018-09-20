package com.example.chenyong.testdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cy.customwidget.NumPercent;

public class MainActivity extends AppCompatActivity {

    private int mProgressValue = 1;
    private int mTotalValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NumPercent numPercent = findViewById(R.id.num_percent);
        numPercent.setTotalValue(mTotalValue);
        numPercent.setProgressValue(mProgressValue);

        findViewById(R.id.btn_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPercent.setProgressValue(++mProgressValue);
            }
        });

        findViewById(R.id.btn_total).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPercent.setTotalValue(++mTotalValue);
            }
        });
    }
}
