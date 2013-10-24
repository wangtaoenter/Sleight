package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.view.RoundSpinView;

public class CircleMenuActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new RoundSpinView(getApplicationContext(), 240, 400, 150));
    }
}
