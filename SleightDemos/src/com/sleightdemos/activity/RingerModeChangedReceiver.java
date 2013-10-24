package com.sleightdemos.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [Examlevel2, 2011-8-30]
 */
public class RingerModeChangedReceiver extends BroadcastReceiver
{
    private static final String TAG = "RingerModeChangedReceiver";
    public static final String RING_MODE = "ringMode";
    public static final String RING_MODE_CHANGED = "com.demo.RingerModeChangedReceiver.ringModeChanged";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.media.RINGER_MODE_CHANGED"))
        {
            Log.e(TAG, "android.media.RINGER_MODE_CHANGED");

            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int ringMode = audioManager.getRingerMode();

            Bundle b = new Bundle();
            b.putInt(RING_MODE, ringMode);

            Intent intent2 = new Intent();
            intent2.putExtras(b);
            intent2.setClass(context, RingerModeChangedActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            context.startActivity(intent2);

            Intent i = new Intent(RING_MODE_CHANGED);
            i.putExtras(b);
            context.sendBroadcast(i);
            Log.d(TAG, "sendBroadcast");
        }
    }
}
