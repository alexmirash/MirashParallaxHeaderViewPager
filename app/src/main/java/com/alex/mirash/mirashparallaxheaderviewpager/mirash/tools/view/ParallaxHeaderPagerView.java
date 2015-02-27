package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
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

    private void init(Context context) {
        inflate(context, R.layout.parallax_header_pager_view, this);
        mAttrs = new Attributes();
        mAttrs.setMinHeaderHeight(getResources().getDimensionPixelSize(R.dimen.min_header_height));
        mAttrs.setHeaderHeight(getResources().getDimensionPixelSize(R.dimen.header_height));
        mAttrs.setMinHeaderTranslation(getResources().getDimensionPixelSize(R.dimen.header_height));

        mHeaderContainer = (ViewGroup) findViewById(R.id.header_container);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_strip);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    public void setHeaderView(View header) {
        if (mHeader != null) {
            mHeaderContainer.removeView(mHeader);
        }
        mHeader = header;
        mHeaderContainer.addView(header, 0);
    }

    public void setPagerAdapter(TabPagerAdapter adapter) {
        mPagerAdapter = adapter;
        mPagerAdapter.setTabHolderScrollingContent(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);
    }

    public void setActionBarHeight(int actionBarHeight) {
        mAttrs.setActionBarHeight(actionBarHeight);
        mAttrs.setMinHeaderTranslation(actionBarHeight - mAttrs.getMinHeaderHeight());
    }

    public void setHeaderParallaxHeight(float height) {
        mAttrs.setParallaxHeight(height);
    }

    public void setHeaderParallaxWidth(float width) {
        mAttrs.setParallaxWidth(width);
        ((MarginLayoutParams) mHeader.getLayoutParams()).rightMargin = (int) -width;
        mHeader.requestLayout();
    }

    private int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mAttrs.getHeaderHeight();
        }
        return firstVisiblePosition * c.getHeight() + headerHeight - top;
    }

    //IScrollTabHolder~
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int scrollY = getScrollY(view);
            ViewHelper.setTranslationY(mHeaderContainer, Math.max(-scrollY, mAttrs.getMinHeaderTranslation()));
            float ratio = clamp(ViewHelper.getTranslationY(mHeaderContainer) / mAttrs.getMinHeaderTranslation(), 0f, 1f);
            log("onVerticalScroll " + ratio);
            if (mCallbacks != null) {
                mCallbacks.onVerticalScroll(ratio);
            }
            mHeader.setTranslationY(mAttrs.getParallaxHeight() * ratio);
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
        if (mCallbacks != null) {
            mCallbacks.onPageScrolled(position, pageRatio, scrollX, totalRatio);
        }
        log("onPageScrolled " + position + ", " + pageRatio + ", " + scrollX);
        mHeader.setTranslationX(-mAttrs.getParallaxWidth() * totalRatio);
    }

    @Override
    public void onPageSelected(int position) {
        SparseArrayCompat<IScrollTabHolder> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
        IScrollTabHolder currentHolder = scrollTabHolders.valueAt(position);
        currentHolder.adjustScroll((int) (mHeaderContainer.getHeight() + ViewHelper.getTranslationY(mHeaderContainer)));
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
    }
}
