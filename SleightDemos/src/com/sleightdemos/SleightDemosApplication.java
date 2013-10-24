package com.sleightdemos;

import android.app.Application;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [2010-3-31]
 */
public class SleightDemosApplication extends Application
{
    public final static String SAMPLE_CODE = "com.sleightdemos.SAMPLE_CODE";

    private SleightDemosApplication instance;

    public void onCreate()
    {
        instance = this;
    }

}
