package com.tdn.domain.realmobject;

import com.tdn.domain.model.UserModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserObject extends RealmObject {
    @PrimaryKey
    private String idUser;

    private String nama;

    private String alamat;

    private String tempatLahir;

    private String tanggalLahir;

    private String username;

    private String password;

    private String nik;

    private String level;

    private String status;

    private String media;

    private String createdAt;

    private String updatedAt;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "idUser='" + idUser + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", tempatLahir='" + tempatLahir + '\'' +
                ", tanggalLahir='" + tanggalLahir + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nik='" + nik + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                ", media='" + media + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


    public Object ToModel() {
        UserModel u = new UserModel();
        u.setAlamat(alamat);
        u.setCreatedAt(createdAt);
        u.setIdUser(idUser);
        u.setLevel(level);
        u.setMedia(media);
        u.setNama(nama);
        u.setNik(nik);
        u.setPassword(password);
        u.setUsername(username);
        return u;
    }
}
