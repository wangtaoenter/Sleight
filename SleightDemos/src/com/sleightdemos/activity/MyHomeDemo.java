package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [, 2010-8-2]
 */
public class MyHomeDemo extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        return super.dispatchKeyEvent(event);
    }

}
