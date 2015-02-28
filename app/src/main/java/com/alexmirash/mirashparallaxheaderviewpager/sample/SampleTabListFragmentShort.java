package com.alexmirash.mirashparallaxheaderviewpager.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alexmirash.mirashparallaxheaderviewpager.R;
import com.alexmirash.mirashparallaxheaderviewpager.tools.fragment.TabHolderListFragment;

import java.util.ArrayList;
import java.util.Random;

public class SampleTabListFragmentShort extends TabHolderListFragment<ListView> {
    private ArrayList<String> mListItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListItems = new ArrayList<>();
        int count = 1 + new Random().nextInt(5);
        for (int i = 0; i < count; i++) {
            mListItems.add(i + ". item - currnet page: " + (mPosition + 1));
        }
    }

    @Override
    protected ListView createListView(LayoutInflater inflater) {
        return (ListView) inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
    }
}