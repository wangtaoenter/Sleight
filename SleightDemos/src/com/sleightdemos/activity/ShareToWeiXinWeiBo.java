package com.sleightdemos.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleightdemos.R;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXMusicObject;
import com.tencent.mm.sdk.platformtools.Util;

public class ShareToWeiXinWeiBo extends Activity implements IWXAPIEventHandler
{
    private static final String TAG = "ShareToWeiXinWeiBo";
    public static final String APP_ID = "wx70c5b885478690cb";

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.share_to);

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.registerApp(APP_ID);

        Button shareToWeiXinBtn = (Button) findViewById(R.id.share_to_weixin);
        shareToWeiXinBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                shareToWeiXin();
            }
        });
    }

    private void shareToWeiXin()
    {
        WXMusicObject music = new WXMusicObject();
        //music.musicUrl = "http://www.baidu.com";
        //        music.musicUrl = "http://res3.imuapp.cn/resource/2/c38/0ee3a138f14637b34f0f1107a1122c38.mp3?OP=6&PT=107&CHID=1&CT=0&VER=&UID=11193&IMSI=460030367694595";
        //music.musicUrl="http://120.196.211.49/XlFNM14sois/AKVPrOJ9CBnIN556OrWEuGhZvlDF02p5zIXwrZqLUTti4o6MOJ4g7C6FPXmtlh6vPtgbKQ==/31353278.mp3";
        music.musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = "分享title";
        msg.description = "description";

        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.earth0);
        msg.thumbData = Util.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);

        Log.d(TAG, "shareToWeiXin.");
    }

    private String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void onReq(BaseReq arg0)
    {
        Log.d(TAG, "onReq:");
    }

    @Override
    public void onResp(BaseResp arg0)
    {
        Log.d(TAG, "onResp:" + arg0.errCode + " " + arg0.errStr);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        api.unregisterApp();
    }
}
