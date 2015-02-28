package com.alexmirash.mirashparallaxheaderviewpager.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.alexmirash.mirashparallaxheaderviewpager.R;
import com.alexmirash.mirashparallaxheaderviewpager.tools.view.ParallaxHeaderPagerView;

import static com.alexmirash.mirashparallaxheaderviewpager.tools.util.MirashUtils.log;

public class SampleActivity extends ActionBarActivity {
    private int mActionBarHeight;
    private ParallaxHeaderPagerView mPagerView;

    private TypedValue mTypedValue = new TypedValue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mPagerView = (ParallaxHeaderPagerView) findViewById(R.id.parallax_header_pager);
        mPagerView.setHeaderView(createHeader(), getResources().getDimensionPixelSize(R.dimen.header_height));
        setupHeaderParams();
        mPagerView.setPagerAdapter(new SampleTabPagerAdapter(getSupportFragmentManager()));
        mPagerView.setCallbacks(new ParallaxHeaderPagerView.ICallbacks() {
            @Override
            public void onVerticalScroll(float ratio) {
            }

            @Override
            public void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        });
        getSupportActionBar().setBackgroundDrawable(null);
        getSupportActionBar().setTitle(getString(R.string.actionbar_title));
    }

    private void setupHeaderParams() {
        int tabStripHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        log("abh = " + getActionBarHeight());
        mPagerView.setMinHeaderHeight(tabStripHeight + getActionBarHeight());
        mPagerView.setHeaderParallaxWidth(getResources().getDimension(R.dimen.header_parallax_width));
        //        mPagerView.setHeaderParallaxHeightFactor(0.55f);
    }

    private View createHeader() {
        return new View(this) {
            {
                setBackground(getResources().getDrawable(R.drawable.pic1));
                setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getResources().getDimensionPixelSize(R.dimen.header_height)));
            }
        };
    }

    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        } else {
            getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
        }
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());
        return mActionBarHeight;
    }
}
