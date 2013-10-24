package com.sleightdemos.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [2010-4-7]
 */
public class StartOtherActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_other_activity);
        Button button = (Button) findViewById(R.id.startActivity);
        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                start();
            }
        });
    }

    private void start()
    {
        Intent intent = new Intent();
        try
        {
            intent.setClassName(
                getApplicationContext().createPackageContext("com.", android.content.Context.CONTEXT_IGNORE_SECURITY),
                "com.");
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
