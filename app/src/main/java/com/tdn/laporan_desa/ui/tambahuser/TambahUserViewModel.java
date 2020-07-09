package com.tdn.laporan_desa.ui.tambahuser;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.data.repository.Repository;
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
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<String> nama = new ObservableField<>();
    public ObservableField<String> alamat = new ObservableField<>();
    public ObservableField<String> tempatlahir = new ObservableField<>();
    public ObservableField<String> tanggallahir = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> nik = new ObservableField<>();
    public ObservableField<String> level = new ObservableField<>();
    public ObservableField<String> status = new ObservableField<>();
    public ObservableField<String> media = new ObservableField<>();
    public ObservableField<String> created_at = new ObservableField<>();
    public ObservableField<String> updated_at = new ObservableField<>();

    public TambahUserViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = Repository.getService(context);
        this.isLoading.set(false);
        this.listener = actionListener;
    }

    public void simpan() {
        listener.onStart();
        if (nama.get().isEmpty()) {
            UserPostReq userPostReq = new UserPostReq();
            userPostReq.setNama(nama.get());
            userPostReq.setAlamat(alamat.get());
            userPostReq.setLevel(level.get());
            userPostReq.setNik(nik.get());
            userPostReq.setTanggalLahir(tanggallahir.get());
            userPostReq.setPassword(nik.get());
            userPostReq.setUsername(username.get());
            userPostReq.setTempatLahir(tempatlahir.get());
            userPostReq.setStatus("ada");
            userPostReq.setCreatedAt(String.valueOf(new Date().getTime()));
            userPostReq.setUpdatedAt(String.valueOf(new Date().getTime()));

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
