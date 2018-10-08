package com.example.socketanother;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import static android.os.Build.HOST;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, SocketAnotherServerService.class));
        startService(new Intent(this, SocketServerService.class));

        findViewById(R.id.btn_command).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                SocketCommandUtils.send("localhost", 8075, "发送指令：" + Math.random());
            }
        });
    }
}
