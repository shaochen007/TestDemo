package com.example.chenyong.testdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cy.customwidget.NumPercent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NumPercent numPercent = findViewById(R.id.num_percent);
        numPercent.setTotalValue(20);

        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPercent.setTotalValue(10);
                numPercent.setProgressValue((int) (Math.random() * 10));
            }
        });
    }
}
