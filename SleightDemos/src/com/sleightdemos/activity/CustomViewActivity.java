package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.view.CustomView;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [2010-4-1]
 */
public class CustomViewActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
    }

}
