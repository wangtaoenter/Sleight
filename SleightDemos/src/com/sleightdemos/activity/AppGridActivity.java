package com.sleightdemos.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.sleightdemos.R;

public class AppGridActivity extends Activity
{
    GridView mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        loadApps(); // do this in onresume?

        setContentView(R.layout.grid_1);
        mGrid = (GridView) findViewById(R.id.myGrid);
        mGrid.setAdapter(new AppsAdapter(this));
    }

    private List<ResolveInfo> mApps;

    private void loadApps()
    {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public class AppsAdapter extends BaseAdapter
    {
        private final LayoutInflater mInflater;

        public AppsAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            //            ImageView i;
            //
            //            if (convertView == null) {
            //                i = new ImageView(Grid1.this);
            //                i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //                i.setLayoutParams(new GridView.LayoutParams(50, 50));
            //            } else {
            //                i = (ImageView) convertView;
            //            }
            //
            //            ResolveInfo info = mApps.get(position);
            //            i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            //
            //            return i;

            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.application_boxed,
                    parent, false);
            }
            ResolveInfo info = mApps.get(position);
            //            if (!info.filtered) {
            //                info.icon = Utilities.createIconThumbnail(info.icon, getContext());
            //                info.filtered = true;
            //            }

            final TextView textView = (TextView) convertView;
            //            info.iconBitmap.setDensity(Bitmap.DENSITY_NONE);
            textView.setBackgroundResource(R.drawable.dialogbg);
            Bitmap b = makeDefaultIcon(info.activityInfo
                .loadIcon(getPackageManager()));
            textView.setCompoundDrawablesWithIntrinsicBounds(null,
                new BitmapDrawable(b), null, null);
            textView.setText(info.activityInfo.loadLabel(getPackageManager()));
            return convertView;
        }

        private Bitmap makeDefaultIcon(Drawable d)
        {
            Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            d.setBounds(0, 0, b.getWidth(), b.getHeight());
            d.draw(c);
            return b;
        }

        public final int getCount()
        {
            return mApps.size();
        }

        public final Object getItem(int position)
        {
            return mApps.get(position);
        }

        public final long getItemId(int position)
        {
            return position;
        }
    }

}
