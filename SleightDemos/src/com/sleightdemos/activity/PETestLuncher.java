package com.sleightdemos.activity;

import android.app.Activity;
import android.content.Intent;
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
 * @version [2010-4-29]
 */
public class PETestLuncher extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_other_activity);
        Button button = (Button) findViewById(R.id.startActivity);
        button.setOnClickListener(ol);
    }

    OnClickListener ol = new OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            Bundle bundle = new Bundle();
            bundle.putString("mediaPath", "file:///sdcard/1.3gp");
            intent.putExtras(bundle);
            intent.setClassName("com.", "com");
            startActivity(intent);
        }
    };

}
