package com.sleightdemos.activity;

import com.sleightdemos.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.SeekBar;

public class AnimSeekBarTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_seek_bar);
        setup((SeekBar) findViewById(R.id.seek_bar0), 25);
        setup((SeekBar) findViewById(R.id.seek_bar1), 50);
        setup((SeekBar) findViewById(R.id.seek_bar2), 75);
    }
    
	private void setup(SeekBar seekBar, int v) {
		Resources res = getResources();
		Drawable d = new AnimSeekBarDrawable(res, v < seekBar.getMax() / 2);
		seekBar.setProgressDrawable(d);
		seekBar.setProgress(v);
	}
}