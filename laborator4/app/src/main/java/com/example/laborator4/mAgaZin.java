package com.example.laborator4;


import java.io.Serializable;
// sau poți implementa Parcelable, dar Serializable e mai simplu pentru exemplu

public class mAgaZin implements Serializable {
    // 1) String
    private String nume;

    // 2) boolean
    private boolean deschis;

    // 3) int
    private int anInfiintare;

    // 4) enum - mulțime finită de valori
    public enum TipMagazin {
        ALIMENTAR,
        ELECTRONICE,
        HAINE
        // adaugă ce valori dorești
    }
    private TipMagazin tip;

    // 5) alt atribut numeric (ex: float rating)
    private float rating;

    // Constructor gol (dacă ai nevoie)
    public mAgaZin() { }

    // Constructor cu parametri
    public mAgaZin(String nume, boolean deschis, int anInfiintare, TipMagazin tip, float rating) {
        this.nume = nume;
        this.deschis = deschis;
        this.anInfiintare = anInfiintare;
        this.tip = tip;
        this.rating = rating;
    }

    // GETTER & SETTER pentru fiecare atribut
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean isDeschis() {
        return deschis;
    }

    public void setDeschis(boolean deschis) {
        this.deschis = deschis;
    }

    public int getAnInfiintare() {
        return anInfiintare;
    }

    public void setAnInfiintare(int anInfiintare) {
        this.anInfiintare = anInfiintare;
    }

    public TipMagazin getTip() {
        return tip;
    }

    public void setTip(TipMagazin tip) {
        this.tip = tip;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    // toString() pentru afișare rapidă (opțional)
    @Override
    public String toString() {
        return "mAgaZin{" +
                "nume='" + nume + '\'' +
                ", deschis=" + deschis +
                ", anInfiintare=" + anInfiintare +
                ", tip=" + tip +
                ", rating=" + rating +
                '}';
    }
}