package com.tdn.domain.realmobject;

import com.tdn.domain.model.LaporanModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LaporanObject extends RealmObject {
    @PrimaryKey

    private String idLaporan;

    private String createdBy;

    private String judul;

    private String deskripsi;

    private String body;

    private String mediaLaporan;

    private String statusLaporan;

    private String createdAt;

    private String updatedAt;

    private String idUser;

    private String nama;

    private String alamat;

    private String tempatLahir;

    private String tanggalLahir;

    private String username;

    private String password;

    private String nik;

    private String level;

    private String statusUser;

    private String mediaUser;

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMediaLaporan() {
        return mediaLaporan;
    }

    public void setMediaLaporan(String mediaLaporan) {
        this.mediaLaporan = mediaLaporan;
    }

    public String getStatusLaporan() {
        return statusLaporan;
    }

    public void setStatusLaporan(String statusLaporan) {
        this.statusLaporan = statusLaporan;
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

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getMediaUser() {
        return mediaUser;
    }

    public void setMediaUser(String mediaUser) {
        this.mediaUser = mediaUser;
    }


    public Object ToModel() {
        LaporanModel l = new LaporanModel();
        l.setIdUser(idUser);
        l.setBody(body);
        l.setCreatedAt(createdAt);
        l.setCreatedBy(createdBy);
        l.setDeskripsi(deskripsi);
        l.setIdLaporan(idLaporan);
        l.setJudul(judul);
        l.setMediaUser(mediaUser);
        l.setMediaLaporan(mediaLaporan);
        l.setStatusUser(statusUser);
        l.setStatusLaporan(statusLaporan);
        l.setUpdatedAt(updatedAt);
        l.setNama(nama);
        l.setNik(nik);
        l.setUsername(username);
        l.setPassword(password);
        l.setTanggalLahir(tanggalLahir);
        l.setLevel(level);
        l.setAlamat(alamat);
        l.setTempatLahir(tempatLahir);
        l.setUpdatedAt(updatedAt);
        return l;
    }
}
