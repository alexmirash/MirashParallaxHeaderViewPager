package com.alexmirash.parallaxheaderviewpagersample.fragment;

import android.support.v4.app.FragmentManager;

import com.alexmirash.parallaxheaderviewpager.TabPagerAdapter;
import com.alexmirash.parallaxheaderviewpager.fragment.ScrollTabHolderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class SampleTabPagerAdapter extends TabPagerAdapter {
    public SampleTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    protected List<String> getTitles() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("Page " + (i + 1));
        }
        return list;
    }

    @Override
    protected ScrollTabHolderFragment getTabItem(int position) {
        switch (position) {
            case 0:
                return new SampleTabGridFragment();
            case 1:
                return new SampleTabScrollFragment();
            case 2:
                return new SampleTabScrollFragmentShort();
            case 3:
                return new SampleTabListFragment();
            default:
                return new SampleTabListFragment();
        }
    }

}