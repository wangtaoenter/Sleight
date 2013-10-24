/*
 * 文件名: GalleryTab.java 版 权： Copyright a Tech. Co. Ltd. All Rights
 * Reserved. 描 述: [该类的简要描述] 创建人: yaoge 创建时间:2010-4-22 修改人： 修改时间: 修改内容：[修改内容]
 */
package com.sleightdemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * 继承自父类主要屏蔽掉父类的快速拖动事件<BR>
 * [功能详细描述]
 * 
 * @author yaoge
 * @version [ME MTVClient_Handset R001C08L0ZT01, 2010-4-22]
 */
public class GalleryTab extends Gallery
{

    /**
     * [构造简要说明]
     * @param context Context
     */
    public GalleryTab(Context context)
    {
        super(context);
    }

    /**
     * [构造简要说明]
     * @param context Context
     * @param attrs AttributeSet
     * @param defStyle defStyle
     */
    public GalleryTab(Context context, AttributeSet attrs, int defStyle)
    {
        super(context,
            attrs,
            defStyle);
    }

    /**
     * [构造简要说明]
     * @param context Context
     * @param attrs AttributeSet
     */
    public GalleryTab(Context context, AttributeSet attrs)
    {
        super(context,
            attrs);
    }

    /**
     * onFling<BR>
     * [功能详细描述]
     * @param e1 MotionEvent
     * @param e2 MotionEvent
     * @param velocityX velocityX
     * @param velocityY velocityY
     * @return boolean 
     * @see android.widget.Gallery#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
        float velocityY)
    {
        return false;
    }
    
}
