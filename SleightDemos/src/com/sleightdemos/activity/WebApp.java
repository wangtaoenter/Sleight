package com.sleightdemos.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [v1.0, 2011-6-30]
 */
public class WebApp extends Activity
{
    private static final String TAG = "WebApp";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.web_app);
        webView = (WebView) findViewById(R.id.webAppView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);

        webView.setScrollBarStyle(-1);
        webView.addJavascriptInterface(new Object()
        {
            public void exit(String title, String message)
            {
                Log.d(TAG, "exitJS called");
                AlertDialog dialog = new AlertDialog.Builder(WebApp.this).setTitle(title).setMessage(message)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {

                        public void onClick(DialogInterface dialog, int which)
                        {
                            exitWebApp();
                        }

                    }).setNegativeButton("No", new DialogInterface.OnClickListener()
                    {

                        public void onClick(DialogInterface dialog, int which)
                        {
                        }

                    }).create();
                dialog.show();
            }
        }, "webapp");

        webView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (event.getAction() == KeyEvent.ACTION_UP)
            {
                webView.loadUrl("javascript:alert('BUTTON1')");
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void exitWebApp()
    {
        finish();
    }

}
