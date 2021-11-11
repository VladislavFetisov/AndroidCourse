package com.example.lab4_2.sub_task_5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        View rootView = inflater.inflate(R.layout.fragment_2, container, false);
        rootView.findViewById(R.id.to_first2).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment2.this).navigate(R.id.action_fragment2_to_fragment1);
        });
        rootView.findViewById(R.id.to_third2).setOnClickListener(v -> {
            NavHostFragment.findNavController(Fragment2.this).navigate(R.id.action_fragment2_to_fragment3);
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.go_to_about) {
            NavHostFragment.findNavController(Fragment2.this).navigate(R.id.activityAbout);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}