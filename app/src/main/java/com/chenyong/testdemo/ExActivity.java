package com.chenyong.testdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chenyong.testdemo.R;
import com.example.chenyong.testdemo.databinding.ActivityExBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ExActivity extends AppCompatActivity {

    private ActivityExBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ex);
        binding.setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_rx:
                        startActivity(new Intent(ExActivity.this,RxJavaActivity.class));
                        break;
                    case R.id.btn_http:
                        startActivity(new Intent(ExActivity.this,HttpActivity.class));
                        break;
                    case R.id.btn_num_percent:
                        startActivity(new Intent(ExActivity.this,MainActivity.class));
                        break;
                    case R.id.btn_wx_qrcode:
                        startActivity(new Intent(ExActivity.this,WxScanLoginActivity.class));
                        break;
                }
            }
        });
    }
}
