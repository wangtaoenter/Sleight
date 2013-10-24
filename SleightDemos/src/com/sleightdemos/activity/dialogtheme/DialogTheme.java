package com.sleightdemos.activity.dialogtheme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
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
