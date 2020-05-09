package com.tdn.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Laporan extends RealmObject {
    @PrimaryKey
    String idLaporan;
}
