package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [, 2010-8-2]
 */
public class MyMenuTest extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_menu);
    }

    public static void setMenuEvent(LinearLayout layout)
    {
        if (layout.getVisibility() == android.view.View.GONE)
        {
            layout.setVisibility(android.view.View.VISIBLE);
        }
        else
        {
            layout.setVisibility(android.view.View.GONE);
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_MENU)
        {
            LinearLayout menu = (LinearLayout) findViewById(R.id.menu);
            //          for (int i = 0; i < ll.getChildCount(); i++) {
            //              Button menu = (Button) ll.getChildAt(i);
            //          isShowMenu = Method.setMenuEvent(ll, isShowMenu);
            setMenuEvent(menu);
            //          if (!isShowMenu) {
            //                  ll.setVisibility(View.VISIBLE);
            //                  menu.setVisibility(View.VISIBLE);
            //              } else {
            //                  ll.setVisibility(View.GONE);
            //                  menu.setVisibility(View.INVISIBLE);
        }
        //          }
        //          isShowMenu = !isShowMenu;
        return super.onKeyUp(keyCode, event);
    }
}
