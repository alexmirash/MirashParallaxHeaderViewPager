package com.alexmirash.parallaxheaderviewpagersample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;

import com.alexmirash.parallaxheaderviewpager.view.ParallaxHeaderPagerView;
import com.alexmirash.parallaxheaderviewpagersample.fragment.SampleTabPagerAdapter;

public class SampleActivity2 extends ActionBarActivity {
    private ParallaxHeaderPagerView mPagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_2);
        mPagerView = (ParallaxHeaderPagerView) findViewById(R.id.parallax_header_pager);
        mPagerView.setHeaderView(createHeader(), getResources().getDimensionPixelSize(R.dimen.header_height));
        mPagerView.setHeaderParallaxWidth(getResources().getDimension(R.dimen.header_parallax_width));
        mPagerView.setPagerAdapter(new SampleTabPagerAdapter(getSupportFragmentManager()));
        mPagerView.setCallbacks(new ParallaxHeaderPagerView.ICallbacks() {
            @Override
            public void onVerticalScroll(float ratio) {
                mPagerView.getSlidingTabStrip().setAlpha(1 - ratio);
            }

            @Override
            public void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        });
    }

    private View createHeader() {
        View view = new View(this);
        view.setBackground(getResources().getDrawable(R.drawable.space));
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.header_height)));
        return view;
    }
}
