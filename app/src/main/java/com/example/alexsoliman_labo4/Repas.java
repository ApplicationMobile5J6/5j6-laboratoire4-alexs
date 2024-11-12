package com.example.alexsoliman_labo4;

public class Repas {
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

    //Constructeur par defaut
    public Repas(){}


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
}
