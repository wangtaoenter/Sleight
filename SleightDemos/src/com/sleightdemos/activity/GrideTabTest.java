package com.sleightdemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sleightdemos.R;
import com.sleightdemos.animation.Rotate3dAnimation;
import com.sleightdemos.animation.WindowAnimation;
import com.sleightdemos.view.CircleTextView;
import com.sleightdemos.widget.gridtab.GridTabActivity;
import com.sleightdemos.widget.gridtab.TabCell;

public class GrideTabTest extends GridTabActivity
{
    private static final String TAG = "GrideTabTest";
    TabCell tab1,tab2,tab3,tab4,tab5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        tab1 = new TabCell();
        tab1.setContent(new Intent(this,AnimSeekBarTest.class),new WindowAnimation(500));
        tab1.setName("tab1");
        tab1.setDrawableSelected(R.drawable.tab_app_select);
        tab1.setDrawableUnselected(R.drawable.tab_app_unselect);
        addTab(tab1);
        
        tab2 = new TabCell();
        tab2.setContent(new Intent(this,GifActivity.class),new Rotate3dAnimation(0,180,200,0,0,true));
        tab2.setName("tab2");
        tab2.setDrawableSelected(R.drawable.tab_category_selected);
        tab2.setDrawableUnselected(R.drawable.tab_category_normal);
        addTab(tab2);
        
        tab3 = new TabCell();
        tab3.setContent(new Intent(this,BuildInfo.class),new WindowAnimation(500));
        tab3.setName("tab3");
        tab3.setDrawableSelected(R.drawable.tab_home_selected);
        tab3.setDrawableUnselected(R.drawable.tab_home_normal);
        addTab(tab3);
        
        tab4 = new TabCell();
        tab4.setContent(new CircleTextView(this),new WindowAnimation(500));
        tab4.setName("tab4");
        tab4.setDrawableSelected(R.drawable.tab_rank_selected);
        tab4.setDrawableUnselected(R.drawable.tab_rank_normal);
        addTab(tab4);
        
        tab5 = new TabCell();
        tab5.setContent(new Intent(this,AnimSeekBarTest.class));
        tab5.setDrawableSelected(R.drawable.tab_app_select);
        tab5.setDrawableUnselected(R.drawable.tab_app_unselect);
        addTab(tab5);
        
        setTabBackgroudRes(R.drawable.tab_bg);
        setTabChangeAnimation(new WindowAnimation(500));
        setTabSelectedBackgroudRes(R.drawable.product_title_bg);
        setOnTabChangeListener(new OnTabChangeListener()
        {
            @Override
            public void onTabChanged(int index)
            {
                Log.d(TAG, "onTabChanged,index:"+index);
            }
        });
    }
}
