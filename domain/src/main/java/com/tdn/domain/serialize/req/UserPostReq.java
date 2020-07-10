package com.tdn.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPostReq {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("tempat_lahir")
    @Expose
    private String tempatLahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("media_user")
    @Expose
    private String mediaUser;
    @SerializedName("status_user")
    @Expose
    private String statusUser;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
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

    public String getMediaUser() {
        return mediaUser;
    }

    public void setMediaUser(String mediaUser) {
        this.mediaUser = mediaUser;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
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
        return "UserPostReq{" +
                "idUser='" + idUser + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", tempatLahir='" + tempatLahir + '\'' +
                ", tanggalLahir='" + tanggalLahir + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nik='" + nik + '\'' +
                ", level='" + level + '\'' +
                ", mediaUser='" + mediaUser + '\'' +
                ", statusUser='" + statusUser + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
