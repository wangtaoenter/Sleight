package com.sleightdemos.widget.gridtab;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

public class ViewContent implements IContent
{
    private View view;
    
    /**
     * animation when show this page
     */
    private Animation animation;
    
    public ViewContent(View view)
    {
        this.view = view;
    }
    
    public ViewContent(View view,Animation animation)
    {
        this.view = view;
        this.animation = animation;
    }

    @Override
    public void doContentAction(GridTabActivity context, Animation animation,
        ViewFlipper viewFlipper)
    {
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
