package com.sleightdemos.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [Examlevel2, 2011-5-27]
 */
public class BuildInfo extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_info);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        TextView t = (TextView) findViewById(R.id.infos);
        HashMap<String, String> infos = new HashMap<String, String>();
        infos.put("Build.BOARD", Build.BOARD);
        //        infos.put("Build.BOOTLOADER", Build.BOOTLOADER);
        infos.put("Build.BRAND", Build.BRAND);
        infos.put("Build.CPU_ABI", Build.CPU_ABI);
        //        infos.put("Build.CPU_ABI2", Build.CPU_ABI2);
        infos.put("Build.DEVICE", Build.DEVICE);
        infos.put("Build.DISPLAY", Build.DISPLAY);
        infos.put("Build.FINGERPRINT", Build.FINGERPRINT);
        //        infos.put("Build.HARDWARE", Build.HARDWARE);
        infos.put("Build.HOST", Build.HOST);
        infos.put("Build.ID", Build.ID);
        infos.put("Build.MANUFACTURER", Build.MANUFACTURER);
        infos.put("Build.MODEL", Build.MODEL);
        infos.put("Build.PRODUCT", Build.PRODUCT);
        //        infos.put("Build.RADIO", Build.RADIO);
        infos.put("Build.TAGS", Build.TAGS);
        infos.put("Build.TYPE", Build.TYPE);
        infos.put("Build.USER", Build.USER);
        infos.put("Build.VERSION.CODENAME", Build.VERSION.CODENAME);
        infos.put("Build.VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL);
        infos.put("Build.VERSION.RELEASE", Build.VERSION.RELEASE);
        infos.put("Build.VERSION.SDK", Build.VERSION.SDK);
        infos.put("Build.VERSION.SDK_INT", String.valueOf(Build.VERSION.SDK_INT));
        infos.put("getApplicationInfo().dataDir", getApplicationInfo().dataDir);
        infos.put("metric.density", Float.toString(metric.density));
        infos.put("metric.densityDpi", Integer.toString(metric.densityDpi));

        Set<String> keys = infos.keySet();
        Iterator<String> iKey = keys.iterator();
        while (iKey.hasNext())
        {
            String key = iKey.next();
            t.append(key + ":" + infos.get(key) + "\r\n");
        }
    }
}
