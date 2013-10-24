/*
 * 文件名: CrashHandler.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2011-7-20
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [v1.0, 2011-7-20]
 */
public class CrashHandler implements UncaughtExceptionHandler
{

    private static final String TAG = "CrashHandler";

    private static final String EXTENSION = ".log";

    private static final String COMMAND = "logcat -v time -f ";

    private String logPath = Environment.getExternalStorageDirectory()
        .toString()
        + "/crash_log/";

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public CrashHandler()
    {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable ex)
    {
        handlerCrash(ex);
        if (mDefaultHandler != null)
        {
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    private void handlerCrash(Throwable ex)
    {
        if (null != ex)
        {
            getDeviceInfo();
            getExceptionInfo(ex);

            File file = new File(logPath);
            if (!file.exists())
            {
                if (!file.mkdirs())
                {
                    Log.w(TAG, "make file failure");
                }
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());

            String fileName = "crash" + timestamp + EXTENSION;
            try
            {
                Runtime.getRuntime().exec(COMMAND + logPath + fileName);
            }
            catch (IOException e)
            {
                Log.e(TAG, "command log  " + e.toString());
            }
        }
    }

    private void getDeviceInfo()
    {
        Log.i(TAG, "MANUFACTURER: " + android.os.Build.MANUFACTURER);
        Log.i(TAG, "MODEL: " + android.os.Build.MODEL);
        Log.i(TAG, "sdkVersion : " + android.os.Build.VERSION.RELEASE);
        Log.i(TAG, "deviceStr : " + android.os.Build.DEVICE);
    }

    private void getExceptionInfo(Throwable ex)
    {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null)
        {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String errorInfo = info.toString();
        printWriter.close();
        Log.e(TAG, errorInfo);
    }
}
