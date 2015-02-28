package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.alex.mirash.mirashparallaxheaderviewpager.R;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.IScrollTabHolder;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.TabPagerAdapter;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view.tabstrip.PagerSlidingTabStrip;
import com.nineoldandroids.view.ViewHelper;

import static com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util.MirashUtils.clamp;
import static com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.util.MirashUtils.log;

/**
 * @author Mirash
 */
public class ParallaxHeaderPagerView extends FrameLayout implements IScrollTabHolder, ViewPager.OnPageChangeListener {
    protected ViewGroup mHeaderContainer;
    protected View mHeader;

    protected ViewPager mViewPager;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    protected TabPagerAdapter mPagerAdapter;

    protected Attributes mAttrs;

    protected ICallbacks mCallbacks;

    public ParallaxHeaderPagerView(Context context) {
        super(context);
        init(context);
    }

    public ParallaxHeaderPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ParallaxHeaderPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.parallax_header_pager_view, this);
        mAttrs = new Attributes();

        mHeaderContainer = (ViewGroup) findViewById(R.id.header_container);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_strip);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    public void setHeaderView(View header, int headerHeight) {
        if (mHeader != null) {
            mHeaderContainer.removeView(mHeader);
        }
        mHeader = header;
        mHeaderContainer.addView(header, 0);
        mAttrs.setHeaderHeight(headerHeight);
    }

    public void setPagerAdapter(TabPagerAdapter adapter) {
        mPagerAdapter = adapter;
        mPagerAdapter.setTabHolderScrollingContent(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);
    }

    public void setMinHeaderHeight(int minHeaderHeight) {
        mAttrs.setMinHeaderHeight(minHeaderHeight);
    }

    public void setHeaderParallaxHeightFactor(float parallaxFactor) {
        mAttrs.setParallaxHeightFactor(parallaxFactor);
    }

    public void setHeaderParallaxWidth(float parallaxWidth) {
        setHeaderParallaxWidth(parallaxWidth, true);
    }

    public void setHeaderParallaxWidth(float parallaxWidth, boolean isSetHeaderNegativeRightMargin) {
        mAttrs.setParallaxWidth(parallaxWidth);
        if (isSetHeaderNegativeRightMargin) {
            ((MarginLayoutParams) mHeader.getLayoutParams()).rightMargin = (int) -parallaxWidth;
        }
    }

    //TODO
    public void setTabUnderHeader(boolean isWhat) {
        log("h = " + getHeaderHeight());
        ((MarginLayoutParams) mHeader.getLayoutParams()).bottomMargin = 144 /*mPagerSlidingTabStrip.getHeight()*/;
        mHeaderContainer.requestLayout();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                log("h = " + getHeaderHeight());
            }
        }, 25);
    }

    @Override
    public int getHeaderHeight() {
        return mAttrs.getHeaderHeight();
//        return mHeaderContainer.getHeight();
    }

    @Override
    public int getMinHeaderHeight() {
        return mAttrs.getMinHeaderHeight();
    }

    //IScrollTabHolder~
    @Override
    public void onScroll(int scrollY, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int maxTranslationY = mAttrs.getMinHeaderHeight() - getHeaderHeight();
            ViewHelper.setTranslationY(mHeaderContainer, Math.max(-scrollY, maxTranslationY));
            float ratio = maxTranslationY != 0 ?
                    clamp(ViewHelper.getTranslationY(mHeaderContainer) / maxTranslationY, 0f, 1f) : 1f;
            if (mAttrs.getParallaxHeightFactor() > 0 && (scrollY < -maxTranslationY)) {
                mHeader.setTranslationY(scrollY * mAttrs.getParallaxHeightFactor());
            }
            if (mCallbacks != null) {
                mCallbacks.onVerticalScroll(ratio);
            }
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
    }
    //~

    //OnPageChangeListener~
    @Override
    public void onPageScrolled(int position, float pageRatio, int scrollX) {
        float totalRatio = mPagerAdapter.getCount() > 1 ? (position + pageRatio) / (mPagerAdapter.getCount() - 1) : 0f;
        if (mAttrs.getParallaxWidth() > 0) {
            mHeader.setTranslationX(-mAttrs.getParallaxWidth() * totalRatio);
        }
        if (mCallbacks != null) {
            mCallbacks.onPageScrolled(position, pageRatio, scrollX, totalRatio);
        }
    }

    @Override
    public void onPageSelected(int position) {
        IScrollTabHolder currentHolder = mPagerAdapter.getScrollTabHolder(position);
        currentHolder.adjustScroll((int) (getHeaderHeight() + ViewHelper.getTranslationY(mHeaderContainer)));
        if (mCallbacks != null) {
            mCallbacks.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
    //~

    public void setCallbacks(ICallbacks callbacks) {
        mCallbacks = callbacks;
    }

    public interface ICallbacks {
        void onVerticalScroll(float ratio);

        void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio);

        void onPageSelected(int position);
    }
}
