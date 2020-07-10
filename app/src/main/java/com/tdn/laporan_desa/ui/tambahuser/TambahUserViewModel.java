package com.tdn.laporan_desa.ui.tambahuser;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.UserPostReq;
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class TambahUserViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public MutableLiveData<String> nama = new MutableLiveData<>();
    public MutableLiveData<String> alamat = new MutableLiveData<>();
    public MutableLiveData<String> tempatlahir = new MutableLiveData<>();
    public MutableLiveData<String> tanggallahir = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> nik = new MutableLiveData<>();
    public MutableLiveData<String> level = new MutableLiveData<>();
    public MutableLiveData<String> media = new MutableLiveData<>();

    public TambahUserViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();

        this.listener = actionListener;

        nama.setValue("");
        alamat.setValue("");
        tempatlahir.setValue("");
        tanggallahir.setValue("");
        username.setValue("");
        password.setValue("");
        nik.setValue("");
        level.setValue("");
        media.setValue("");
    }

    public void simpan() {
        listener.onStart();

        if (!nama.getValue().isEmpty()) {
            UserPostReq userPostReq = new UserPostReq();
            userPostReq.setIdUser("tes");
            userPostReq.setNama(nama.getValue());
            userPostReq.setAlamat(alamat.getValue());
            userPostReq.setLevel(level.getValue());
            userPostReq.setNik(nik.getValue());
            userPostReq.setTanggalLahir(tanggallahir.getValue());
            userPostReq.setPassword(nik.getValue());
            userPostReq.setUsername(username.getValue());
            userPostReq.setTempatLahir(tempatlahir.getValue());
            userPostReq.setStatusUser("ada");
            userPostReq.setMediaUser(media.getValue());
            userPostReq.setCreatedAt(String.valueOf(new Date().getTime()));
            userPostReq.setUpdatedAt(String.valueOf(new Date().getTime()));
            Log.e("Send User", userPostReq.toString());
            apiService.postUser(userPostReq).enqueue(new Callback<ResponsePostPutDel>() {
                @Override
                public void onResponse(Call<ResponsePostPutDel> call, Response<ResponsePostPutDel> response) {

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
                }
            });
        } else {

            listener.onError("Isi semua data");
        }


    }
}
