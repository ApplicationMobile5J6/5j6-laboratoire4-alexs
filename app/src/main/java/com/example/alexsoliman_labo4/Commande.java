package com.example.alexsoliman_labo4;

import android.os.Parcel;
import android.os.Parcelable;

public class Commande implements Parcelable {
    private int noCommande;
    private String nomClient;
    private String telClient;
    private int noRepas;
    private String nom;
    private double prix;

    // Constructeur avec paramètres
    public Commande(int noCommande, String nomClient, String telClient, int noRepas, String nom, double prix) {
        this.noCommande = noCommande;
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.noRepas = noRepas;
        this.nom = nom;
        this.prix = prix;
    }

    // Constructeur par défaut
    public Commande() {}

    // Getters et setters
    public int getNoCommande() { return noCommande; }
    public void setNoCommande(int noCommande) { this.noCommande = noCommande; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getTelClient() { return telClient; }
    public void setTelClient(String telClient) { this.telClient = telClient; }

    public int getNoRepas() { return noRepas; }
    public void setNoRepas(int noRepas) { this.noRepas = noRepas; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }


    protected Commande(Parcel in) {
        noCommande = in.readInt();
        nomClient = in.readString();
        telClient = in.readString();
        noRepas = in.readInt();
        nom = in.readString();
        prix = in.readDouble();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noCommande);
        dest.writeString(nomClient);
        dest.writeString(telClient);
        dest.writeInt(noRepas);
        dest.writeString(nom);
        dest.writeDouble(prix);
    }


    public static final Parcelable.Creator<Commande> CREATOR = new Parcelable.Creator<Commande>() {
        @Override
        public Commande createFromParcel(Parcel in) {
            return new Commande(in);
        }

        @Override
        public Commande[] newArray(int size) {
            return new Commande[size];
        }
    };
}
