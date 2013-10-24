package com.sleightdemos.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [Examlevel2, 2011-6-16]
 */
public class SmsSenderActivity extends Activity
{
    private static final String TAG = "SmsSenderActivity";
    EditText textView, numText;
    Button button;

    private BroadcastReceiver smsSendReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            Log.i(TAG, "[onReceive]");
            Log.i(TAG, "[onReceive]" + intent.getAction());
            Toast.makeText(SmsSenderActivity.this, "is:" + intent.getAction(), Toast.LENGTH_LONG);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_sender);
        numText = (EditText) findViewById(R.id.toNumber);
        textView = (EditText) findViewById(R.id.sendingText);
        button = (Button) findViewById(R.id.SendBtn);

        IntentFilter mFfilter = new IntentFilter();
        mFfilter.addAction("com.sleightdemos.action.smssend");
        registerReceiver(smsSendReceiver, mFfilter);

        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                sendSMS(textView.getText().toString());
            }
        });
        numText.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {
                numText.setText("");
            }
        });
    }

    private void sendSMS(String textMsg)
    {
        SmsManager sm = SmsManager.getDefault();
        Intent i = new Intent("com.sleightdemos.action.smssend");
        PendingIntent dummyEvent = PendingIntent.getBroadcast(SmsSenderActivity.this, 0, i, 0);
        sm.sendTextMessage(numText.getText().toString(), null, textMsg, dummyEvent, dummyEvent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(smsSendReceiver);
    }

}
