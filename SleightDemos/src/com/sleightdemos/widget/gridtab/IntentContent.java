package com.sleightdemos.widget.gridtab;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

public class IntentContent implements IContent
{
    /**
     * intent
     */
    private Intent intent;

    /**
     * animation when show this page
     */
    private Animation animation;

    /**
     * compose the IntentContent
     * 
     * @param i Intent,should contain the target activity
     */
    public IntentContent(Intent i)
    {
        intent = i;
    }

    /**
     * compose the IntentContent
     * 
     * @param i Intent,should contain the target activity
     * @param animation animation when show this page
     */
    public IntentContent(Intent i, Animation animation)
    {
        intent = i;
        this.animation = animation;
    }

    @Override
    public void doContentAction(GridTabActivity context, Animation animation,
        ViewFlipper viewFlipper)
    {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        View view = context.getLocalActivityManager().startActivity("", intent)
            .getDecorView();
        //切换activity时显示的动画效果
        if (this.animation != null)
        {
            view.startAnimation(this.animation);
        }
        else if (animation != null)
        {
            view.startAnimation(animation);
        }
        viewFlipper.removeAllViews();
        viewFlipper.addView(view);
        viewFlipper.showNext();
    }
}
