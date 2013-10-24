package com.sleightdemos.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.sleightdemos.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EarthAnimationActivity extends Activity
{
    protected static final int FORWARD = 0;
    protected static final int REVERSE = 1;

    private Button earthButton;

    private AnimationDrawable earthButtonAnimation;

    protected int direction;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.earth_animation);

        earthButton = (Button) findViewById(R.id.earth_button);
        earthButtonAnimation = (AnimationDrawable) earthButton.getBackground();
        direction = FORWARD;

        earthButton.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {
                if (!earthButtonAnimation.isRunning())
                {
                    earthButtonAnimation.start();
                    earthButton.setText(R.string.click_me_to_stop);
                }
                else
                {
                    earthButtonAnimation.stop();
                    int resId = R.anim.earth_animation_rev;
                    if (direction == FORWARD)
                    {
                        direction = REVERSE;
                    }
                    else
                    {
                        resId = R.anim.earth_animation;
                        direction = FORWARD;
                    }
                    earthButton.setBackgroundResource(resId);
                    earthButtonAnimation = (AnimationDrawable) earthButton.getBackground();
                    earthButton.setText(R.string.click_me_to_stop);
                }
            }

        });
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        (new Timer(false)).schedule(new AnimationTimer(earthButtonAnimation),
            100);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        earthButtonAnimation.stop();
    }

    private static class AnimationTimer extends TimerTask
    {
        AnimationDrawable animation;

        public AnimationTimer(AnimationDrawable animation)
        {
            this.animation = animation;
        }

        @Override
        public void run()
        {
            animation.start();
            this.cancel();
        }

    }

}
