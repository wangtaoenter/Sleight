package com.sleightdemos.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class ApnUtil {
	private static String apn; // 保存APN信息的变量
	private static final Uri APN_TABLE_URI = Uri
			.parse("content://telephony/carriers");// 系统数据路径
	private static final Uri PREFERRED_APN_URI = Uri
			.parse("content://telephony/carriers/preferapn");// 系统apn路径
	private static final String APNNAME = "CMNET";// apn名称:任意
	private static final String APN = "cmnet";// apn

	/**
	 * 获取联网方式：APN 通过context取得ConnectivityManager中的NetworkInfo里关于apn的联网信息
	 * 
	 * @param context
	 * @return apn
	 */
	public static String getAPN(Context context) {
		// 通过context得到ConnectivityManager连接管理
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 通过ConnectivityManager得到NetworkInfo网络信息
		NetworkInfo info = manager.getActiveNetworkInfo();
		// 获取NetworkInfo中的apn信息
		if (info != null) {
			apn = info.getExtraInfo();
			if (apn == null) {
				apn = "取不到移动网络信息";
			}
		} else {
			apn = "取不到网络信息";
		}
		return apn;
	}

	/**
	 * 判断apn是否cmnet
	 * 
	 * @param context
	 * @return true : apn是cmnet
	 * @return false: apn不是cmnet
	 */
	public static boolean isCmnet(Context context) {
		getAPN(context);
		if (apn.equals("cmnet")) {
			return true;
		}
		return false;
	}

	/**
	 * 检测网络是否连接（注：需要在配置文件即AndroidManifest.xml加入权限）
	 * 
	 * @param context
	 * @return true : 网络连接成功
	 * @return false : 网络连接失败
	 * */
	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			// 获取网络连接管理的对象
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				// 判断当前网络是否已经连接
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 对系统的apn数据库表进行遍历，若没有cmnet则创建改apn，若存在则设置cmnet为当前的apn
	 * 
	 * @param context
	 **/
	public static void detectSystemApn(final Context context) {
		Cursor c = context.getContentResolver().query(APN_TABLE_URI, null,
				null, null, null);
		// 检测系统apn，若没有apn则默认添加一个cmnet的apn;
		if (c == null) {
			InsertAPN(context, APNNAME, APN);
			return;
		}
		// 遍历系统apn，若存在cmnet apn 设置cmnet为当前的apn
		if (c.moveToFirst()) {
			do {
				String row = "";
				int i = c.getColumnIndex("name");
				row += c.getString(i);
				if (row.equals("CMNET")) {
					// 获得 cmnet apn的id
					int apn_id = Integer.parseInt(c.getString(c
							.getColumnIndex("_id")));
					c.close();
					// 修改apn设置
					// updateApn(context,apn_id);
					// 设置cment为当前的apn
					SetNowAPN(context, apn_id);
					return;
					// return true;
				}
			} while (c.moveToNext());
		}
		c.close();
		// 遍历系统apn，不存在cmnet apn 添加一个cmnet的apn
		InsertAPN(context, APNNAME, APN);
	}

	/**
	 * 向系统apn表中插入cmnet apn
	 * 
	 * @param context
	 * @param name
	 *            APN名称
	 * @param apn
	 *            apn
	 * */
	private static void InsertAPN(final Context context, final String name,
			final String apn) {
		int id = -1;
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("apn", apn);
		values.put("numeric", "46001");
		values.put("proxy", "");
		values.put("type", "default");
		values.put("mcc", "460");
		values.put("mnc", "01");
		values.put("port", "");
		values.put("mmsproxy", "");
		values.put("mmsport", "");
		values.put("user", "");
		values.put("server", "");
		values.put("password", "");
		values.put("mmsc", "");

		Cursor c = null;
		try {
			Uri newRow = resolver.insert(APN_TABLE_URI, values);
			if (newRow != null) {
				c = resolver.query(newRow, null, null, null, null);
				int idindex = c.getColumnIndex("_id");
				c.moveToFirst();
				id = c.getShort(idindex);
			}
		} catch (SQLException e) {
		}

		if (c != null) {
			c.close();
		}
		SetNowAPN(context, id);
	}

	/**
	 * 把指定的apn设置为当前的apn
	 * 
	 * @param context
	 * @param id
	 *            系统数据库表中要设置为当前apn的id值
	 * */
	private static void SetNowAPN(final Context context, final int id) {
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("apn_id", id);
		try {
			resolver.update(PREFERRED_APN_URI, values, null, null);
		} catch (SQLException e) {
		}

	}

	/**
	 * 更改apn信息，防止用户设置错误的cmnet apn,设置错误的apn以后在手机不会显示但是系统数据库已经存在该apn
	 * 
	 * @param id
	 *            apn在 表的位置
	 * */
	private static void updateApn(final Context context, final int id) {
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("numeric", "46001");
		values.put("proxy", "");
		values.put("type", "default");
		values.put("mcc", "460");
		values.put("mnc", "01");
		values.put("port", "");
		values.put("mmsproxy", "");
		values.put("mmsport", "");
		values.put("user", "");
		values.put("server", "");
		values.put("password", "");
		values.put("mmsc", "");
		resolver.update(APN_TABLE_URI, values, "_id=" + id, null);
	}
}
