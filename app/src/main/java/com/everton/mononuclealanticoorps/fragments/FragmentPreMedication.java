package com.everton.mononuclealanticoorps.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.everton.mononuclealanticoorps.R;

public class FragmentPreMedication extends Fragment {
    public FragmentPreMedication() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition, container, false);

        return view;
    }
}
