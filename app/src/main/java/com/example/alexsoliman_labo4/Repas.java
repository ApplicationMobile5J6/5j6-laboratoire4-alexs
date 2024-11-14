package com.example.alexsoliman_labo4;

import android.os.Parcel;
import android.os.Parcelable;

public class Repas implements Parcelable {
    private int noRepas;
    private String nom;
    private String description;
    private String categorie;
    private double prix;

    // Constructeur avec param
    public Repas(int noRepas, String nom, String description, String categorie, double prix) {
        this.noRepas = noRepas;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }

    // Constructeur par defaut
    public Repas() {}

    // Getters et setters
    public int getNoRepas() { return noRepas; }
    public void setNoRepas(int noRepas) { this.noRepas = noRepas; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }



    // Parcleable
    protected Repas(Parcel in) {
        noRepas = in.readInt();
        nom = in.readString();
        description = in.readString();
        categorie = in.readString();
        prix = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noRepas);
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(categorie);
        dest.writeDouble(prix);
    }

    public static final Parcelable.Creator<Repas> CREATOR = new Parcelable.Creator<Repas>() {
        @Override
        public Repas createFromParcel(Parcel in) {
            return new Repas(in);
        }

        @Override
        public Repas[] newArray(int size) {
            return new Repas[size];
        }

    };

    @Override
    public String toString() {
        return nom;
    }
}
