package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view;

/**
 * @author Mirash
 */
public class Attributes {
    private int mActionBarHeight;
    private int mMinHeaderHeight;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private float mParallaxHeight;
    private float mParallaxWidth;

    public int getActionBarHeight() {
        return mActionBarHeight;
    }

    public void setActionBarHeight(int actionBarHeight) {
        mActionBarHeight = actionBarHeight;
    }

    public int getMinHeaderHeight() {
        return mMinHeaderHeight;
    }

    public void setMinHeaderHeight(int minHeaderHeight) {
        mMinHeaderHeight = minHeaderHeight;
    }

    public int getHeaderHeight() {
        return mHeaderHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        mHeaderHeight = headerHeight;
    }

    public int getMinHeaderTranslation() {
        return mMinHeaderTranslation;
    }

    public void setMinHeaderTranslation(int minHeaderTranslation) {
        mMinHeaderTranslation = minHeaderTranslation;
    }

    public void setParallaxHeight(float parallaxHeight) {
        mParallaxHeight = parallaxHeight;
    }

    public void setParallaxWidth(float parallaxWidth) {
        mParallaxWidth = parallaxWidth;
    }

    public float getParallaxHeight() {
        return mParallaxHeight;
    }

    public float getParallaxWidth() {
        return mParallaxWidth;
    }
}
