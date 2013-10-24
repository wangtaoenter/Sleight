package com.sleightdemos.tabs;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import com.sleightdemos.R;

/**
 * A custom Tab. Tabs are at bottom,contents images and words
 * 
 * @author wangtaoenter
 * @version [2010-4-24]
 */
public class CustomTab extends TabActivity
{

    public static final String Tab1 = "Tab1";
    public static final String Tab2 = "Tab2";
    public static final String Tab3 = "Tab3";
    public static final String Tab4 = "Tab4";
    public static final String Tab5 = "Tab5";

    private TabHost tHost;

    private TabWidget tw;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_tab);

        tHost = this.getTabHost();

        tHost.addTab(tHost.newTabSpec(Tab1).setIndicator("").setContent(R.id.view1));
        tHost.addTab(tHost.newTabSpec(Tab2).setIndicator("").setContent(R.id.view2));
        tHost.addTab(tHost.newTabSpec(Tab3).setIndicator("").setContent(R.id.view3));
        tHost.addTab(tHost.newTabSpec(Tab4).setIndicator("").setContent(R.id.view4));

        CustomLayout ct = new CustomLayout(this);

        tHost.addTab(tHost.newTabSpec(Tab4).setIndicator("").setContent(ct));
        //        tHost.addTab(tHost.newTabSpec(Tab1).setIndicator("").setContent(ct));

        tHost.setOnTabChangedListener(new OnTabChangeListener()
        {
            public void onTabChanged(String tabId)
            {

            }
        });

        LinearLayout ll = (LinearLayout) tHost.getChildAt(0);

        tw = (TabWidget) ll.getChildAt(1);
        tw.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_selected_background));
        updateWidgetView(0, "One", R.drawable.icon);
        updateWidgetView(1, "Two", R.drawable.icon);
        updateWidgetView(2, "Three", R.drawable.icon);
        updateWidgetView(3, "Four", R.drawable.icon);
        updateWidgetView(4, "Five", R.drawable.icon);

    }

    public View composeLayout(String s, int i)
    {
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.single_music_top));
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setSingleLine(true);
        tv.setText(s);
        layout.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));

        ImageView iv = new ImageView(this);
        iv.setImageResource(i);
        layout.addView(iv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT));
        return layout;
    }

    public void updateWidgetView(int i, String text, int image)
    {
        RelativeLayout rl = (RelativeLayout) tw.getChildAt(i);
        rl.removeAllViewsInLayout();
        rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.single_music_top));
        rl.addView(composeLayout(text, image));
        rl.setSelected(true);
    }

    public class CustomLayout implements TabHost.TabContentFactory
    {
        Activity activity;
        LayoutInflater inflaterHelper;

        LinearLayout layout;

        public CustomLayout(Activity a)
        {
            activity = a;

            inflaterHelper = a.getLayoutInflater();
        }

        /** {@inheritDoc} */
        //tag 标记各个标签    
        public View createTabContent(String tag)
        {
            return addCustomView(tag);
        }

        public View addCustomView(String id)
        {

            layout = new LinearLayout(activity);
            layout.setOrientation(LinearLayout.VERTICAL);

            if (id.equals(Tab1))
            {
                ImageView iv = new ImageView(activity);
                iv.setImageResource(R.drawable.icon);
                layout.addView(iv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
            }
            else if (id.equals(Tab2))
            {
                EditText edit = new EditText(activity);
                layout.addView(edit, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));

                Button btn = new Button(activity);
                btn.setText("OK");
                btn.setWidth(100);
                layout.addView(btn, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));

                RadioGroup rGroup = new RadioGroup(activity);
                rGroup.setOrientation(LinearLayout.HORIZONTAL);
                RadioButton radio1 = new RadioButton(activity);
                radio1.setText("Radio A");
                rGroup.addView(radio1);
                RadioButton radio2 = new RadioButton(activity);
                radio2.setText("Radio B");
                rGroup.addView(radio2);

                layout.addView(rGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            else if (id.equals(Tab3))
            {

                LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT);

                layout.addView(inflaterHelper.inflate(R.layout.top_right_corner, null), param3);
            }
            else if (id.equals(Tab4))
            {
                TextView tv = new TextView(activity);
                tv.setText("HelloTags!");
                tv.setGravity(Gravity.CENTER);
                layout.addView(tv);
            }

            return layout;
        }

    }

}
