package com.sleightdemos.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [2010-4-26]
 */
public class PhoneStateActivity extends Activity
{
    PhoneStateListener psl = new PhoneStateListener()
    {
        @Override
        public void onSignalStrengthChanged(int asu)
        {
            super.onSignalStrengthChanged(asu);
            Log.d("onSignalStrengthChanged", asu + "");
        }

        @Override
        public void onDataConnectionStateChanged(int state)
        {
            super.onDataConnectionStateChanged(state);
            Log.d("onDataConnectionStateChanged", state + "");
        }

        @Override
        public void onServiceStateChanged(ServiceState serviceState)
        {
            super.onServiceStateChanged(serviceState);
            Log.d("onServiceStateChanged", serviceState.getState() + "");
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(psl, PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
        tm.listen(psl, PhoneStateListener.LISTEN_CALL_STATE);
        tm.listen(psl, PhoneStateListener.LISTEN_SERVICE_STATE);
        tm.listen(psl, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);

    }
}
