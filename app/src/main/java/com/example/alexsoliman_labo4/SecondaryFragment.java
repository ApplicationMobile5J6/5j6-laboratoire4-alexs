package com.example.alexsoliman_labo4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SecondaryFragment extends Fragment {

    private RepasViewModel repasViewModel;
    private TextView tv_categorie, tv_price, tv_description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.fragment_secondary, container, false);

        tv_categorie = vue.findViewById(R.id.tv_categorie);
        tv_price = vue.findViewById(R.id.tv_price);
        tv_description = vue.findViewById(R.id.tv_description);

        return vue;
    }

    @Override
    public void onViewCreated(View vue, Bundle savedInstanceState) {
        super.onViewCreated(vue, savedInstanceState);


        repasViewModel = new ViewModelProvider(requireActivity()).get(RepasViewModel.class);


        repasViewModel.getSelectedRepas().observe(getViewLifecycleOwner(), new Observer<Repas>() {
            @Override
            public void onChanged(Repas repas) {
                if (repas != null) {
                    tv_categorie.setText(repas.getCategorie());
                    tv_price.setText(String.valueOf(repas.getPrix()));
                    tv_description.setText(repas.getDescription());
                }
            }
        });
    }
}
