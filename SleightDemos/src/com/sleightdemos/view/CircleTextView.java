package com.sleightdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.view.View;

/*
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [Examlevel2, 2011-6-8]
 */
public class CircleTextView extends View
{
    Paint paint = new Paint();
    {
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setAlpha(70);
    };

    Paint bgPaint = new Paint();
    {
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.WHITE);
    };

    Path path = new Path();
    private static final String QUOTE = "Now is the time for all good men to come to the aid of their country.";

    public CircleTextView(Context context)
    {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        path.addCircle(150, 150, 100, Direction.CW);
        path.addRect(50, 250, 250, 360, Direction.CCW);
        canvas.drawPath(path, bgPaint);
        canvas.drawTextOnPath(QUOTE, path, 200, 20, paint);
    }

}
