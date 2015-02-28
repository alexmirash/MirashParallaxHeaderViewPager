package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

public interface IScrollTabHolder {

    void adjustScroll(int scrollHeight);

    void onScroll(int scrollY, int pagePosition);

    int getHeaderHeight();

    int getMinHeaderHeight();
}
