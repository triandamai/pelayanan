package com.tdn.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Komentar extends RealmObject {
    @PrimaryKey
    String idKomentar;
}
