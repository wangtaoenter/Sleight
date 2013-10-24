package com.sleightdemos.widget.gridtab;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;

/**
 * Hold tab contents
 * 
 * @author wangtao
 * @version [V1.0, 2011-10-14]
 */
public class TabCell
{
    private String name;

    private int drawableUnselected = -1;
    
    private int drawableSelected = -1;
    
    private IContent content;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getDrawableUnselected()
    {
        return drawableUnselected;
    }

    public void setDrawableUnselected(int drawableUnselected)
    {
        this.drawableUnselected = drawableUnselected;
    }

    public int getDrawableSelected()
    {
        return drawableSelected;
    }

    public void setDrawableSelected(int drawableSelected)
    {
        this.drawableSelected = drawableSelected;
    }

    public void setContent(Intent intent)
    {
        content = new IntentContent(intent);
    }
    
    public void setContent(Intent intent,Animation animation)
    {
        content = new IntentContent(intent,animation);
    }
    
    public void setContent(View view)
    {
        content = new ViewContent(view);
    }
    
    public void setContent(View view,Animation animation)
    {
        content = new ViewContent(view, animation);
    }
    
    public IContent getContent()
    {
        return content;
    }
}
