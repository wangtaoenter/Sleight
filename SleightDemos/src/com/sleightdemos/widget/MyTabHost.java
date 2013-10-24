package com.sleightdemos.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleightdemos.R;

public class MyTabHost extends FrameLayout implements OnTouchModeChangeListener
{

    private MyTabWidget mTabWidget;
    private FrameLayout mTabContent;
    private List<TabSpec> mTabSpecs = new ArrayList<TabSpec>(2);

    /**
     * 子TAB标志
     */
    private boolean isParent = true;

    /**
     * This field should be made private, so it is hidden from the SDK.
     */
    private View mCurrentView = null;

    /**
     * This field should be made private, so it is hidden from the SDK.
     */
    private OnTabChangeListener mOnTabChangeListener;
    private OnKeyListener mTabKeyListener;
    private int mCurrentTab = -1;
    private LocalActivityManager mLocalActivityManager = null;

    /**
     * @param context Context
     */
    public MyTabHost(Context context)
    {
        super(context);
        initTabHost();
    }

    /**
     * @param context Context
     * @param attrs AttributeSet
     */
    public MyTabHost(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initTabHost();
    }

    private final void initTabHost()
    {
        setFocusableInTouchMode(true);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);

        mCurrentTab = -1;
        mCurrentView = null;
    }

    /**
     * 
     * newTabSpecT
     * 
     * @param tag String
     * @return TabSpec
     */
    public TabSpec newTabSpecT(String tag)
    {
        return new TabSpec(tag);
    }

    /**
     * <p>
     * Call setup() before adding tabs if loading TabHost using findViewById().
     * <i><b>However</i></b>: You do not need to call setup() after getTabHost()
     * in {@link android.app.TabActivity TabActivity}. Example:
     * </p>
     * 
     * <pre>
     * mTabHost = (TabHost) findViewById(R.id.tabhost);
     * mTabHost.setup();
     * mTabHost.addTab(TAB_TAG_1, "Hello, world!", "Tab 1");
     */
    public void setup()
    {
        mTabWidget = (MyTabWidget) findViewById(R.id.tabs);
        if (mTabWidget == null)
        {
            throw new RuntimeException(
                "Your TabHost must have a TabWidget whose id attribute is 'R.id.tabs'");
        }

        // KeyListener to attach to all tabs. Detects non-navigation keys
        // and relays them to the tab content.
        mTabKeyListener = new OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                switch (keyCode)
                {
                    case KeyEvent.KEYCODE_DPAD_CENTER:
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                    case KeyEvent.KEYCODE_DPAD_UP:
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                    case KeyEvent.KEYCODE_ENTER:
                        return false;

                }
                mTabContent.requestFocus(View.FOCUS_FORWARD);
                return mTabContent.dispatchKeyEvent(event);
            }

        };

        mTabWidget
            .setTabSelectionListener(new MyTabWidget.OnTabSelectionChanged()
            {
                public void onTabSelectionChanged(int tabIndex, boolean clicked)
                {
                    setCurrentTab(tabIndex);
                    if (clicked)
                    {
                        mTabContent.requestFocus(View.FOCUS_FORWARD);
                    }
                }
            });

        mTabContent = (FrameLayout) findViewById(R.id.tabcontent);
        if (mTabContent == null)
        {
            throw new RuntimeException("Your TabHost must have a FrameLayout"
                + " whose id attribute is 'android.R.id.tabcontent'");
        }
    }

    /**
     * If you are using {@link TabSpec#setContent(android.content.Intent)}, this
     * must be called since the activityGroup is needed to launch the local
     * activity.
     * 
     * This is done for you if you extend {@link android.app.TabActivity}.
     * 
     * @param activityGroup Used to launch activities for tab content.
     */
    public void setup(LocalActivityManager activityGroup)
    {
        setup();
        mLocalActivityManager = activityGroup;
    }

    /**
     * 
     * onAttachedToWindow
     * 
     * @see android.widget.TabHost#onAttachedToWindow()
     */
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        final ViewTreeObserver treeObserver = getViewTreeObserver();
        if (treeObserver != null)
        {
            treeObserver.addOnTouchModeChangeListener(this);
        }
    }

    /**
     * 
     * onDetachedFromWindow
     * 
     * @see android.widget.TabHost#onDetachedFromWindow()
     */
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        final ViewTreeObserver treeObserver = getViewTreeObserver();
        if (treeObserver != null)
        {
            treeObserver.removeOnTouchModeChangeListener(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onTouchModeChanged(boolean isInTouchMode)
    {
        if (!isInTouchMode)
        {
            // leaving touch mode.. if nothing has focus, let's give it to
            // the indicator of the current tab
            if (!mCurrentView.hasFocus() || mCurrentView.isFocused())
            {
                mTabWidget.getChildAt(mCurrentTab).requestFocus();
            }
        }
    }

    /**
     * Add a tab.
     * 
     * @param tabSpec Specifies how to create the indicator and content.
     */
    public void addTab(TabSpec tabSpec)
    {

        if (tabSpec.mIndicatorStrategy == null)
        {
            throw new IllegalArgumentException(
                "you must specify a way to create the tab indicator.");
        }

        if (tabSpec.mContentStrategy == null)
        {
            throw new IllegalArgumentException(
                "you must specify a way to create the tab content");
        }
        View tabIndicator = tabSpec.mIndicatorStrategy.createIndicatorView();
        tabIndicator.setOnKeyListener(mTabKeyListener);
        mTabWidget.addView(tabIndicator);
        mTabSpecs.add(tabSpec);
        // 初始化时，设置默认选中tabIndex = 1;
        if (mCurrentTab == -1)
        {
            setCurrentTab(1);
        }
    }

    /**
     * Removes all tabs from the tab widget associated with this tab host.
     */
    public void clearAllTabs()
    {
        mTabWidget.removeAllViews();
        initTabHost();
        mTabContent.removeAllViews();
        mTabSpecs.clear();
        requestLayout();
        invalidate();
    }

    public MyTabWidget getTabWidgetT()
    {
        return mTabWidget;
    }

    public int getCurrentTab()
    {
        return mCurrentTab;
    }

    /**
     * 
     * getCurrentTabTag
     * 
     * @return String
     * @see android.widget.TabHost#getCurrentTabTag()
     */
    public String getCurrentTabTag()
    {
        if (mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size())
        {
            return mTabSpecs.get(mCurrentTab).getTag();
        }
        return null;
    }

    /**
     * 
     * getCurrentTabView
     * 
     * @return View
     * @see android.widget.TabHost#getCurrentTabView()
     */
    public View getCurrentTabView()
    {
        if (mCurrentTab >= 0 && mCurrentTab < mTabSpecs.size())
        {
            return mTabWidget.getChildAt(mCurrentTab);
        }
        return null;
    }

    public View getCurrentView()
    {
        return mCurrentView;
    }

    /**
     * 
     * setCurrentTabByTag
     * 
     * @param tag aa
     * @see android.widget.TabHost#setCurrentTabByTag(java.lang.String)
     */
    public void setCurrentTabByTag(String tag)
    {
        int i;
        for (i = 0; i < mTabSpecs.size(); i++)
        {
            if (mTabSpecs.get(i).getTag().equals(tag))
            {
                setCurrentTab(i);
                break;
            }
        }
    }

    /**
     * 
     * getTabContentView
     * 
     * @return FrameLayout
     * @see android.widget.TabHost#getTabContentView()
     */
    public FrameLayout getTabContentView()
    {
        return mTabContent;
    }

    /**
     * 
     * dispatchKeyEvent
     * 
     * @param event KeyEvent
     * @return boolean
     * @see android.widget.TabHost#dispatchKeyEvent(android.view.KeyEvent)
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        boolean handled = false;
        try
        {
            handled = super.dispatchKeyEvent(event);

            // unhandled key ups change focus to tab indicator for embedded
            // activities
            // when there is nothing that will take focus from default focus
            // searching
            if (!handled
                && (event.getAction() == KeyEvent.ACTION_DOWN)
                && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP)
                && (mCurrentView.hasFocus())
                && (mCurrentView.findFocus().focusSearch(View.FOCUS_UP) == null))
            {
                mTabWidget.getChildAt(mCurrentTab).requestFocus();
                playSoundEffect(SoundEffectConstants.NAVIGATION_UP);
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // Log.w("not focuse",
            // e.getMessage());
        }
        return handled;
    }

    /**
     * 
     * dispatchWindowFocusChanged
     * 
     * @param hasFocus boolean
     * @see android.widget.TabHost#dispatchWindowFocusChanged(boolean)
     */
    @Override
    public void dispatchWindowFocusChanged(boolean hasFocus)
    {
        mCurrentView.dispatchWindowFocusChanged(hasFocus);
    }

    /**
     * 
     * setCurrentTab
     * 
     * @param index int
     * @see android.widget.TabHost#setCurrentTab(int)
     */
    public void setCurrentTab(int index)
    {
        if (index < 0 || index >= mTabSpecs.size())
        {
            return;
        }

        if (index == mCurrentTab)
        {
            return;
        }

        // notify old tab content
        if (mCurrentTab != -1)
        {
            mTabSpecs.get(mCurrentTab).mContentStrategy.tabClosed();
        }

        mCurrentTab = index;
        final MyTabHost.TabSpec spec = mTabSpecs.get(index);

        // Call the tab widget's focusCurrentTab(), instead of just
        // selecting the tab.
        mTabWidget.focusCurrentTab(mCurrentTab);

        // tab content
        mCurrentView = spec.mContentStrategy.getContentView();

        if (mCurrentView.getParent() == null)
        {
            mTabContent.addView(mCurrentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
        }

        if (!mTabWidget.hasFocus())
        {
            // if the tab widget didn't take focus (likely because we're in
            // touch mode)
            // give the current tab content view a shot
            mCurrentView.requestFocus();
        }

        // mTabContent.requestFocus(View.FOCUS_FORWARD);
        invokeOnTabChangeListener();
    }

    /**
     * Register a callback to be invoked when the selected state of any of the
     * items in this list changes
     * 
     * @param l The callback that will run
     */
    public void setOnTabChangedListener(OnTabChangeListener l)
    {
        mOnTabChangeListener = l;
    }

    private void invokeOnTabChangeListener()
    {
        if (mOnTabChangeListener != null)
        {
            mOnTabChangeListener.onTabChanged(getCurrentTabTag());
        }
    }

    /**
     * Interface definition for a callback to be invoked when tab changed
     */
    public interface OnTabChangeListener
    {
        /**
         * onTabChanged
         * 
         * @param tabId String
         */
        void onTabChanged(String tabId);
    }

    /**
     * Makes the content of a tab when it is selected. Use this if your tab
     * content needs to be created on demand, i.e. you are not showing an
     * existing view or starting an activity.
     */
    public interface TabContentFactory
    {
        /**
         * Callback to make the tab contents
         * 
         * @param tag Which tab was selected.
         * @return The view to distplay the contents of the selected tab.
         */
        View createTabContent(String tag);
    }

    /**
     * A tab has a tab indictor, content, and a tag that is used to keep track
     * of it. This builder helps choose among these options.
     * 
     * For the tab indicator, your choices are: 1) set a label 2) set a label
     * and an icon
     * 
     * For the tab content, your choices are: 1) the id of a {@link View} 2) a
     * {@link TabContentFactory} that creates the {@link View} content. 3) an
     * {@link Intent} that launches an {@link android.app.Activity}.
     */
    public class TabSpec
    {

        private String mTag;

        private IndicatorStrategy mIndicatorStrategy;
        private ContentStrategy mContentStrategy;

        private TabSpec(String tag)
        {
            mTag = tag;
        }

        /**
         * 
         * setIndicator
         * 
         * @param label CharSequence
         * @return TabSpec
         */
        public TabSpec setIndicator(CharSequence label)
        {
            mIndicatorStrategy = new LabelIndicatorStrategy(label);
            return this;
        }

        /**
         * 
         * setIndicator
         * 
         * @param label CharSequence
         * @param icon Drawable
         * @return TabSpec
         */
        public TabSpec setIndicator(CharSequence label, Drawable icon)
        {
            mIndicatorStrategy = new LabelAndIconIndicatorStrategy(label, icon);
            return this;
        }

        /**
         * setContent
         * 
         * @param viewId int
         * @return TabSpec
         */
        public TabSpec setContent(int viewId)
        {
            mContentStrategy = new ViewIdContentStrategy(viewId);
            return this;
        }

        /**
         * setContent
         * 
         * @param contentFactory TabContentFactory
         * @return TabSpec
         */
        public TabSpec setContent(TabContentFactory contentFactory)
        {
            mContentStrategy = new FactoryContentStrategy(mTag, contentFactory);
            return this;
        }

        /**
         * setContent
         * 
         * @param intent Intent
         * @return TabSpec
         */
        public TabSpec setContent(Intent intent)
        {
            mContentStrategy = new IntentContentStrategy(mTag, intent);
            return this;
        }

        String getTag()
        {
            return mTag;
        }
    }

    /**
     * Specifies what you do to create a tab indicator.
     */
    private static interface IndicatorStrategy
    {

        /**
         * Return the view for the indicator.
         */
        View createIndicatorView();
    }

    /**
     * Specifies what you do to manage the tab content.
     */
    private static interface ContentStrategy
    {

        /**
         * Return the content view. The view should may be cached locally.
         */
        View getContentView();

        /**
         * Perhaps do something when the tab associated with this content has
         * been closed (i.e make it invisible, or remove it).
         */
        void tabClosed();
    }

    /**
     * How to create a tab indicator that just has a label.
     */
    private class LabelIndicatorStrategy implements IndicatorStrategy
    {

        private final CharSequence mLabel;

        private LabelIndicatorStrategy(CharSequence label)
        {
            mLabel = label;
        }

        public View createIndicatorView()
        {
            LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int tabInd = isParent ? R.layout.tab_indicator
                : R.layout.tab_indicator_child;
            View tabIndicator = inflater.inflate(tabInd, mTabWidget, // tab widget is the parent
                false); // no inflate params
            if (isParent)
            {
                tabIndicator.setBackgroundResource(R.drawable.tab_indicator);
            }
            else
            {
                tabIndicator.setBackgroundResource(R.drawable.tab_indicator);
            }

            final TextView tv = (TextView) tabIndicator
                .findViewById(R.id.title);
            tv.setText(mLabel);

            return tabIndicator;
        }
    }

    /**
     * How we create a tab indicator that has a label and an icon
     */
    private class LabelAndIconIndicatorStrategy implements IndicatorStrategy
    {

        private final CharSequence mLabel;
        private final Drawable mIcon;

        private LabelAndIconIndicatorStrategy(CharSequence label, Drawable icon)
        {
            mLabel = label;
            mIcon = icon;
        }

        public View createIndicatorView()
        {
            LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View tabIndicator = inflater.inflate(R.layout.tab_indicator,
                mTabWidget, // tab widget is the parent
                false); // no inflate params

            tabIndicator.setBackgroundResource(R.drawable.tab_indicator);

            final TextView tv = (TextView) tabIndicator
                .findViewById(R.id.title);
            tv.setText(mLabel);

            final ImageView iconView = (ImageView) tabIndicator
                .findViewById(R.id.icon);
            iconView.setImageDrawable(mIcon);

            return tabIndicator;
        }
    }

    /**
     * How to create the tab content via a view id.
     */
    private class ViewIdContentStrategy implements ContentStrategy
    {

        private final View mView;

        private ViewIdContentStrategy(int viewId)
        {
            mView = mTabContent.findViewById(viewId);
            if (mView != null)
            {
                mView.setVisibility(View.GONE);
            }
            else
            {
                throw new RuntimeException(
                    "Could not create tab content because "
                        + "could not find view with id " + viewId);
            }
        }

        public View getContentView()
        {
            mView.setVisibility(View.VISIBLE);
            return mView;
        }

        public void tabClosed()
        {
            mView.setVisibility(View.GONE);
        }
    }

    /**
     * How tab content is managed using {@link TabContentFactory}.
     */
    private class FactoryContentStrategy implements ContentStrategy
    {
        private View mTabContent;
        private final CharSequence mTag;
        private TabContentFactory mFactory;

        public FactoryContentStrategy(CharSequence tag,
            TabContentFactory factory)
        {
            mTag = tag;
            mFactory = factory;
        }

        public View getContentView()
        {
            if (mTabContent == null)
            {
                mTabContent = mFactory.createTabContent(mTag.toString());
            }
            mTabContent.setVisibility(View.VISIBLE);
            return mTabContent;
        }

        public void tabClosed()
        {
            mTabContent.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * How tab content is managed via an {@link Intent}: the content view is the
     * decorview of the launched activity.
     */
    private class IntentContentStrategy implements ContentStrategy
    {

        private final String mTag;
        private final Intent mIntent;

        private View mLaunchedView;

        private IntentContentStrategy(String tag, Intent intent)
        {
            mTag = tag;
            mIntent = intent;
        }

        public View getContentView()
        {
            if (mLocalActivityManager == null)
            {
                throw new IllegalStateException(
                    "Did you forget to call 'public void "
                        + "setup(LocalActivityManager activityGroup)'?");
            }
            final Window w = mLocalActivityManager.startActivity(mTag, mIntent);
            final View wd = w != null ? w.getDecorView() : null;
            if (mLaunchedView != wd && mLaunchedView != null)
            {
                if (mLaunchedView.getParent() != null)
                {
                    mTabContent.removeView(mLaunchedView);
                }
            }
            mLaunchedView = wd;

            // XXX Set FOCUS_AFTER_DESCENDANTS on embedded activies for now so
            // they can get
            // focus if none of their children have it. They need focus to be
            // able to
            // display menu items.
            //
            // Replace this with something better when Bug 628886 is fixed...
            //
            if (mLaunchedView != null)
            {
                mLaunchedView.setVisibility(View.VISIBLE);
                mLaunchedView.setFocusableInTouchMode(true);
                ((ViewGroup) mLaunchedView)
                    .setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
            }
            return mLaunchedView;
        }

        public void tabClosed()
        {
            if (mLaunchedView != null)
            {
                mLaunchedView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置是否是父tab
     * 
     * @param isP boolean
     */
    public void setIsParent(boolean isP)
    {
        isParent = isP;
    }

}
