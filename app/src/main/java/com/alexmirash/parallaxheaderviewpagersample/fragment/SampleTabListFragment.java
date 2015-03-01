package com.alexmirash.parallaxheaderviewpagersample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alexmirash.parallaxheaderviewpagersample.R;
import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderListFragment;

import java.util.ArrayList;
import java.util.Random;

public class SampleTabListFragment extends TabHolderListFragment<ListView> {
    private ArrayList<String> mListItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        int count = 50 + new Random().nextInt(50);
        for (int i = 0; i < count; i++) {
            mListItems.add(i + ". item - currnet page: " + (mPosition + 1));
        }
    }

    @Override
    protected ListView createScrollingRootView(LayoutInflater inflater) {
        return (ListView) inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootScrollingView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
    }
}