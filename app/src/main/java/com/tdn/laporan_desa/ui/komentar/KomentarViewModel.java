package com.tdn.laporan_desa.ui.komentar;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.local.RealmLiveResult;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.realmobject.KomentarObject;
import com.tdn.domain.serialize.req.KomentarPostReq;
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class KomentarViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public MutableLiveData<String> isi = new MutableLiveData<>();
    public MutableLiveData<String> idLap = new MutableLiveData<>();
    private LiveData<List<KomentarObject>> komentarObjectLiveData;
    private Realm realm;

    public KomentarViewModel(Context context, ActionListener actionListener, String id) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.isLoading.set(false);
        this.listener = actionListener;
        this.realm = Realm.getDefaultInstance();
        fetchfromApi(id);
        fetchfromLocal(id);
    }

    public void fetchfromLocal(String id) {

        komentarObjectLiveData = new RealmLiveResult(realm.where(KomentarObject.class).equalTo("idLaporan", id).findAll());
    }

    public void fetchfromApi(String id) {

        Repository.getInstance(context).getAllKomentar(id);

    }

    public LiveData<List<KomentarObject>> getKomentarObjectLiveData() {
        if (komentarObjectLiveData == null) {
            komentarObjectLiveData = new MutableLiveData<>();
        }
        return komentarObjectLiveData;
    }

    public void simpan() {
        if (isi.getValue() != null && !isi.getValue().isEmpty()) {
            listener.onStart();
            KomentarPostReq komentarPostReq = new KomentarPostReq();
            komentarPostReq.setBodyKomentar(isi.getValue());
            komentarPostReq.setIdKomentar(String.valueOf(new Date().getTime()));
            komentarPostReq.setIdKomentarParent("kosong");
            komentarPostReq.setIdLaporan(idLap.getValue());
            komentarPostReq.setStatus("ada");
            Log.e(TAG, komentarPostReq.toString());
            apiService.postKomentar(komentarPostReq).enqueue(new Callback<ResponsePostPutDel>() {
                @Override
                public void onResponse(Call<ResponsePostPutDel> call, Response<ResponsePostPutDel> response) {
                    Log.e(TAG, response.toString());
                    if (cek(response.code())) {
                        if (cek(response.body().getResponseCode())) {
                            listener.onSuccess(response.body().getResponseMessage());
                        } else {
                            listener.onError(response.body().getResponseMessage());
                        }
                    } else {
                        listener.onError(response.message().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponsePostPutDel> call, Throwable t) {
                    listener.onError(t.getMessage());
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            listener.onError("Masukkan Komentar");
        }
    }
}
