package com.tdn.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.realmobject.ChatObject;

public class ChatModel extends BaseModel {

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
    @SerializedName("id_chat")
    @Expose
    private String idChat;
    @SerializedName("status")
    @Expose
    private String status;
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

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
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
                ", idChat='" + idChat + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public Object ToObject() {
        ChatObject o = new ChatObject();
        o.setCreatedAt(createdAt);
        o.setIdChat(idChat);
        o.setReceiverAlamat(receiverAlamat);
        o.setReceiverId(receiverId);
        o.setReceiverLevel(receiverLevel);
        o.setReceiverMedia(receiverMedia);
        o.setReceiverNama(receiverNama);
        o.setReceiverUsername(receiverUsername);
        o.setSenderAlamat(senderAlamat);
        o.setSenderId(senderId);
        o.setSenderLevel(senderLevel);
        o.setSenderMedia(senderMedia);
        o.setSenderNama(senderNama);
        o.setSenderUsername(senderUsername);
        o.setUpdatedAt(updatedAt);
        o.setStatus(status);
        return o;
    }
}
