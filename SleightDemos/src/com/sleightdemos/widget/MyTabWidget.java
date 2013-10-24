package com.sleightdemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class MyTabWidget extends LinearLayout implements OnFocusChangeListener
{

    private OnTabSelectionChanged mSelectionChangedListener;
    private int mSelectedTab = 0;

    /**
     * 
     * @param context Context
     */
    public MyTabWidget(Context context)
    {
        this(context, null);
    }

    /**
     * 
     * @param context Context
     * @param attrs AttributeSet
     */
    public MyTabWidget(Context context, AttributeSet attrs)
    {
        this(context, attrs, com.android.internal.R.attr.tabWidgetStyle);
    }

    /**
     * 
     * @param context Context
     * @param attrs AttributeSet
     * @param defStyle int
     */
    public MyTabWidget(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs);
        initTabWidget();

        TypedArray a = context.obtainStyledAttributes(attrs,
            com.android.internal.R.styleable.TabWidget, defStyle, 0);

        a.recycle();
    }

    /**
     * 
     * 
     * 
     * @param w int
     * @param h int
     * @param oldw int
     * @param oldh int
     * @see android.view.View#onSizeChanged(int, int, int, int)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void initTabWidget()
    {
        setOrientation(LinearLayout.HORIZONTAL);
        // mBottomLeftStrip = mContext
        // .getResources()
        // .getDrawable(com.android.internal.R.drawable.tab_bottom_left);
        // mBottomRightStrip = mContext
        // .getResources()
        // .getDrawable(com.android.internal.R.drawable.tab_bottom_right);
        // Deal with focus, as we don't want the focus to go by default
        // to a tab other than the current tab
        setFocusable(true);
        setOnFocusChangeListener(this);
    }

    /**
     * 
     * 
     * 
     * @param child View
     * @see android.view.ViewGroup#childDrawableStateChanged(android.view.View)
     */
    @Override
    public void childDrawableStateChanged(View child)
    {
        if (child == getChildAt(mSelectedTab))
        {
            // To make sure that the bottom strip is redrawn
            invalidate();
        }
        super.childDrawableStateChanged(child);
    }

    /**
     * 
     * 屏蔽了底下的线
     * 
     * @param canvas Canvas
     * @see android.view.ViewGroup#dispatchDraw(android.graphics.Canvas)
     */
    @Override
    public void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);

    }

    /**
     * Sets the current tab. This method is used to bring a tab to the front of
     * the Widget, and is used to post to the rest of the UI that a different
     * tab has been brought to the foreground.
     * 
     * Note, this is separate from the traditional "focus" that is employed from
     * the view logic.
     * 
     * For instance, if we have a list in a tabbed view, a user may be
     * navigating up and down the list, moving the UI focus (orange
     * highlighting) through the list items. The cursor movement does not effect
     * the "selected" tab though, because what is being scrolled through is all
     * on the same tab. The selected tab only changes when we navigate between
     * tabs (moving from the list view to the next tabbed view, in this
     * example).
     * 
     * To move both the focus AND the selected tab at once, please use
     * {@link #setCurrentTab}. Normally, the view logic takes care of adjusting
     * the focus, so unless you're circumventing the UI, you'll probably just
     * focus your interest here.
     * 
     * @param index The tab that you want to indicate as the selected tab (tab
     *            brought to the front of the widget)
     * 
     * @see #focusCurrentTab
     */
    public void setCurrentTab(int index)
    {
        if (index < 0 || index >= getChildCount())
        {
            return;
        }

        getChildAt(mSelectedTab).setSelected(false);
        mSelectedTab = index;
        getChildAt(mSelectedTab).setSelected(true);
    }

    /**
     * Sets the current tab and focuses the UI on it. This method makes sure
     * that the focused tab matches the selected tab, normally at
     * {@link #setCurrentTab}. Normally this would not be an issue if we go
     * through the UI, since the UI is responsible for calling
     * TabWidget.onFocusChanged(), but in the case where we are selecting the
     * tab programmatically, we'll need to make sure focus keeps up.
     * 
     * @param index The tab that you want focused (highlighted in orange) and
     *            selected (tab brought to the front of the widget)
     * 
     * @see #setCurrentTab
     */
    public void focusCurrentTab(int index)
    {
        final int oldTab = mSelectedTab;

        // set the tab
        setCurrentTab(index);

        // change the focus if applicable.
        if (oldTab != index)
        {
            getChildAt(index).requestFocus();
        }
    }

    /**
     * 
     * 
     * 
     * @param enabled boolean
     * @see android.view.View#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        int count = getChildCount();

        for (int i = 0; i < count; i++)
        {
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }

    /**
     * 
     * 
     * 
     * @param child View
     * @see android.view.ViewGroup#addView(android.view.View)
     */
    @Override
    public void addView(View child)
    {
        if (child.getLayoutParams() == null)
        {
            final LinearLayout.LayoutParams lp = new LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            lp.setMargins(0, 0, 0, 0);
            child.setLayoutParams(lp);
        }

        // Ensure you can navigate to the tab with the keyboard, and you can
        // touch it
        child.setFocusable(true);
        child.setClickable(true);

        super.addView(child);

        // than potentially interfere with the view's listener
        child.setOnClickListener(new TabClickListener(getChildCount() - 1));
        child.setOnFocusChangeListener(this);
    }

    /**
     * Provides a way for {@link TabHost} to be notified that the user clicked
     * on a tab indicator.
     */
    void setTabSelectionListener(OnTabSelectionChanged listener)
    {
        mSelectionChangedListener = listener;
    }

    /**
     * 
     * 
     * 
     * @param v View
     * @param hasFocus boolean
     * @see android.view.View.OnFocusChangeListener#onFocusChange(android.view.View,
     *      boolean)
     */
    public void onFocusChange(View v, boolean hasFocus)
    {
        if (v == this && hasFocus)
        {
            getChildAt(mSelectedTab).requestFocus();
            return;
        }

        if (hasFocus)
        {
            int i = 0;
            while (i < getChildCount())
            {
                if (getChildAt(i) == v)
                {
                    setCurrentTab(i);
                    mSelectionChangedListener.onTabSelectionChanged(i, false);
                    break;
                }
                i++;
            }
        }
    }

    // registered with each tab indicator so we can notify tab host
    /**
     * 
     * TabClickListener
     * 
     * @version [MTVClient_Handset_R001C08L0ZT05, 2010-10-23]
     */
    private class TabClickListener implements OnClickListener
    {

        private final int mTabIndex;

        private TabClickListener(int tabIndex)
        {
            mTabIndex = tabIndex;
        }

        public void onClick(View v)
        {
            mSelectionChangedListener.onTabSelectionChanged(mTabIndex, true);
        }
    }

    /**
     * Let {@link TabHost} know that the user clicked on a tab indicator.
     */
    static interface OnTabSelectionChanged
    {
        /**
         * Informs the TabHost which tab was selected. It also indicates if the
         * tab was clicked/pressed or just focused into.
         * 
         * @param tabIndex index of the tab that was selected
         * @param clicked whether the selection changed due to a touch/click or
         *            due to focus entering the tab through navigation. Pass
         *            true if it was due to a press/click and false otherwise.
         */
        void onTabSelectionChanged(int tabIndex, boolean clicked);
    }

}
