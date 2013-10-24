/*
 * 文件名: MyHomeDemo.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2010-8-2
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * @author w00138133
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
