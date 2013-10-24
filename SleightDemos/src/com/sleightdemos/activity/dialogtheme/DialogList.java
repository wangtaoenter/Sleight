package com.sleightdemos.activity.dialogtheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [2010-4-15]
 */
public class DialogList extends ListActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", "Dialog Theme");
        Intent intent = new Intent(this, DialogTheme.class);
        temp.put("intent", intent);
        myData.add(temp);

        Map<String, Object> temp2 = new HashMap<String, Object>();
        temp2.put("title", "Normal Theme");
        Intent intent2 = new Intent(this, DialogTheme.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("normalStyle", true);
        intent2.putExtras(bundle);
        temp2.put("intent", intent2);
        myData.add(temp2);

        setListAdapter(new SimpleAdapter(this, myData,
            android.R.layout.simple_list_item_1, new String[] {"title" },
            new int[] {android.R.id.text1 }));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Map map = (Map) l.getItemAtPosition(position);
        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }

}
