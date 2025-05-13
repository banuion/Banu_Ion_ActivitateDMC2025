package com.example.laborator7;

import android.os.Parcel;
import android.os.Parcelable;

public class Biberon implements Parcelable {
    // Atributele
    private String brand;
    private boolean esteAnticolici;
    private int capacitate;
    private float rating;

    public Biberon(String brand, boolean esteAnticolici, int capacitate, float rating) {
        this.brand = brand;
        this.esteAnticolici = esteAnticolici;
        this.capacitate = capacitate;
        this.rating = rating;
    }

    protected Biberon(Parcel in) {
        brand = in.readString();
        esteAnticolici = in.readByte() != 0;
        capacitate = in.readInt();
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
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeByte((byte) (esteAnticolici ? 1 : 0));
        dest.writeInt(capacitate);
        dest.writeFloat(rating);
    }

    @Override
    public String toString() {
        // Folosim toString pentru salvare în fișier – puteți formata după preferință
        return "Biberon{" +
                "brand='" + brand + '\'' +
                ", esteAnticolici=" + esteAnticolici +
                ", capacitate=" + capacitate +
                ", rating=" + rating +
                '}';
    }
}
