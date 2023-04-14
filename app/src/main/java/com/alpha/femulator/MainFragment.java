package com.alpha.femulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main, container, false);

        CircularDaysLeftView circular = view.findViewById(R.id.circular_days_left);
        circular.setProgress(0,30,28); // Set the number of days left
        float num_of_days=5f;
        circular.setStartAngle(-90f+9f*num_of_days);

        return view;
    }
}