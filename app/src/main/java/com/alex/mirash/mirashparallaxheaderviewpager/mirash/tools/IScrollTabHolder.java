package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools;

import android.widget.AbsListView;

public interface IScrollTabHolder {

	void adjustScroll(int scrollHeight);

	void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);
}
