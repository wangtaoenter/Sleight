package com.sleightdemos.activity;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.R;

public class ReadConfigAcitivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_test);
        XmlPullParser xmlParser = getResources().getXml(R.xml.config);
    }

}
