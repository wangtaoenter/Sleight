package com.sleightdemos.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sleightdemos.R;
import com.sleightdemos.widget.MyTabActivity;
import com.sleightdemos.widget.MyTabHost;
import com.sleightdemos.widget.MyTabHost.TabSpec;

public class MyTabTest extends MyTabActivity {
	
	/**
     * tab标签
     */
    private MyTabHost tabHost;
    
    /**
     * tab对应的
     */
    private TabSpec tabSpec1;

    /**
     * tab对应的
     */
    private TabSpec tabSpec2;

    /**
     * tab对应的
     */
    private TabSpec tabSpec3;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTab();
	}
	
	 /**
     * 初始化
     */
    private void initTab()
    {
        tabHost = getTabHost();
        tabHost.setBackgroundResource(R.drawable.tab_back);
        tabSpec1 = tabHost.newTabSpecT("tabSpec1");
        tabSpec2 = tabHost.newTabSpecT("tabSpec2");
        tabSpec3 = tabHost.newTabSpecT("tabSpec3");

        tabSpec1.setIndicator("tabSpec1");

        tabSpec2.setIndicator("tabSpec2");

        tabSpec3.setIndicator("tabSpec3");

        tabSpec1.setContent(new Intent(this,
            BuildInfo.class));
        tabSpec2.setContent(new Intent(this,
            CircleTextViewActivity.class));
        tabSpec3.setContent(new Intent(this,
            GifActivity.class));

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
    }
}
