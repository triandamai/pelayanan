package com.tdn.domain.serialize.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tdn.domain.model.ConversationModel;

import java.util.List;

public class ResponseGetConversation {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private List<ConversationModel> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<ConversationModel> getData() {
        return data;
    }

    public void setData(List<ConversationModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseGetConversation{" +
                "status=" + status +
                ", responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
