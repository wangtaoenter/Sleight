package com.sleightdemos.activity;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class PlayerActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String url = "http://218.2.129.55:5018/3gp/3gp/001.wav?msisdn=13800000&spid=1&sid=110202614&pid=1&nettype=0&ctid=2&preview=0&traceuniqueid=60901061106081103030172001&timestamp=20110608110303&encrypt=b20a32d7a2f7cc2eecb31d13c6a319b9";
        play(url);
    }

    private void play(String url)
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try
        {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalStateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
