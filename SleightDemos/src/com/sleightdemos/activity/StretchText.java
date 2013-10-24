package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

import com.sleightdemos.R;

public class StretchText extends Activity implements OnTouchListener
{
    private static final String TAG = "StretchText";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretch_text);
        editText = (EditText) findViewById(R.id.editText);
        editText.setOnTouchListener(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        Log.d(TAG, "MotionEvent, RawX:" + event.getRawX() + ",RawY:"
            + event.getRawY() + ",X:" + event.getX() + ",Y:" + event.getY());
        Log.d(TAG, "" + editText.getCompoundPaddingLeft());
        Log.d(TAG, "" + editText.getCompoundPaddingBottom());
        Log.d(TAG, "" + editText.getPaddingLeft());
        Log.d(TAG, "" + editText.getPaddingBottom());
        Log.d(TAG, "" + editText.getScrollX());
        Log.d(TAG, "" + editText.getScrollY());
        Log.d(TAG, "" + editText.getTotalPaddingLeft());
        Log.d(TAG, "" + editText.getTotalPaddingBottom());
        int w = editText.getWidth();
        int h = editText.getHeight();
        Log.d(TAG, "w:" + w + ",h:" + h);
        editText.setWidth((int) event.getX());
        editText.setHeight((int) event.getY());
        return true;
    }
}
