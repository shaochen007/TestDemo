package com.chenyong.testdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.chenyong.testdemo.R;
import com.example.chenyong.testdemo.databinding.ActivityHttpBinding;
import com.chenyong.testdemo.util.ToastUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpActivity extends AppCompatActivity {
    private ActivityHttpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_http);



        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getApplicationContext(),"111");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        urlConnection();
                    }
                }).start();
            }
        });

    }

    private void urlConnection(){
        String URL = "https://www.baidu.com";
//        String URL = "http://zh.wikipedia.org/w/api.php?action=opensearch&search=Android";
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("","连接成功");
//                ToastUtils.showToast(getApplicationContext(),"连接成功");
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(),"utf-8");
                int i;
                String str = "";
                if ((i=inputStreamReader.read())!=-1){
                    str = str+(char) i;
                }
                inputStreamReader.close();
                Log.d("","str:"+str);
//                binding.trvContent.setText(str);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void httpClient(){
        String URL = "";

    }
}
