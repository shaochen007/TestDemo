package com.chenyong.testdemo

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.chenyong.testdemo.util.EncryptUtils
import com.example.chenyong.testdemo.R
import com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth
import com.tencent.mm.opensdk.diffdev.OAuthErrCode
import com.tencent.mm.opensdk.diffdev.OAuthListener
import com.tencent.mm.opensdk.utils.Log
import java.util.*

class WxScanLoginActivity : AppCompatActivity() {

    val TAG = "WxScanLoginActivity"

    lateinit var oauth: IDiffDevOAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wx_scan_login)
        //初始化oauth
        oauth = DiffDevOAuthFactory.getDiffDevOAuth()

        auth("AApM1OofrObHyTiLTt2NVQ2UpeYRziWHsCS82NbnpWK8r_HSn2CI5gXBgZ0TtSaFXcopsyHUXL9uuxtWbIsMkA")
    }

    override fun onDestroy() {
        super.onDestroy()
        oauth.removeAllListeners()
        oauth.detach()
    }

    fun auth(ticket: String) {
        val noncestr = getNonceStr()
        val timestamp = getTimestamp()
        val signature = getSignature(noncestr, timestamp, ticket)

        val authRet = oauth.auth(WXAPPID, "snsapi_userinfo", noncestr,
                timestamp, signature, object : OAuthListener {
            /**
             * 用户点击授权后，回调改接口
             */
            override fun onAuthFinish(errCode: OAuthErrCode?, authCode: String?) {
                Log.i(TAG, "onAuthFinish,OAuthErrCode->$errCode ,authCode->$authCode")

                val tips: String = when (errCode) {
                    OAuthErrCode.WechatAuth_Err_OK -> "登录成功,code=$authCode"
                    OAuthErrCode.WechatAuth_Err_NormalErr -> "登录失败,一般错误"
                    OAuthErrCode.WechatAuth_Err_NetworkErr -> "登录失败,网络错误"
                    OAuthErrCode.WechatAuth_Err_JsonDecodeErr -> "json解码失败"
                    OAuthErrCode.WechatAuth_Err_Cancel -> "用户取消"
                    OAuthErrCode.WechatAuth_Err_Timeout -> "登录失败，超时错误"
                    else -> ""
                }
                Log.i(TAG, tips)
            }

            /**
             * auth之后返回的二维码接口
             *
             * @param qrcodeImgPath 废弃
             * @param imgBuf 二维码图片数据
             */
            override fun onAuthGotQrcode(qrcodeImgPath: String?, imgBuf: ByteArray?) {
                Log.i(TAG, "imgBuf->$imgBuf")
                if (imgBuf != null) {
                    val bitmap = BitmapFactory.decodeByteArray(imgBuf, 0, imgBuf.size)
                    runOnUiThread {
                        findViewById<ImageView>(R.id.img_wx).setImageBitmap(bitmap)
                    }
                }
            }

            /**
             * 用户扫描二维码之后，回调改接口
             */
            override fun onQrcodeScanned() {
                Log.i(TAG, "onQrcodeScanned")
            }

        })
        Log.i(TAG, "authRet->$authRet")
    }

    private val WXAPPID = "wxff5940861041ce2d"
//    private val WXAPPID = "f261186a38e1273d48a1aef433b72da6"

    private fun getNonceStr(): String {
        val r = Random(System.currentTimeMillis())
        return EncryptUtils.encryptMD5ToString((WXAPPID +
                r.nextInt(10000) + System.currentTimeMillis()).toByteArray())
    }

    private fun getTimestamp(): String {
        return System.currentTimeMillis().toString()
    }

    private fun getSignature(noncestr: String, timestamp: String, sdk_ticket: String): String {
        val str = "appid=${WXAPPID}&noncestr=$noncestr&sdk_ticket=$sdk_ticket&timestamp=$timestamp"
        return EncryptUtils.encryptSHA1ToString(str)
    }
}
