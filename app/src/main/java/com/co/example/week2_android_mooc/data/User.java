package com.co.example.week2_android_mooc.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;
    private long birthDay;

    public User() {
    }

    public User(String nombre, String telefono, String email, String descripcion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(long birthDay) {
        this.birthDay = birthDay;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    protected User(Parcel in) {
        this.nombre = in.readString();
        this.telefono = in.readString();
        this.email = in.readString();
        this.descripcion = in.readString();
        this.birthDay = in.readLong();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.telefono);
        dest.writeString(this.email);
        dest.writeString(this.descripcion);
        dest.writeLong(this.birthDay);
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }


}
