package com.sleightdemos.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [2010-4-8]
 */
public class WebPageBackgroundColorBridgeActivity extends Activity
{
    private static final int OPAQUE = 0x00FFFFFF;

    private EditText url;
    private WebView webView;
    private Button go;
    private SeekBar colorBar;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_page_background);

        url = (EditText) findViewById(R.id.url);
        go = (Button) findViewById(R.id.go);
        webView = (WebView) findViewById(R.id.webview);
        colorBar = (SeekBar) findViewById(R.id.colorbar);

        // 浏览器的选择
        webView.setWebViewClient(new WebViewClient()
        {

            /*
             * (non-Javadoc)
             * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android .webkit.WebView, java.lang.String)
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                WebPageBackgroundColorBridgeActivity.this.url.setText(url);
                load();
                return true;
            }

        });

        webView.getSettings().setJavaScriptEnabled(true);

        // ENTER 按键
        url.setOnKeyListener(new OnKeyListener()
        {

            public boolean onKey(View arg0, int key, KeyEvent arg2)
            {
                if (key == KeyEvent.KEYCODE_ENTER)
                {
                    load();
                    return true;
                }

                return false;
            }

        });

        // load page when we click go
        go.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {
                load();
            }

        });

        // 改变颜色
        colorBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch)
            {
                float p100 = progress / 100.0f;
                webView.loadUrl(String.format("javascript:{document.body.style.backgroundColor='#%06x'}",
                    Color.HSVToColor(new float[] {360 * p100, p100, 1 - p100 }) & OPAQUE));
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub

            }

        });

        load();

    }

    private void load()
    {
        webView.loadUrl(url.getText().toString());
        webView.requestFocus();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
        {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
