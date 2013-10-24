package com.sleightdemos.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.R;
import com.sleightdemos.view.PageTurnerViewP1;

/**
 * 
 * 翻页效果
 * 
 * @version [v1.0, 2011-7-27]
 */
public class TurnPageActivity extends Activity
{

    PageTurnerViewP1 ptvp;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turn_page);

        ptvp = (PageTurnerViewP1) findViewById(R.id.ptv);
        ptvp.setPageId(R.id.relativeLayout1);
        ptvp.startPageTurn(0);

    }
}
