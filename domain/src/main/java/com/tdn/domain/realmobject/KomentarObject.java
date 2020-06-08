package com.tdn.domain.realmobject;

import com.tdn.domain.model.KomentarModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KomentarObject extends RealmObject {
    @PrimaryKey
    private String idKomentar;
    private String idLaporan;

    private String idKomentarParent;

    private String body;

    private String status;

    private String createdAt;

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


    public Object ToModel() {
        KomentarModel komentarModel = new KomentarModel();
        komentarModel.setBody(body);
        komentarModel.setCreatedAt(createdAt);
        komentarModel.setIdKomentar(idKomentar);
        komentarModel.setIdKomentarParent(idKomentarParent);
        komentarModel.setStatus(status);
        komentarModel.setUpdatedAt(updatedAt);
        return komentarModel;
    }
}
