package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [2010-4-13]
 */
public class TopRightCornerImgActivity extends Activity
{
    private TextView textView;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_right_corner);
        textView = (TextView) findViewById(R.id.topRight_textView);
        textView.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                counter = Integer.parseInt(textView.getText().toString()) + 1;
                counter = (counter > 9) ? 0 : counter;
                textView.setText(Integer.toString(counter));
            }
        });

    }
}
