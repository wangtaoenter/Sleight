/*
 * 文件名: SimpleActivity.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2010-4-15
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos.activity.dialogtheme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [2010-4-15]
 */
public class DialogTheme extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Bundle bunde = this.getIntent().getExtras();
        if (null != bunde && bunde.getBoolean("normalStyle"))
        {
            setTheme(android.R.style.Theme);
        }
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("Hello.同一个Activity");
        setContentView(textView);
    }

}
