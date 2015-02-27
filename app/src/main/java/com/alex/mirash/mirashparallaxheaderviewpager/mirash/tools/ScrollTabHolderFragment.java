package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.AbsListView;


public abstract class ScrollTabHolderFragment extends Fragment implements IScrollTabHolder {
    static final String ARG_POSITION = "position";

    protected IScrollTabHolder mScrollTabHolder;
    protected int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
    }

    public void setScrollTabHolder(IScrollTabHolder scrollTabHolder) {
        mScrollTabHolder = scrollTabHolder;
    }

    protected ScrollTabHolderFragment() {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        // nothing
    }

}