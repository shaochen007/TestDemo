package com.chenyong.testdemo.client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.chenyong.testdemo.R;
import com.example.chenyong.testdemo.databinding.ActivityTcpclientBinding;
import com.chenyong.testdemo.service.TCPServerService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClientActivity extends AppCompatActivity implements View.OnTouchListener {
    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private ActivityTcpclientBinding binding;
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    private ConstraintLayout.LayoutParams layoutParams;
    private WindowManager windowManager;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    binding.tvContent.setText(binding.tvContent.getText() + (String) msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    binding.button.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tcpclient);
        windowManager = this.getWindowManager();
        layoutParams = (ConstraintLayout.LayoutParams) binding.button.getLayoutParams();
                //        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSPARENT);
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
        binding.button.setOnTouchListener(this);
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String msg = binding.etContent.getText().toString();
//                if (!msg.isEmpty() && mPrintWriter != null) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mPrintWriter.println(msg);
//                        }
//                    }).start();
//                    binding.etContent.setText("");
//                    String time = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
//                    final String showedMsg = "self" + time + ":" + msg + "\n";
//                    binding.tvContent.setText(binding.tvContent.getText() + showedMsg);
//                }
//            }
//        });
    }



    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }


    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
//                socket = new Socket("10.0.2.2",41818);
//                socket = new Socket("localhost",8688);
//                mClientSocket = socket;
//                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
//                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                socket = new Socket("localhost", 8075);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
            } catch (IOException e) {
                SystemClock.sleep(1000);
                e.printStackTrace();
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                if (msg != null) {
                    String time = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
                    final String showedMsg = "server" + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                }
            }
            if (mPrintWriter != null) {
                mPrintWriter.close();
            }
            if (br != null) {
                br.close();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int x,y;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = rawX - layoutParams.leftMargin;
                y = rawY - layoutParams.topMargin;

                break;
            case MotionEvent.ACTION_MOVE:
                int iX = rawX - x;
                int iY = rawY -y;
                layoutParams.leftMargin = -iX;
                layoutParams.topMargin = -iY;
                binding.button.setLayoutParams(layoutParams);
//                windowManager.updateViewLayout(binding.button.getRootView(),layoutParams);
                break;
                default:
                    break;
        }
        binding.getRoot().invalidate();
        return false;
    }
}