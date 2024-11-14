package com.example.alexsoliman_labo4;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RepasViewModel extends ViewModel {

    private final MutableLiveData<Repas> selectedRepas = new MutableLiveData<>();

    public void selectRepas(Repas repas) {
        selectedRepas.setValue(repas);
    }

    public LiveData<Repas> getSelectedRepas() {
        return selectedRepas;
    }
}
