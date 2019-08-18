package com.psl.wifimonitor

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.net.wifi.WifiManager.SUPPLICANT_STATE_CHANGED_ACTION
import android.net.wifi.WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.wifi.WifiManager
import android.net.wifi.WifiManager.WIFI_STATE_CHANGED_ACTION
import android.net.wifi.WifiManager.NETWORK_STATE_CHANGED_ACTION
import android.os.Build
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        // Android 8.0使用startForegroundService在前台启动新服务
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, NetworkStateService::class.java))
        } else{
            startService(Intent(this, NetworkStateService::class.java))
        }

        tv_net_info.text = NetWorkStateReceiver.sWifiRecord
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEventBus(msg: String) {
        tv_net_info.text = NetWorkStateReceiver.sWifiRecord
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
