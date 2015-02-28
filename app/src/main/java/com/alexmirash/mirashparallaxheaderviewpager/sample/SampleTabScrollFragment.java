package com.alexmirash.mirashparallaxheaderviewpager.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.alexmirash.mirashparallaxheaderviewpager.tools.fragment.TabHolderScrollFragment;

/**
 * @author Mirash
 */
public class SampleTabScrollFragment extends TabHolderScrollFragment<ScrollView> {

    @Override
    protected ScrollView createRootScrollView(LayoutInflater inflater) {
        return new ScrollView(getActivity());
    }

    @Override
    protected View createViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.addView(createView(Color.CYAN));
        ll.addView(createView(Color.YELLOW));
        ll.addView(createView(Color.BLUE));
        ll.addView(createView(Color.RED));
        ll.addView(createView(Color.GREEN));
        return ll;
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        mScrollView.scrollTo(0, getHeaderHeight() - scrollHeight);
    }

    private View createView(int color) {
        View v = new View(getActivity());
        v.setBackgroundColor(color);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000));
        return v;
    }
}
