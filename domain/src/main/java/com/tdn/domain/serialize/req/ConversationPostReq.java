package com.tdn.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConversationPostReq {
    @SerializedName("id_detail_chat")
    @Expose
    private String idDetailChat;
    @SerializedName("id_chat")
    @Expose
    private String idChat;
    @SerializedName("from_sender")
    @Expose
    private String fromSender;
    @SerializedName("to_receiver")
    @Expose
    private String toReceiver;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status_detail")
    @Expose
    private String statusDetail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getIdDetailChat() {
        return idDetailChat;
    }

    public void setIdDetailChat(String idDetailChat) {
        this.idDetailChat = idDetailChat;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getFromSender() {
        return fromSender;
    }

    public void setFromSender(String fromSender) {
        this.fromSender = fromSender;
    }

    public String getToReceiver() {
        return toReceiver;
    }

    public void setToReceiver(String toReceiver) {
        this.toReceiver = toReceiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
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
        return "ConversationPostReq{" +
                "idDetailChat='" + idDetailChat + '\'' +
                ", idChat='" + idChat + '\'' +
                ", fromSender='" + fromSender + '\'' +
                ", toReceiver='" + toReceiver + '\'' +
                ", body='" + body + '\'' +
                ", media='" + media + '\'' +
                ", type='" + type + '\'' +
                ", statusDetail='" + statusDetail + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
