package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import java.util.List;

/**
 * @author Mirash
 */
public abstract class TabPagerAdapter extends FragmentPagerAdapter {
    private SparseArrayCompat<IScrollTabHolder> mScrollTabHolders;
    private List<String> mTitles;
    private IScrollTabHolder mListener;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        mTitles = getTitles();
        mScrollTabHolders = new SparseArrayCompat<>();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }


    @Override
    public Fragment getItem(int position) {
        ScrollTabHolderFragment fragment = getTabItem(position);
        mScrollTabHolders.put(position, fragment);
        if (mListener != null) {
            fragment.setScrollTabHolder(mListener);
        }
        return fragment;
    }

    public void setTabHolderScrollingContent(IScrollTabHolder listener) {
        mListener = listener;
    }

    public SparseArrayCompat<IScrollTabHolder> getScrollTabHolders() {
        return mScrollTabHolders;
    }

    protected abstract List<String> getTitles();

    protected abstract ScrollTabHolderFragment getTabItem(int position);

}