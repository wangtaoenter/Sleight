package com.sleightdemos.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sleightdemos.R;

public class ShareToFB extends Activity implements OnClickListener
{
    private Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_fb);
        
        button = (Button)findViewById(R.id.shareBtn);
        button.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        String url = "http://share.renren.com/share/buttonshare.do?link=http://v.youku.com/v_playlist/f15451781o1p0.html&title=Rain";
        
        Uri uri= Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);  
        startActivity(intent);
    }
}
