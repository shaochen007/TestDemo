package com.chenyong.testdemo;

import com.chenyong.testdemo.util.EncryptUtils;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        assertEquals("429eaaa13fd71efbc3fd344d0a9a9126835e7303", sha1());
    }

    private String sha1() {
        String data = "appid=appid&noncestr=noncestr&sdk_ticket=-p3A5zVP95IuafPhzA6lRR95_F9nZEBfJ_n4E9t8ZFWKJTDPOwccVQhHCwDBmvLkayF_jh-m9HOExhumOziDWA&timestamp=1417508194";
        //            String result = new String(MessageDigest.getInstance("SHA-1").digest(data.getBytes()));
        String result = EncryptUtils.encryptSHA1ToString(data);
        return result;
    }
}