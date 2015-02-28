package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.fragment.ScrollTabHolderFragment;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util.MirashUtils;

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
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            MirashUtils.log("bundle is  null -> create and put pos = " + position);
            bundle = new Bundle();
            fragment.setArguments(bundle);
        }
        bundle.putInt(ScrollTabHolderFragment.ARG_POSITION, position);

        mScrollTabHolders.put(position, fragment);
        if (mListener != null) {
            fragment.setScrollTabHolder(mListener);
        }
        return fragment;
    }

    public void setTabHolderScrollingContent(IScrollTabHolder listener) {
        mListener = listener;
    }

    public IScrollTabHolder getScrollTabHolder(int position) {
        return mScrollTabHolders.valueAt(position);
    }

    protected abstract List<String> getTitles();

    protected abstract ScrollTabHolderFragment getTabItem(int position);

}