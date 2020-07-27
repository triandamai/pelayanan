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
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class DataUserViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private Realm realm;
    private ActionListener actionListener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public LiveData<List<UserObject>> userObjectLiveData;

    public DataUserViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.realm = Realm.getDefaultInstance();
        this.actionListener = actionListener;
        fetchfromApi();
        fetchfromLocal();
    }

    public void fetchfromLocal() {

        userObjectLiveData = new RealmLiveResult(realm.where(UserObject.class).findAll());
    }

    public void fetchfromApi() {

        Repository.getInstance(context).getAllUser();

    }

    public LiveData<List<UserObject>> getUserObjectLiveData() {
        if (userObjectLiveData == null) {
            userObjectLiveData = new MutableLiveData<>();
        }
        return userObjectLiveData;
    }

    public void hapus(String id) {
        actionListener.onStart();
        apiService.deleteUser(id)
                .enqueue(new Callback<ResponsePostPutDel>() {
                    @Override
                    public void onResponse(Call<ResponsePostPutDel> call, Response<ResponsePostPutDel> response) {
                        if (cek(response.code())) {
                            if (cek(response.body().getResponseCode())) {
                                actionListener.onSuccess(response.body().getResponseMessage());
                            } else {
                                actionListener.onError(response.body().getResponseMessage());
                            }
                        } else {
                            actionListener.onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePostPutDel> call, Throwable t) {
                        actionListener.onError(t.getMessage());
                    }
                });
    }
}
