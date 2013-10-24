package com.sleightdemos.activity;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.sleightdemos.util.Security;

public class SecurityTest extends Activity
{
    private final static String TAG = "SecurityTest";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "" + Security.md5("123"));
        Log.d(TAG, "" + Security.getSha256("123"));
        try
        {
            Log.d(TAG, "" + new String(Security.hashMethod("123".getBytes())));
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d(TAG, ""
            + Security.symmetricDecryptoAES("3I2CpC0IJo+q1lO1n4rnoA=="));
        Log.d(TAG, "" + Security.symmetricDecryptoDES("P3CZQELo5sQ="));
        Log.d(TAG, "" + Security.symmetricEncryptoAES("123"));
        Log.d(TAG, "" + Security.symmetricEncryptoDES("123"));
    }
}
