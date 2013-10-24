package com.sleightdemos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author music
 * @version [V1.0, 2013-9-30]
 */
public class MusicFxTest extends Activity implements OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goto_musicfx);

        Button btn = (Button) findViewById(R.id.goMusicfx);
        Button btn1 = (Button) findViewById(R.id.goMusicfx1);

        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id)
        {
            case R.id.goMusicfx:
                Intent i = new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
                startActivity(i);
                break;
            case R.id.goMusicfx1:
                Intent i1 = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
                startActivity(i1);
                break;
            default:
                break;
        }
    }
}
