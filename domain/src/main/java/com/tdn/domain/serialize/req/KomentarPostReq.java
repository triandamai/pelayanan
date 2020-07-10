package com.tdn.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KomentarPostReq {

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

    @Override
    public String toString() {
        return "KomentarPostReq{" +
                "idKomentar='" + idKomentar + '\'' +
                ", idLaporan='" + idLaporan + '\'' +
                ", idKomentarParent='" + idKomentarParent + '\'' +
                ", bodyKomentar='" + bodyKomentar + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
