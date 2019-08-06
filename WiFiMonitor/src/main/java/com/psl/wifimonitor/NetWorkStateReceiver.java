package com.psl.wifimonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NetWorkStateReceiver extends BroadcastReceiver {
    // 静态变量记录当前连接的WiFi
    static String sCurWiFi;
    // 静态变量记录WiFi连接、断开
    static String sWifiRecord = "";
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("网络状态发生变化" + intent.getAction());
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        //获得ConnectivityManager对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        System.out.println(connectivityManager);
        if (connectivityManager == null) {
            return;
        }

//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        System.out.println(activeNetworkInfo);
//        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
//            return;
//        }


        String wifiDesc = null, dataNetDesc = null, netDesc = null;
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        System.out.println(wifiNetworkInfo);
        if (wifiNetworkInfo == null) {
            wifiDesc = "\n断开WiFi：" + sCurWiFi + ", 时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            sWifiRecord += wifiDesc;
            EventBus.getDefault().post("");
            Toast.makeText(context, wifiDesc, Toast.LENGTH_SHORT).show();
            return;
        }
        NetworkInfo dataNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiNetworkInfo.isConnected()) {
            // 获取WiFi名称
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            wifiDesc = "\n连接WiFi：" + (sCurWiFi = wifiInfo.getSSID()) + ", 时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        } else {
            wifiDesc = "\n断开WiFi：" + sCurWiFi + ", 时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

        sWifiRecord += wifiDesc;
        EventBus.getDefault().post("");
        Toast.makeText(context, wifiDesc, Toast.LENGTH_SHORT).show();

//        if (dataNetworkInfo != null && dataNetworkInfo.isConnected()) {
//            dataNetDesc = "已连接数据网络";
//            netDesc += dataNetDesc;
//        }
//
//        EventBus.getDefault().post(wifiDesc + "," + dataNetDesc);
//        if (netDesc != null) {
//            Toast.makeText(context, netDesc, Toast.LENGTH_SHORT).show();
//        }
    }
//    }
}
