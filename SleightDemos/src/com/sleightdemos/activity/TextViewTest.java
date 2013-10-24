package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sleightdemos.R;

public class TextViewTest extends Activity
{
    TextView textView;
    EditText editText;
    Button setBtn, cleanBtn;
    String a = "正在播放:";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvt);
        textView = (TextView) findViewById(R.id.text);

        a = a
            + ":@@##%^%&&*(*%$###%&*(*(^%!@##%^&@$$$%)*^%$$$(_+(&()__+!@#$%^&*()><?:";
        textView.setText(a);

        editText = (EditText) findViewById(R.id.input);
        setBtn = (Button) findViewById(R.id.setBtn);
        setBtn.setOnClickListener(onClickListener);
        cleanBtn = (Button) findViewById(R.id.cleanBtn);
        cleanBtn.setOnClickListener(onClickListener);

    }

    OnClickListener onClickListener = new OnClickListener()
    {

        public void onClick(View v)
        {
            if (v.equals(setBtn))
            {
                a = a + editText.getText().toString();
                textView.setText(a);
            }
            else
            {
                a = "正在播放:";
                textView.setText("");
            }
        }

    };
}
