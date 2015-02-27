package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.view;

/**
 * @author Mirash
 */
public class Attributes {
    private int mHeaderHeight;
    private int mMinHeaderHeight;
    private float mParallaxHeightFactor;
    private float mParallaxWidth;


    int getHeaderHeight() {
        return mHeaderHeight;
    }

    public Attributes setHeaderHeight(int headerHeight) {
        mHeaderHeight = headerHeight;
        return this;
    }

    int getMinHeaderHeight() {
        return mMinHeaderHeight;
    }

    public Attributes setMinHeaderHeight(int minHeaderTranslation) {
        mMinHeaderHeight = minHeaderTranslation;
        return this;
    }

    public Attributes setParallaxHeightFactor(float parallaxFactor) {
        mParallaxHeightFactor = parallaxFactor;
        return this;
    }

    public Attributes setParallaxWidth(float parallaxWidth) {
        mParallaxWidth = parallaxWidth;
        return this;
    }

    float getParallaxHeightFactor() {
        return mParallaxHeightFactor;
    }

    float getParallaxWidth() {
        return mParallaxWidth;
    }
}
