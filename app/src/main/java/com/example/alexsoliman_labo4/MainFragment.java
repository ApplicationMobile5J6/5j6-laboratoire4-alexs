package com.example.alexsoliman_labo4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private static final String ARG_REPAS_LIST = "repasList";
    private ArrayList<Repas> repasList;

    public static MainFragment newInstance(ArrayList<Repas> repasList) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_REPAS_LIST, repasList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            repasList = getArguments().getParcelableArrayList(ARG_REPAS_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View vue, Bundle savedInstanceState) {
        super.onViewCreated(vue, savedInstanceState);


        Spinner spn_menu = vue.findViewById(R.id.spinnerMenu);


        if (repasList != null) {
            ArrayAdapter<Repas> adapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, repasList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_menu.setAdapter(adapter);
        }
    }
}
