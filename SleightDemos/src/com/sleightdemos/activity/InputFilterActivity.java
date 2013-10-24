package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import com.sleightdemos.R;
import com.sleightdemos.util.OwnInputFilter;

public class InputFilterActivity extends Activity
{
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.input_filter);
        
        editText = (EditText) findViewById(R.id.editText);
        InputFilter[] inputFilters = {new OwnInputFilter.EnterFilter() };
        editText.setFilters(inputFilters);
    }
}
