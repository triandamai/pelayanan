package com.tdn.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.realmobject.KomentarObject;

public class KomentarModel extends BaseModel {
    @SerializedName("id_komentar")
    @Expose
    private String idKomentar;
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
    @SerializedName("id_komentar_parent")
    @Expose
    private String idKomentarParent;
    @SerializedName("body_komentar")
    @Expose
    private String bodyKomentar;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("media_laporan")
    @Expose
    private String mediaLaporan;

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("media_user")
    @Expose
    private String mediaUser;

    public String getIdKomentar() {
        return idKomentar;
    }

    public void setIdKomentar(String idKomentar) {
        this.idKomentar = idKomentar;
    }

    public String getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(String idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getIdKomentarParent() {
        return idKomentarParent;
    }

    public void setIdKomentarParent(String idKomentarParent) {
        this.idKomentarParent = idKomentarParent;
    }

    public String getBodyKomentar() {
        return bodyKomentar;
    }

    public void setBodyKomentar(String bodyKomentar) {
        this.bodyKomentar = bodyKomentar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "KomentarModel{" +
                "idKomentar='" + idKomentar + '\'' +
                ", idLaporan='" + idLaporan + '\'' +
                ", idKomentarParent='" + idKomentarParent + '\'' +
                ", bodyKomentar='" + bodyKomentar + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", judul='" + judul + '\'' +
                ", body='" + body + '\'' +
                ", mediaLaporan='" + mediaLaporan + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", nik='" + nik + '\'' +
                ", level='" + level + '\'' +
                ", mediaUser='" + mediaUser + '\'' +
                '}';
    }

    @Override
    public Object ToObject() {
        KomentarObject komentarModel = new KomentarObject();
        komentarModel.setIdKomentar(idKomentar);
        komentarModel.setIdLaporan(idLaporan);
        komentarModel.setIdKomentarParent(idKomentarParent);
        komentarModel.setBodyKomentar(bodyKomentar);
        komentarModel.setStatus(status);
        komentarModel.setCreatedAt(createdAt);
        komentarModel.setUpdatedAt(updatedAt);
        komentarModel.setCreatedBy(createdBy);
        komentarModel.setJudul(judul);
        komentarModel.setBody(body);
        komentarModel.setMediaLaporan(mediaLaporan);
        komentarModel.setNama(nama);
        komentarModel.setAlamat(alamat);
        komentarModel.setNik(nik);
        komentarModel.setLevel(level);
        komentarModel.setMediaUser(mediaUser);
        return komentarModel;
    }
}
