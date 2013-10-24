package com.sleightdemos.widget;

import android.app.Activity;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.sleightdemos.R;

public class MyTabActivity extends ActivityGroup
{

    private MyTabHost mTabHost;
    private String mDefaultTab = null;
    private int mDefaultTabIndex = -1;

    /**
     * Sets the default tab that is the first tab highlighted.
     * 
     * @param tag the name of the default tab
     */
    public void setDefaultTab(String tag)
    {
        mDefaultTab = tag;
        mDefaultTabIndex = -1;
    }

    /**
     * Sets the default tab that is the first tab highlighted.
     * 
     * @param index the index of the default tab
     */
    public void setDefaultTab(int index)
    {
        mDefaultTab = null;
        mDefaultTabIndex = index;
    }

    /**
     * onRestoreInstanceState
     * 
     * @param state Bundle
     * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
     */
    @Override
    protected void onRestoreInstanceState(Bundle state)
    {
        super.onRestoreInstanceState(state);
        ensureTabHost();
        String cur = state.getString("currentTab");
        if (cur != null)
        {
            mTabHost.setCurrentTabByTag(cur);
        }
        if (mTabHost.getCurrentTab() < 0)
        {
            if (mDefaultTab != null)
            {
                mTabHost.setCurrentTabByTag(mDefaultTab);
            }
            else if (mDefaultTabIndex >= 0)
            {
                mTabHost.setCurrentTab(mDefaultTabIndex);
            }
        }
    }

    /**
     * 
     * onPostCreate
     * 
     * @param icicle Bundle
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    @Override
    protected void onPostCreate(Bundle icicle)
    {
        super.onPostCreate(icicle);

        ensureTabHost();

        if (mTabHost.getCurrentTab() == -1)
        {
            mTabHost.setCurrentTab(0);
        }
    }

    /**
     * 
     * onSaveInstanceState
     * 
     * @param outState Bundle
     * @see android.app.ActivityGroup#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String currentTabTag = mTabHost.getCurrentTabTag();
        if (currentTabTag != null)
        {
            outState.putString("currentTab", currentTabTag);
        }
    }

    /**
     * Updates the screen state (current list and other views) when the content
     * changes.
     * 
     *@see Activity#onContentChanged()
     */
    @Override
    public void onContentChanged()
    {
        super.onContentChanged();
        mTabHost = (MyTabHost) findViewById(R.id.tabhost);

        if (mTabHost == null)
        {
            throw new RuntimeException(
                "Your content must have a TabHost whose id attribute is "
                    + "'R.id.tabhost'");
        }
        mTabHost.setup(getLocalActivityManager());
    }

    private void ensureTabHost()
    {
        if (mTabHost == null)
        {
            this.setContentView(R.layout.tab_content);
        }
    }

    /**
     * 
     * onChildTitleChanged
     * 
     * @param childActivity Activity
     * @param title CharSequence
     * @see android.app.Activity#onChildTitleChanged(android.app.Activity,
     *      java.lang.CharSequence)
     */
    @Override
    protected void onChildTitleChanged(Activity childActivity,
        CharSequence title)
    {
        // Dorky implementation until we can have multiple activities running.
        if (getLocalActivityManager().getCurrentActivity() == childActivity)
        {
            View tabView = mTabHost.getCurrentTabView();
            if (tabView != null && tabView instanceof TextView)
            {
                ((TextView) tabView).setText(title);
            }
        }
    }

    /**
     * Returns the {@link TabHost} the activity is using to host its tabs.
     * 
     * @return the {@link TabHost} the activity is using to host its tabs.
     */
    public MyTabHost getTabHost()
    {
        ensureTabHost();
        return mTabHost;
    }

    /**
     * Returns the {@link TabWidget} the activity is using to draw the actual
     * tabs.
     * 
     * @return the {@link TabWidget} the activity is using to draw the actual
     *         tabs.
     */
    public MyTabWidget getTabWidget()
    {
        return mTabHost.getTabWidgetT();
    }

}
