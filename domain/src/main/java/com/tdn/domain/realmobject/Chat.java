package com.tdn.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chat extends RealmObject {
    @PrimaryKey
    String idChat;
}
