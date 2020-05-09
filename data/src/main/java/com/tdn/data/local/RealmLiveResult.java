package com.tdn.data.local;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmLiveResult<T extends RealmModel> extends LiveData<RealmResults<T>> {
    private final RealmResults<T> results;


    private OrderedRealmCollectionChangeListener<RealmResults<T>> listener = new OrderedRealmCollectionChangeListener<RealmResults<T>>() {
        @Override
        public void onChange(@NonNull RealmResults<T> results, @Nullable OrderedCollectionChangeSet changeSet) {
           setValue(results);
        }
    };

    @MainThread
    public RealmLiveResult(@NonNull RealmResults<T> results) {
        //noinspection ConstantConditions
        if (results == null) {
            throw new IllegalArgumentException("Result tidak boleh null!");
        }
        if (!results.isValid()) {
            throw new IllegalArgumentException("Ream Result Tidak alid.");
        }

        if (results.isLoaded()) {

            setValue(results);
        }
        this.results = results;
    }


    @Override
    protected void onActive() {
        super.onActive();
        if (results.isValid()) {
            results.addChangeListener(listener);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (results.isValid()) {
            results.removeChangeListener(listener);
        }
    }
}
