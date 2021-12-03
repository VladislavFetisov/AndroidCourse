package com.example.lab4_2.sub_task_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab4_2.R;


public class Fragment3 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        rootView.findViewById(R.id.bnToSecond).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment3.this).navigate(R.id.action_fragment3_to_fragment2);
        });
        rootView.findViewById(R.id.bnToFirst).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment3.this).navigate(R.id.action_fragment3_to_fragment1);
        });
        return rootView;
    }
}