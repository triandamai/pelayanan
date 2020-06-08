package com.tdn.laporan_desa.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.realmobject.LaporanObject;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.List;

import io.realm.Realm;

public class HomePageViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    private LiveData<List<LaporanObject>> laporanObjectLiveData;
    private Realm realm;

    public HomePageViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = Repository.getService(context);
        this.listener = actionListener;
        this.realm = Realm.getDefaultInstance();
        fetchfromApi();
        fetchfromLocal();
    }

    public void fetchfromLocal() {

        laporanObjectLiveData = new RealmLiveResult(realm.where(LaporanObject.class).findAll());
    }

    public void fetchfromApi() {

        Repository.getInstance(context).getAllLaporan();

    }

    public LiveData<List<LaporanObject>> getLaporanObjectLiveData() {
        if (laporanObjectLiveData == null) {
            laporanObjectLiveData = new MutableLiveData<>();
        }
        return laporanObjectLiveData;
    }
    // TODO: Implement the ViewModel
}
