package com.example.socketanother;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class SocketServerService extends Service {
    private boolean mIsServiceDestoryed = false;
    private String[] mDefinedMessages = new String[]{"你好啊，哈哈", "请问你叫什么名字呀？", "今天北京天气不错啊"};

    @Override
    public void onCreate() {
        Log.d("", "开启服务1");
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8076);

                Log.d("", "开启服务2");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
                    logClient(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void logClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        while (!mIsServiceDestoryed) {
            String str = in.readLine();
            if (str == null) {
                break;
            }
            System.out.println(str);
        }

        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        client.close();
    }
}
