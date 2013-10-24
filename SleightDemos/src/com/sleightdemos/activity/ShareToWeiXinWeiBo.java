package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleightdemos.R;

public class ShareToWeiXinWeiBo extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.share_to);

        Button shareToWeiXinBtn = (Button) findViewById(R.id.share_to_weixin);
        shareToWeiXinBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}
