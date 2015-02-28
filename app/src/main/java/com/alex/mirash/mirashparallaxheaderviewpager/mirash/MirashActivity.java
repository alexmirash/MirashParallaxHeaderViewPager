package com.alex.mirash.mirashparallaxheaderviewpager.mirash;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.alex.mirash.mirashparallaxheaderviewpager.R;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.sample.SampleTabPagerAdapter;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util.MirashUtils;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view.ParallaxHeaderPagerView;
import com.alex.mirash.mirashparallaxheaderviewpager.notboringactionbar.AlphaForegroundColorSpan;

public class MirashActivity extends ActionBarActivity {
    private int mActionBarHeight;
    private ParallaxHeaderPagerView mPagerView;

    private TypedValue mTypedValue = new TypedValue();
    private SpannableString mSpannableString;
    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirash);
        mPagerView = (ParallaxHeaderPagerView) findViewById(R.id.parallax_header_pager);
        mPagerView.setHeaderView(createHeader(), getResources().getDimensionPixelSize(R.dimen.header_height));
        setupHeaderParams();
        mPagerView.setPagerAdapter(new SampleTabPagerAdapter(getSupportFragmentManager()));
        mPagerView.setCallbacks(new ParallaxHeaderPagerView.ICallbacks() {
            @Override
            public void onVerticalScroll(float ratio) {
                setTitleAlpha(ratio);
            }

            @Override
            public void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        });

        mSpannableString = new SpannableString(getString(R.string.actionbar_title));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(0xffffffff);
//        ViewHelper.setAlpha(getActionBarIconView(), 0f);
        getSupportActionBar().setBackgroundDrawable(null);
    }

    private void setupHeaderParams() {
        int tabStripHeight = getResources().getDimensionPixelSize(R.dimen.max_header_height_offset);
        MirashUtils.log("abh = " + getActionBarHeight());
        mPagerView.setMinHeaderHeight(tabStripHeight + getActionBarHeight());
        mPagerView.setHeaderParallaxHeightFactor(0.55f);
        mPagerView.setHeaderParallaxWidth(500);
    }

    private View createHeader() {
        return new View(this) {
            {
                setBackground(getResources().getDrawable(R.drawable.pic1));
                setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getResources().getDimensionPixelSize(R.dimen.header_height)));
            }
        };
//        KenBurnsSupportView headerPicture = (KenBurnsSupportView) getLayoutInflater().inflate(R.layout.nice_header_view, null);
//        headerPicture.setResourceIds(R.drawable.pic0, R.drawable.pic1);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getResources().getDimensionPixelSize(R.dimen.header_height));
//        headerPicture.setLayoutParams(layoutParams);
//        return headerPicture;
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

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(mSpannableString);
    }
}
