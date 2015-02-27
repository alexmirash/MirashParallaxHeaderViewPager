package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

import android.support.v4.app.Fragment;
import android.widget.AbsListView;


public abstract class ScrollTabHolderFragment extends Fragment implements IScrollTabHolder {
    protected IScrollTabHolder mScrollTabHolder;

    public void setScrollTabHolder(IScrollTabHolder scrollTabHolder) {
        mScrollTabHolder = scrollTabHolder;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        // nothing
    }

}