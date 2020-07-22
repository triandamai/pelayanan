package com.tdn.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.realmobject.ConversationObject;

public class ConversationModel extends BaseModel {

    @SerializedName("sender_id")
    @Expose
    private String senderId;
    @SerializedName("sender_nama")
    @Expose
    private String senderNama;
    @SerializedName("sender_media")
    @Expose
    private String senderMedia;
    @SerializedName("sender_alamat")
    @Expose
    private String senderAlamat;
    @SerializedName("sender_username")
    @Expose
    private String senderUsername;
    @SerializedName("sender_level")
    @Expose
    private String senderLevel;
    @SerializedName("receiver_id")
    @Expose
    private String receiverId;
    @SerializedName("receiver_nama")
    @Expose
    private String receiverNama;
    @SerializedName("receiver_media")
    @Expose
    private String receiverMedia;
    @SerializedName("receiver_alamat")
    @Expose
    private String receiverAlamat;
    @SerializedName("receiver_username")
    @Expose
    private String receiverUsername;
    @SerializedName("receiver_level")
    @Expose
    private String receiverLevel;
    @SerializedName("id_detail_chat")
    @Expose
    private String idDetailChat;
    @SerializedName("id_chat")
    @Expose
    private String idChat;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderNama() {
        return senderNama;
    }

    public void setSenderNama(String senderNama) {
        this.senderNama = senderNama;
    }

    public String getSenderMedia() {
        return senderMedia;
    }

    public void setSenderMedia(String senderMedia) {
        this.senderMedia = senderMedia;
    }

    public String getSenderAlamat() {
        return senderAlamat;
    }

    public void setSenderAlamat(String senderAlamat) {
        this.senderAlamat = senderAlamat;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderLevel() {
        return senderLevel;
    }

    public void setSenderLevel(String senderLevel) {
        this.senderLevel = senderLevel;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverNama() {
        return receiverNama;
    }

    public void setReceiverNama(String receiverNama) {
        this.receiverNama = receiverNama;
    }

    public String getReceiverMedia() {
        return receiverMedia;
    }

    public void setReceiverMedia(String receiverMedia) {
        this.receiverMedia = receiverMedia;
    }

    public String getReceiverAlamat() {
        return receiverAlamat;
    }

    public void setReceiverAlamat(String receiverAlamat) {
        this.receiverAlamat = receiverAlamat;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getReceiverLevel() {
        return receiverLevel;
    }

    public void setReceiverLevel(String receiverLevel) {
        this.receiverLevel = receiverLevel;
    }

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
        return "ConversationModel{" +
                "senderId='" + senderId + '\'' +
                ", senderNama='" + senderNama + '\'' +
                ", senderMedia='" + senderMedia + '\'' +
                ", senderAlamat='" + senderAlamat + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", senderLevel='" + senderLevel + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", receiverNama='" + receiverNama + '\'' +
                ", receiverMedia='" + receiverMedia + '\'' +
                ", receiverAlamat='" + receiverAlamat + '\'' +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", receiverLevel='" + receiverLevel + '\'' +
                ", idDetailChat='" + idDetailChat + '\'' +
                ", idChat='" + idChat + '\'' +
                ", body='" + body + '\'' +
                ", media='" + media + '\'' +
                ", type='" + type + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public Object ToObject() {
        ConversationObject o = new ConversationObject();
        o.setBody(body);
        o.setCreatedAt(createdAt);
        o.setIdChat(idChat);
        o.setIdDetailChat(idDetailChat);
        o.setMedia(media);
        o.setReceiverAlamat(receiverAlamat);
        o.setReceiverId(receiverId);
        o.setReceiverLevel(receiverLevel);
        o.setReceiverMedia(receiverMedia);
        o.setReceiverNama(receiverNama);
        o.setReceiverUsername(senderUsername);
        o.setSenderAlamat(senderAlamat);
        o.setSenderId(senderId);
        o.setSenderLevel(senderLevel);
        o.setSenderMedia(senderMedia);
        o.setSenderNama(senderNama);
        o.setSenderUsername(senderUsername);
        o.setType(type);
        o.setUpdatedAt(updatedAt);
        return o;
    }
}