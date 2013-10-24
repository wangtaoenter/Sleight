package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.view.CircleTextView;

public class CircleTextViewActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new CircleTextView(this));
    }

}
