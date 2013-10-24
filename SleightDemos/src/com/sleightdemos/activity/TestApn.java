package com.sleightdemos.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sleightdemos.R;
import com.sleightdemos.util.ApnUtil;

public class TestApn extends Activity {

	MyHandler myHandler = new MyHandler();
	TextView nowApn;//当前apn
    TextView netState;//网络连接状态
    Button setCmnet;
    public ProgressDialog pd;
    
    //启动线程，五秒后检测修改结果
    Thread thread = new Thread(){
		public void run(){
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myHandler.sendMessage(myHandler.obtainMessage(1));
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_apn);
        nowApn = (TextView)findViewById(R.id.apnValue);
        netState = (TextView)findViewById(R.id.isConnect);
        //设置apn为cmnet的按钮，添加点击事件
        setCmnet = (Button)findViewById(R.id.setCmnetApn);
        setCmnet.setOnClickListener(setCmnetApn);
        //检测网络是否连接
        Button isConnect = (Button)findViewById(R.id.checkConnect);
        isConnect.setOnClickListener(checkNet);
        init();
    }
    
    /**
     * 初始化页面值
     * */
    private void init(){
    	String apn = ApnUtil.getAPN(this);
    	pd = new ProgressDialog(this);
		pd.setTitle("正在切换中...");
		pd.setIcon(R.drawable.icon);
		pd.setCancelable(false);
    	nowApn.setText(apn);
    	hiddenSet();
    }
    
    /**
     * 如果当前apn为cmnet隐藏设置cmnet按钮
     * */
    private void hiddenSet(){
    	if(ApnUtil.isCmnet(this)){
    		setCmnet.setVisibility(View.INVISIBLE);
    	}else{
    		setCmnet.setVisibility(View.VISIBLE);
    	}
    }
    
    
    /**
     * 点击设置apn的响应处理
     * */
    private OnClickListener setCmnetApn = new OnClickListener() {
		public void onClick(View v) {
			ApnUtil.detectSystemApn(TestApn.this);
			if(pd != null){
				if(!pd.isShowing()){
					pd.show();
				}
			}
			//创建线程：修改apn后要等待一段时间才能得到设置以后的apn
			thread.start();
		}
	};
	
	/**
     * 检测网络连接
     * */
    private OnClickListener checkNet = new OnClickListener() {
		public void onClick(View v) {
			if(ApnUtil.isConnect(TestApn.this)){
				netState.setText("已连接");
			}else{
				netState.setText("未连接");
			}
		}
	};
	
	/**
	 * 创建一个handler 接收消息改变页面信息
	 * */
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
				case 1:
				//接收 线程发送的handler消息，进行判断是否已经切换成功
					String apn = ApnUtil.getAPN(TestApn.this);
			    	nowApn.setText(apn);
			    	netState.setText("");
			    	hiddenSet();
			    	pd.dismiss();
					return;
			}
		}
	}
	
}