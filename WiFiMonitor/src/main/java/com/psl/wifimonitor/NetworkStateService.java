package com.psl.wifimonitor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class NetworkStateService extends Service {
    private static final String CHANNEL_ID = "com.kungeek.smscaptcha.SmsService";
    private static final String CHANNEL_NAME = "SmsService";

    private BroadcastReceiver networkConnectChangedReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID);
            // 向系统注册通知渠道，注册后不能改变重要性以及其它通知行为
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        } else {
            builder = new Notification.Builder(this);
        }

        builder.setOngoing(true);

        startForeground(1001, builder.build());
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerNetworkConnectChangeReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkConnectChangedReceiver);
        stopForeground(true);
    }


    private void registerNetworkConnectChangeReceiver() {
        IntentFilter filter = new IntentFilter();
//        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
//        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        filter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
//        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        networkConnectChangedReceiver = new NetWorkStateReceiver();
        registerReceiver(networkConnectChangedReceiver, filter);
    }
}
