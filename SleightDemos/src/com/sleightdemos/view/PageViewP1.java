package com.sleightdemos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class PageViewP1 extends RelativeLayout
{
    public static final int CORNER_BOTTOM_LEFT = 0;
    public static final int CORNER_BOTTOM_RIGHT = 1;
    public static final int CORNER_TOP_LEFT = 2;
    public static final int CORNER_TOP_RIGHT = 3;
    private Path mClipPath;
    private PageTurnerViewP1 mPageTurner;
    private Callback mCallback;
    private int mCorner;
    private Drawable mBackPage;
    private Drawable mPageBackground;

    public PageViewP1(Context context)
    {
        super(context);
    }

    public PageViewP1(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //      TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PageTurner);
        //      this.getResources().getDrawable(com.tom.clip.R.drawable.page);
        //      this.mBackPage = this.getResources().getDrawable(com.tom.clip.R.drawable.a1);
        //      this.mPageBackground =this.getResources().getDrawable(com.tom.clip.R.drawable.a2);

        this.mBackPage = this.getBackground();
        this.mCorner = -1;
    }

    void setPageTurner(PageTurnerViewP1 pageTurnerViewP1)
    {
        this.mPageTurner = pageTurnerViewP1;
    }

    void setClipPath(Path clipPath)
    {
        this.mClipPath = clipPath;
    }

    public void setCallback(Callback callback)
    {
        this.mCallback = callback;
    }

    void drawBackPage(Canvas canvas)
    {
        if (this.mCallback != null)
            this.mCallback.onDrawBackPage(canvas);
    }

    void drawBackground(Canvas canvas)
    {
        if (this.mCallback != null)
            this.mCallback.onDrawBackground(canvas);
    }

    public void startPageTurn()
    {
        if (this.mPageTurner != null)
            this.mPageTurner.startPageTurn(0);
    }

    void onPageTurnFinished(Canvas canvas)
    {
        if (this.mCallback != null)
        {
            this.mCallback.onPageTurnFinished(canvas);
        }

        this.mClipPath = null;
    }

    protected void dispatchDraw(Canvas canvas)
    {

        if (this.mClipPath != null)
        {
            canvas.save();
            canvas.clipPath(this.mClipPath, Region.Op.INTERSECT);
        }
        super.dispatchDraw(canvas);
        if (this.mClipPath != null)
            canvas.restore();
    }

    public void setCorner(int corner)
    {
        this.mCorner = corner;
    }

    public int getCorner()
    {
        return this.mCorner;
    }

    public void setBackPage(Drawable backPage)
    {
        this.mBackPage = backPage;
    }

    public Drawable getBackPage()
    {
        return this.mBackPage;
    }

    public void setPageBackground(Drawable background)
    {
        this.mPageBackground = background;
    }

    public Drawable getPageBackground()
    {
        return this.mPageBackground;
    }

    public static abstract class Callback
    {
        public void onDrawBackPage(Canvas canvas)
        {
            Log.v("Callback", "drawing back page");
        }

        public void onDrawBackground(Canvas canvas)
        {
            Log.v("Callback2", "drawing back page");
        }

        public void onPageTurnFinished(Canvas canvas)
        {
            Log.v("Callback3", "drawing back page");
        }
    }
}
