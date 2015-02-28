package com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alex.mirash.mirashparallaxheaderviewpager.R;
import com.alex.mirash.mirashparallaxheaderviewpager.mirash.tools.fragment.TabHolderListFragment;

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
    protected ListView createListView(LayoutInflater inflater) {
        return (ListView) inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));
    }
}