package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.sample;

import android.support.v4.app.FragmentManager;

import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.ScrollTabHolderFragment;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.TabPagerAdapter;

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
        for (int i = 0; i < 7; i++) {
            list.add("Page " + (i + 1));
        }
        return list;
    }

    @Override
    protected ScrollTabHolderFragment getTabItem(int position) {
        return new SampleTabListFragment();
    }

}