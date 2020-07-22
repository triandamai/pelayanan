package com.tdn.laporan_desa.ui.chat;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.persistensi.MyUser;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.realmobject.ChatObject;

import java.util.List;

import io.realm.Realm;

public class ChatViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    private Realm realm;
    public LiveData<List<ChatObject>> userObjectLiveData;

    public ChatViewModel(Context context) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.isLoading.set(false);
        this.realm = Realm.getDefaultInstance();
        fetchfromApi();
        fetchfromLocal();
    }


    public void fetchfromLocal() {

        userObjectLiveData = new RealmLiveResult(realm.where(ChatObject.class).findAll());
    }

    public void fetchfromApi() {

        Repository.getInstance(context).getAllUser(MyUser.getInstance(context).getUser().getIdUser());

    }

    public LiveData<List<ChatObject>> getUserObjectLiveData() {
        if (userObjectLiveData == null) {
            userObjectLiveData = new MutableLiveData<>();
        }
        return userObjectLiveData;
    }
}
