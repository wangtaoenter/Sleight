package com.sleightdemos.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class OwnTextApperance extends Activity {
	
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		textView.setTypeface(Typeface.createFromAsset(this.getAssets(), "ITCKRIST.TTF"));
		textView.setText("The quick brown fox jumps over the lazy dog.1234567890");
		textView.append("中文输入是什么样子的？");
		setContentView(textView);
	}
}
