/*
 * 文件名: SleightDemosApplication.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2010-3-31
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos;

import android.app.Application;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
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
