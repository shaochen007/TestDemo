package com.kungeek.phoneandsms

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_phone)
        findViewById<Button>(R.id.btn_phone).setOnClickListener {
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val phone = telephonyManager.line1Number
            Log.d("phoneNumber", phone)
            textView.text = phone
        }
    }
}
