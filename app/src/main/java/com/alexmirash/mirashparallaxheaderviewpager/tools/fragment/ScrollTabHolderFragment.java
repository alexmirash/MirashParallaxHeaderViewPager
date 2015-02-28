package com.alexmirash.mirashparallaxheaderviewpager.tools.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alexmirash.mirashparallaxheaderviewpager.tools.IScrollTabHolder;

import static com.alexmirash.mirashparallaxheaderviewpager.tools.util.MirashUtils.log;


public abstract class ScrollTabHolderFragment extends Fragment implements IScrollTabHolder {
    public static final String ARG_POSITION = "position";

    protected IScrollTabHolder mScrollTabHolder;
    protected int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
    }

    public void setScrollTabHolder(IScrollTabHolder scrollTabHolder) {
        log("setScrollTabHolder to fragment " + mPosition);
        mScrollTabHolder = scrollTabHolder;
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public int getHeaderHeight() {
        return mScrollTabHolder.getHeaderHeight();
    }

    @Override
    public int getMinHeaderHeight() {
        return mScrollTabHolder.getMinHeaderHeight();
    }

    @Override
    public void onScroll(int scrollY, int pagePosition) {
    }

}