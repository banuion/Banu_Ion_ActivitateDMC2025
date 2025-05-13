package com.example.laborator6;

import android.os.Parcel;
import android.os.Parcelable;

public class Biberon implements Parcelable {
    // Atribut de tip String: brand
    private String brand;
    // Atribut de tip boolean: dacă este anticolici
    private boolean esteAnticolici;
    // Atribut de tip int: capacitate în mililitri
    private int capacitate;
    // Atribut de tip enum: tipul materialului
    public enum TipMaterial { PLASTIC, STICLA, SILICONE }
    private TipMaterial material;
    // Atribut de tip float: rating
    private float rating;

    // Constructor
    public Biberon(String brand, boolean esteAnticolici, int capacitate, TipMaterial material, float rating) {
        this.brand = brand;
        this.esteAnticolici = esteAnticolici;
        this.capacitate = capacitate;
        this.material = material;
        this.rating = rating;
    }

    // Constructor pentru Parcelable
    protected Biberon(Parcel in) {
        brand = in.readString();
        esteAnticolici = in.readByte() != 0;
        capacitate = in.readInt();
        int ordinal = in.readInt();
        material = TipMaterial.values()[ordinal];
        rating = in.readFloat();
    }

    public static final Creator<Biberon> CREATOR = new Creator<Biberon>() {
        @Override
        public Biberon createFromParcel(Parcel in) {
            return new Biberon(in);
        }

        @Override
        public Biberon[] newArray(int size) {
            return new Biberon[size];
        }
    };

    // Getteri și setteri
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public boolean isEsteAnticolici() { return esteAnticolici; }
    public void setEsteAnticolici(boolean esteAnticolici) { this.esteAnticolici = esteAnticolici; }
    public int getCapacitate() { return capacitate; }
    public void setCapacitate(int capacitate) { this.capacitate = capacitate; }
    public TipMaterial getMaterial() { return material; }
    public void setMaterial(TipMaterial material) { this.material = material; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeByte((byte) (esteAnticolici ? 1 : 0));
        dest.writeInt(capacitate);
        dest.writeInt(material.ordinal());
        dest.writeFloat(rating);
    }

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