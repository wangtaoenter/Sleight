package com.sleightdemos.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [Examlevel2, 2011-8-30]
 */
public class RingerModeChangedActivity extends Activity
{
    private static final String TAG = "RingerModeChangedActivity";

    private Handler handler = new Handler()
    {

    };

    private BroadcastReceiver RTReceiver = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction().equals(RingerModeChangedReceiver.RING_MODE_CHANGED))
            {
                Log.e(TAG,
                    "RTReceiver,RING_MODE_CHANGED:" + intent.getExtras().getInt(RingerModeChangedReceiver.RING_MODE));
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        IntentFilter mFfilter = new IntentFilter();
        mFfilter.addAction(RingerModeChangedReceiver.RING_MODE_CHANGED);
        registerReceiver(RTReceiver, mFfilter);

        Intent intent = getIntent();
        int ringMode = -1;
        if (null != intent)
        {
            Bundle bundle = intent.getExtras();
            if (null != bundle)
            {
                ringMode = bundle.getInt(RingerModeChangedReceiver.RING_MODE);
                Log.d(TAG, "onCreate,ringMode:" + ringMode);
            }
        }
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart()
    {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, "onDestroy");
        unregisterReceiver(RTReceiver);
        super.onDestroy();
    }
}
