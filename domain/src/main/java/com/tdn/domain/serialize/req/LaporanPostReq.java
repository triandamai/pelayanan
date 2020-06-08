package com.tdn.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanPostReq {
    @SerializedName("id_laporan")
    @Expose
    private String idLaporan;
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
    @SerializedName("status_laporan")
    @Expose
    private String statusLaporan;

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


}
