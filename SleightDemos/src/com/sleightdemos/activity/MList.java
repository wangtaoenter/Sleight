package com.sleightdemos.activity;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sleightdemos.R;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [2010-4-28]
 */
public class MList extends ListActivity
{
    private static class ProductSearchAdapter extends BaseAdapter
    {

        private LayoutInflater mInflater;
        private Bitmap mIcon1;
        private Bitmap mIcon2;

        public ProductSearchAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
            mIcon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.add);
            mIcon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cancel);
        }

        public int getCount()
        {
            return DATA.length;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;
            Button btn = null;

            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.mlist, null);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                btn = (Button) convertView.findViewById(R.id.button);
                convertView.setTag(holder);
            }
            else
            {
                // Get the ViewHolder back to get fast access to the TextView 
                // and the ImageView. 
                holder = (ViewHolder) convertView.getTag();
            }

            // Bind the data efficiently with the holder. 
            holder.text.setText(DATA[position]);
            holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);

            holder.icon.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Log.i("image", " u clicked on icon Position" + position);

                }
            });
            holder.text.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Log.i("Text", " u clicked on text Position" + position);

                }
            });

            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Log.i("Button", "U clicked on button" + position);

                }
            });

            return convertView;
        }

        static class ViewHolder
        {
            TextView text;
            ImageView icon;
        }

        private static final String[] DATA = {"Abbaye de Belloc", "Abbaye du Mont des Cats" };

    }

    ListView product_search_list;
    Button srch_btn;
    EditText srch_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setListAdapter(new ProductSearchAdapter(this));
        // setContentView(R.layout.productsearch); 
        // getListView().setEmptyView(findViewById(R.id.text)); 
        // srch_text = (EditText)findViewById(R.id.prdsearchtb); 
        // srch_btn = (Button) findViewById(R.id.prdsearchtb); 
        // srch_btn.setOnClickListener(new View.OnClickListener() { 
        //       
        // @Override 
        // public void onClick(View v) { 
        // callProductSearchAdapter(); 
        // 
        // } 
        // }); 

    }

    void callProductSearchAdapter()
    {
        setListAdapter(new ProductSearchAdapter(this));
    }
    //
    //    private void createDialog(String title, String text, final Intent i)
    //    {
    //        if (i == null)
    //        {
    //            AlertDialog ad = new AlertDialog.Builder(this).setIcon(R.drawable.personal_listen)
    //                .setPositiveButton("Ok", null)
    //                .setTitle(title)
    //                .setMessage(text)
    //                .create();
    //            ad.show();
    //        }
    //    }

}
