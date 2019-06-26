package com.example.socketanother;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket 发送指令工具类
 *
 * @author psl
 * @version 1.0
 */
public class SocketCommandUtils {

    public static void send(final String host, final int port, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                PrintWriter out = null;
                try {
                    socket = new Socket(host, port);
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    if (socket.isConnected() && !socket.isOutputShutdown()) {
                            out.write(msg);
                            out.flush();
                            out.close();
                            socket.close();
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
    }
}
