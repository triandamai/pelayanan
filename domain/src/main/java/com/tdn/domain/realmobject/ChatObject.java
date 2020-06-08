package com.tdn.domain.realmobject;

import com.tdn.domain.model.ChatModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChatObject extends RealmObject {
    @PrimaryKey
    String idChat;


    public Object ToModel() {
        ChatModel chatModel = new ChatModel();

        return chatModel;
    }
}
