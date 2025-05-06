package com.example.laborator4;

import java.io.Serializable;

public class Biberon implements Serializable {
    // Atribut de tip String: brandul biberonului
    private String brand;
    // Atribut de tip boolean: indică dacă biberonul este anticolici
    private boolean esteAnticolici;
    // Atribut de tip int: capacitatea în mililitri
    private int capacitate;
    // Atribut de tip enum: tipul materialului
    public enum TipMaterial {
        PLASTIC,
        STICLA,
        SILICONE
    }
    private TipMaterial material;
    // Atribut de tip float: rating-ul biberonului
    private float rating;

    // Constructor
    public Biberon(String brand, boolean esteAnticolici, int capacitate, TipMaterial material, float rating) {
        this.brand = brand;
        this.esteAnticolici = esteAnticolici;
        this.capacitate = capacitate;
        this.material = material;
        this.rating = rating;
    }

    // Getteri și setteri
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public boolean isEsteAnticolici() {
        return esteAnticolici;
    }
    public void setEsteAnticolici(boolean esteAnticolici) {
        this.esteAnticolici = esteAnticolici;
    }
    public int getCapacitate() {
        return capacitate;
    }
    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }
    public TipMaterial getMaterial() {
        return material;
    }
    public void setMaterial(TipMaterial material) {
        this.material = material;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    // toString() pentru afișare
    @Override
    public String toString() {
        return "Biberon{" +
                "brand='" + brand + '\'' +
                ", esteAnticolici=" + esteAnticolici +
                ", capacitate=" + capacitate +
                ", material=" + material +
                ", rating=" + rating +
                '}';
    }
}