package com.example.alexsoliman_labo4;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private static final String ARG_REPAS_LIST = "repasList";
    private ArrayList<Repas> repasList;
    private RepasViewModel repasViewModel;

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
        // Initialize the ViewModel
        repasViewModel = new ViewModelProvider(requireActivity()).get(RepasViewModel.class);
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

            // Set an item selected listener to update the ViewModel
            spn_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Repas selectedRepas = (Repas) parent.getItemAtPosition(position);
                    repasViewModel.selectRepas(selectedRepas); // Update the ViewModel with the selected item
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });
        }
    }
}
