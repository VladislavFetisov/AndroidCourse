package com.example.lab4_2.sub_task_5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_2.R;


public class Fragment2 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        rootView.findViewById(R.id.bnToFirst).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment2.this).navigate(R.id.action_fragment2_to_fragment1);
        });
        rootView.findViewById(R.id.bnToThird).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment2.this).navigate(R.id.action_fragment2_to_fragment3);
        });
        return rootView;
    }


}