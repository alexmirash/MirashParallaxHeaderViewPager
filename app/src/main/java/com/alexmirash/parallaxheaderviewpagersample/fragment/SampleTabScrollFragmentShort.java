package com.alexmirash.parallaxheaderviewpagersample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderScrollFragment;

/**
 * @author Mirash
 */
public class SampleTabScrollFragmentShort extends TabHolderScrollFragment<ScrollView> {
    @Override
    protected ScrollView createRootScrollView(LayoutInflater inflater) {
        return new ScrollView(getActivity());
    }

    @Override
    protected View createViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setBackgroundColor(Color.argb(100, 100, 100, 100));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(createView(Color.CYAN));
        return linearLayout;
    }

    private View createView(int color) {
        View v = new View(getActivity());
        v.setBackgroundColor(color);
        LinearLayout.MarginLayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        lp.topMargin = 100;
        lp.leftMargin = 100;
        lp.rightMargin = 100;
        v.setLayoutParams(lp);
        return v;
    }
}
