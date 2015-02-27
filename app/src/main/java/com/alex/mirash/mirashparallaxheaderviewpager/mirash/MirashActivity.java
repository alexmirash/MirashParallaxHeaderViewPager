package com.alex.mirash.mirashparallaxheaderviewpager.mirash;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;

import com.alex.mirash.mirashparallaxheaderviewpager.R;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.sample.SampleTabPagerAdapter;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view.ParallaxHeaderPagerView;
import com.alex.mirash.mirashparallaxheaderviewpager.notboringactionbar.AlphaForegroundColorSpan;

import static com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util.MirashUtils.clamp;

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
        mPagerView.setActionBarHeight(getActionBarHeight());
        mPagerView.setPagerAdapter(new SampleTabPagerAdapter(getSupportFragmentManager()));
        mPagerView.setHeaderView(createHeader());
        mPagerView.setHeaderParallaxHeight(350);
        mPagerView.setHeaderParallaxWidth(500);
        mPagerView.setCallbacks(new ParallaxHeaderPagerView.ICallbacks() {
            @Override
            public void onVerticalScroll(float ratio) {
                setTitleAlpha(clamp(5f * ratio - 4f, 0f, 1f));
            }

            @Override
            public void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio) {
            }
        });

        mSpannableString = new SpannableString(getString(R.string.actionbar_title));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(0xffffffff);
//        ViewHelper.setAlpha(getActionBarIconView(), 0f);
        getSupportActionBar().setBackgroundDrawable(null);
    }

    private View createHeader() {
/*        KenBurnsSupportView headerPicture = new KenBurnsSupportView(this);
        headerPicture.setResourceIds(R.drawable.pic0, R.drawable.pic1);*/
        return new View(this) {
            {
                setBackground(getResources().getDrawable(R.drawable.pic1));
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

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(mSpannableString);
    }
}
