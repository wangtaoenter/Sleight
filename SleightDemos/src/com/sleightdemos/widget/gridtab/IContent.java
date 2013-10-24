package com.sleightdemos.widget.gridtab;

import android.view.animation.Animation;
import android.widget.ViewFlipper;

public interface IContent
{
    void doContentAction(GridTabActivity context,Animation animation,ViewFlipper viewFlipper);
}
