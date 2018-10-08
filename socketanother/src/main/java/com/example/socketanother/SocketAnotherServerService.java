package com.example.socketanother;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.math.MathUtils;
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

public class SocketAnotherServerService extends Service {
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
                serverSocket = new ServerSocket(8075);

                Log.d("", "开启服务2");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestoryed) {
                try {
                    final Socket client = serverSocket.accept();
                    logClient(client);
//                    Log.d("another", "run: recivedmsg");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Socket socket = null;
                            PrintWriter out = null;
                            try {
                                socket = new Socket("localhost", 8076);
                                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                                if (socket.isConnected()) {
                                    if (!socket.isOutputShutdown()) {
                                        out.write("回复指令：" + Math.random());
                                        out.flush();
                                        out.close();
                                        socket.close();
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (socket != null) {
                                    try {
                                        socket.close();
                                        assert out != null;
                                        out.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void logClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
//        out.println("欢迎来到聊天室");
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
