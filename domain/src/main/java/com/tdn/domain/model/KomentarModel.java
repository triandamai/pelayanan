package com.tdn.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.realmobject.KomentarObject;

public class KomentarModel extends BaseModel{
    @SerializedName("id_komentar")
    @Expose
    private String idKomentar;
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
    @SerializedName("id_komentar_parent")
    @Expose
    private String idKomentarParent;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    @Override
    public Object ToObject() {
        KomentarObject komentarModel = new KomentarObject();
        komentarModel.setBody(body);
        komentarModel.setCreatedAt(createdAt);
        komentarModel.setIdKomentar(idKomentar);
        komentarModel.setIdKomentarParent(idKomentarParent);
        komentarModel.setStatus(status);
        komentarModel.setUpdatedAt(updatedAt);
        return komentarModel;
    }
}
