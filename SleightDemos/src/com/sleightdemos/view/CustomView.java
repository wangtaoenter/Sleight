package com.sleightdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version
 */
public class CustomView extends ImageView
{
    public CustomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setBackgroundColor(Color.WHITE);
    }

    public CustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
    }

    public CustomView(Context context)
    {
        super(context);
        setBackgroundColor(Color.WHITE);
    }

    boolean drawGlow = false;
    //this is the pixel coordinates of the screen 
    float glowX = 0;
    float glowY = 0;
    //this is the radius of the circle we are drawing 
    float radius = 20;
    //this is the paint object which specifies the color and alpha level  
    //of the circle we draw 
    Paint paint = new Paint();
    {
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setAlpha(70);
    };

    Paint textPainter = new Paint();
    {
        textPainter.setColor(Color.argb(200, 223, 120, 100));
        textPainter.setTextSize(35);
        textPainter.setShadowLayer(5f, 2.5f, 2.5f, Color.GRAY);
    };

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (drawGlow)
        {
            canvas.drawCircle(glowX, glowY, radius, paint);
            canvas.drawText("Painting", 0f, 40f, textPainter);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            drawGlow = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP)
        {
            drawGlow = false;
        }
        glowX = event.getX();
        glowY = event.getY();
        this.invalidate();
        return true;
    }
}
