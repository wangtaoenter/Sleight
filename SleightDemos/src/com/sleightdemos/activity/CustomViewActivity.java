/*
 * 文件名: CustomViewActivity.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2010-4-1
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos.activity;

import com.sleightdemos.view.CustomView;

import android.app.Activity;
import android.os.Bundle;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
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
