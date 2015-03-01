package com.alexmirash.parallaxheaderviewpagersample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderGridFragment;
import com.alexmirash.parallaxheaderviewpager.view.headergridview.HeaderGridView;
import com.alexmirash.parallaxheaderviewpagersample.R;
import com.alexmirash.parallaxheaderviewpagersample.adapter.GridSampleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.alexmirash.parallaxheaderviewpagersample.adapter.GridSampleAdapter.Item;

public class SampleTabGridFragment extends TabHolderGridFragment<HeaderGridView> {
    private List<Item> mListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        int count = 50 + new Random().nextInt(50);
        for (int i = 0; i < count; i++) {
            mListItems.add(new Item(i > 2 ? R.drawable.ic_launcher : R.drawable.droid));
        }
    }

    @Override
    protected HeaderGridView createScrollingRootView(LayoutInflater inflater) {
        HeaderGridView gridView = new HeaderGridView(getActivity());
        gridView.setNumColumns(3);
        return gridView;
    }

    @Override
    protected void onCreateViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        mRootScrollingView.setAdapter(new GridSampleAdapter(getActivity(), mListItems));
    }
}