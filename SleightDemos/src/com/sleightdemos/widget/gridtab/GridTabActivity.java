package com.sleightdemos.widget.gridtab;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.sleightdemos.R;

/**
 * A TabActiviy base on GridView
 * 
 * @author wangtao
 * @version [V1.0, 2011-10-14]
 */
public class GridTabActivity extends ActivityGroup
{
    private static final String TAG = "GridTabActivity";
    /**
     * view flipper that contains activity view
     */
    private ViewFlipper viewFlipper;

    /**
     * grid view that contains tab items
     */
    private GridView gridView;

    private ArrayList<TabCell> tabCells = new ArrayList<TabCell>();

    private OnTabChangeListener onTabChangeListener;

    //上次点击的位置，当前点击的位置
    private int lastClickPosition, clickPosition;

    private int tabSelectedBackgroudRes = -1;

    private Animation tabChangeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gird_tab);
        initViews();
        initAdapter();
    }

    @Override
    protected void onStart()
    {
        Log.d(TAG, "onStart");
        super.onStart();
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setNumColumns(tabCells.size());
        gridView.setGravity(Gravity.CENTER);
        toContent(0);
    }

    private void initViews()
    {
        viewFlipper = (ViewFlipper) findViewById(R.id.content_holder);
        gridView = (GridView) findViewById(R.id.gridview);
    }

    private void initAdapter()
    {
        gridView.setAdapter(new BottomGridViewAdapter(this));
    }

    public void setTabBackgroudRes(int resId)
    {
        gridView.setBackgroundResource(resId);
    }

    public void setTabSelectedBackgroudRes(int resId)
    {
        tabSelectedBackgroudRes = resId;
    }

    /**
     * Set animation to all tabcell. if a special animation was set to the
     * tabcell when it was created,then the special animation will be used to
     * this tabcell.
     * 
     * @param animation animation to all tabcell
     */
    public void setTabChangeAnimation(Animation animation)
    {
        tabChangeAnimation = animation;
    }

    /**
     * set the tab change listener
     * 
     * @param onChangeListener
     */
    public void setOnTabChangeListener(OnTabChangeListener onChangeListener)
    {
        this.onTabChangeListener = onChangeListener;
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3)
            {
                //Log.i(TAG, "arg2="+arg2+"; arg3="+arg3);
                lastClickPosition = clickPosition;
                clickPosition = arg2;
                if (lastClickPosition == clickPosition)
                    return; //防止重复点击同一个菜单

                RelativeLayout temp = (RelativeLayout) gridView
                    .getChildAt(arg2);
                if (tabSelectedBackgroudRes != -1)
                {
                    temp.setBackgroundResource(tabSelectedBackgroudRes);
                }
                else
                {
                    temp.setBackgroundColor(Color.GRAY);
                }
                temp.getChildAt(0).setBackgroundResource(
                    tabCells.get(arg2).getDrawableSelected());
                onTabChangeListener.onTabChanged(arg2);
                for (int i = 0; i < tabCells.size(); i++)
                {
                    if (i != arg2)
                    {
                        RelativeLayout temp2 = (RelativeLayout) gridView
                            .getChildAt(i);
                        temp2.setBackgroundDrawable(null);
                        temp2.getChildAt(0).setBackgroundResource(
                            tabCells.get(i).getDrawableUnselected());
                    }
                }
                toContent(arg2);
            }
        });
    }

    private void toContent(int activityIndex)
    {
        IContent content = tabCells.get(activityIndex).getContent();
        content.doContentAction(this, tabChangeAnimation, viewFlipper);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume()
    {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    /**
     * 
     * add a tabHolder to the tab
     * 
     * @param tabHolder
     */
    public void addTab(TabCell tabHolder)
    {
        tabCells.add(tabHolder);
    }

    private class BottomGridViewAdapter extends BaseAdapter
    {

        private LayoutInflater mInflater;

        public BottomGridViewAdapter(Context ctx)
        {
            this.mInflater = LayoutInflater.from(ctx);
        }

        @Override
        public int getCount()
        {
            return tabCells.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            MenuViewHolder viewHolder;
            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.item_tab, null);
                viewHolder = new MenuViewHolder();
                viewHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.image_item);
                viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.text_item);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (MenuViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setBackgroundResource(tabCells.get(position)
                .getDrawableUnselected());
            viewHolder.textView.setText(tabCells.get(position).getName());

            return convertView;
        }

    }

    private final class MenuViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }

    public interface OnTabChangeListener
    {
        public void onTabChanged(int index);
    }
}
