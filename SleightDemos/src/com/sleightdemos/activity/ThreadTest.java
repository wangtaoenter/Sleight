package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.sleightdemos.R;

public class ThreadTest extends Activity
{
    private static final String TAG = "ThreadTest";

    Button startBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_test);

    }
}
