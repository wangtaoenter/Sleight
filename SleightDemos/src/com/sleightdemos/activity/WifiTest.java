package com.sleightdemos.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

public class WifiTest extends Activity
{
    private static final String TAG = "WifiTest";

    private WifiManager wifiManager;

    private WifiConfiguration wifiConfiguration;

    private WifiInfo wifiInfo;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initWifiManager(this);
    }

    private void initWifiManager(Context context)
    {
        wifiManager = (WifiManager) context
            .getSystemService(Context.WIFI_SERVICE);
        Log.d(TAG, "wifiManager.getWifiState()" + wifiManager.getWifiState());
        Log.d(TAG, "wifiManager.isWifiEnabled()" + wifiManager.isWifiEnabled());
        Log.d(TAG, "wifiManager.getConnectionInfo().getBSSID()"
            + wifiManager.getConnectionInfo().getBSSID());
        Log.d(TAG, "wifiManager.getConnectionInfo().getSSID()"
            + wifiManager.getConnectionInfo().getSSID());
        Log.d(TAG, "wifiManager.getConnectionInfo().getRssi()"
            + wifiManager.getConnectionInfo().getRssi());

        List<ScanResult> scanResults = wifiManager.getScanResults();
        Log.d(TAG, "scanResults == null:" + (scanResults == null));
        for (ScanResult scanResult : scanResults)
        {
            Log.d(TAG, "BSSID:" + scanResult.BSSID + "\tSSID" + scanResult.SSID
                + "\t" + scanResult.describeContents());
        }

        List<WifiConfiguration> wfiConfigurations = wifiManager
            .getConfiguredNetworks();
        Log.d(TAG, "scanResults == null:" + (scanResults == null));
        for (WifiConfiguration wfiConfiguration : wfiConfigurations)
        {
            Log.d(TAG, "wfiConfiguration.networkId:"
                + wfiConfiguration.networkId + "\tBSSID"
                + wfiConfiguration.BSSID + "\tSSID" + wfiConfiguration.SSID
                + "\t" + wfiConfiguration.describeContents());
        }

        WifiConfiguration mWC = new WifiConfiguration();

        mWC.SSID = "s00109348N5";
        mWC.preSharedKey = "2011123456";

        Log.d(TAG, ""
            + wifiManager.enableNetwork(wifiManager.addNetwork(mWC), true));

    }
}
