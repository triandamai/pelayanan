package com.tdn.laporan_desa.ui.datauser;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.realmobject.UserObject;

import java.util.List;

import io.realm.Realm;

public class DataUserViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private Realm realm;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public LiveData<List<UserObject>> userObjectLiveData;

    public DataUserViewModel(Context context) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.realm = Realm.getDefaultInstance();
        fetchfromApi();
        fetchfromLocal();
    }

    public void fetchfromLocal() {

        userObjectLiveData = new RealmLiveResult(realm.where(UserObject.class).findAll());
    }

    public void fetchfromApi() {

        Repository.getInstance(context).getAllLaporan();

    }

    public LiveData<List<UserObject>> getUserObjectLiveData() {
        if (userObjectLiveData == null) {
            userObjectLiveData = new MutableLiveData<>();
        }
        return userObjectLiveData;
    }
}
