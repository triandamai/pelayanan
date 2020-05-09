package com.tdn.data.local;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import io.realm.ObjectChangeSet;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmObjectChangeListener;

public class RealmLiveObject<T extends RealmModel> extends LiveData<T> {

    private RealmObjectChangeListener<T> listener = new RealmObjectChangeListener<T>() {
        @Override
        public void onChange(@NonNull T object, ObjectChangeSet objectChangeSet) {
            if (!objectChangeSet.isDeleted()) {
                setValue(object);
            } else {
                setValue(null);
            }
        }
    };


    @MainThread
    public RealmLiveObject(@NonNull T object) {
        //noinspection ConstantConditions
        if (object == null) {
            throw new IllegalArgumentException("Objek tidak Boleh null!");
        }
        if (!RealmObject.isManaged(object)) {
            throw new IllegalArgumentException("RealmLiveObject hanya support reamlmobject manage!");
        }
        if (!RealmObject.isValid(object)) {
            throw new IllegalArgumentException("Objek tidak valid!");
        }
        setValue(object);
    }


    @Override
    protected void onActive() {
        super.onActive();
        T object = getValue();
        if (object != null && RealmObject.isValid(object)) {
            RealmObject.addChangeListener(object, listener);
        }
    }


    @Override
    protected void onInactive() {
        super.onInactive();
        T object = getValue();
        if (object != null && RealmObject.isValid(object)) {
            RealmObject.removeChangeListener(object, listener);
        }
    }
}
