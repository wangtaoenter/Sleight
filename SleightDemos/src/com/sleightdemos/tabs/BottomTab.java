package com.sleightdemos.tabs;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.sleightdemos.R;
import com.sleightdemos.activity.CustomViewActivity;

/**
 * A custom Tab Demo
 * 
 * @author wangtaoenter
 * @version [2010-4-24]
 */
public class BottomTab extends TabActivity
{

    /** Called when the activity is first created. */
    private TabHost mTabHost;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab);

        mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Tab 1").setContent(R.id.textview1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Tab 2").setContent(R.id.textview2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Tab 3").setContent(R.id.textview3));
        mTabHost.setCurrentTab(0);

        mTabHost.setOnTabChangedListener(onTabChangeListener);
        mTabHost.setOnClickListener(onClickListener);

    }

    OnClickListener onClickListener = new OnClickListener()
    {

        public void onClick(View v)
        {
            Log.d("HelT", "click");
        }
    };

    OnTabChangeListener onTabChangeListener = new TabHost.OnTabChangeListener()
    {

        public void onTabChanged(String tabId)
        {
            Log.d("HelT", tabId);
            if (tabId.equals("tab_test3"))
            {
                Intent intent = new Intent();
                intent.setClass(BottomTab.this, CustomViewActivity.class);
                startActivity(intent);
            }
        }
    };

}
