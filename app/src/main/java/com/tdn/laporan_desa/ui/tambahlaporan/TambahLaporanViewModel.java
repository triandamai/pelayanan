package com.tdn.laporan_desa.ui.tambahlaporan;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.data.persistensi.MyUser;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.LaporanPostReq;
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class TambahLaporanViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<String> foto = new ObservableField<>();
    public ObservableField<String> isi = new ObservableField<>();
    public ObservableField<String> judul = new ObservableField<>();

    public TambahLaporanViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = Repository.getService(context);
        this.isLoading.set(false);
        this.listener = actionListener;

    }

    public void simpan() {
        listener.onStart();
        LaporanPostReq laporanPostReq = new LaporanPostReq();
        laporanPostReq.setBody(isi.get());
        laporanPostReq.setCreatedBy(MyUser.getInstance(context).getUser().getIdUser());
        laporanPostReq.setJudul(judul.get());
        laporanPostReq.setMediaLaporan(foto.get());
        laporanPostReq.setStatusLaporan("ada");

        apiService.postLaporan(laporanPostReq).enqueue(new Callback<ResponsePostPutDel>() {
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
    }
}
