package com.alexmirash.parallaxheaderviewpagersample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Mirash
 */
public class GridSampleAdapter extends BaseAdapter {
    private List<Item> mItems;
    private Context mContext;

    public GridSampleAdapter(Context context, List<Item> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new ImageView(mContext);
            ((ImageView) view).setScaleType(ImageView.ScaleType.MATRIX);
        }
        Item item = getItem(i);
        ((ImageView) view).setImageResource(item.drawableId);
        return view;
    }

    public static class Item {
        public final int drawableId;

        public Item(int drawableId) {
            this.drawableId = drawableId;
        }
    }
}
