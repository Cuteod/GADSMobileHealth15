package com.example.diagnoseme.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diagnoseme.Adapters.HorizontalListAdapter;
import com.example.diagnoseme.R;

public class Home extends Fragment {

    View mView;
    RecyclerView mRecyclerView;
    public Home() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = mView.findViewById(R.id.recyclerViewHome);
        mRecyclerView.setAdapter(new HorizontalListAdapter(mView.getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext(), RecyclerView.HORIZONTAL,false));

        return mView;

    }
}