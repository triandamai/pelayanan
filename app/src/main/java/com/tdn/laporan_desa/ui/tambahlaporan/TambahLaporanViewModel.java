package com.tdn.laporan_desa.ui.tambahlaporan;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tdn.data.persistensi.MyUser;
import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.LaporanPostReq;
import com.tdn.domain.serialize.res.ResponsePostPutDel;
import com.tdn.laporan_desa.callback.ActionListener;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class TambahLaporanViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public MutableLiveData<String> foto = new MutableLiveData<>();
    public MutableLiveData<String> isi = new MutableLiveData<>();
    public MutableLiveData<String> judul = new MutableLiveData<>();

    public TambahLaporanViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.listener = actionListener;

        isi.setValue("");
        judul.setValue("");
        foto.setValue("");

    }

    public void simpan() {
        listener.onStart();
        if (!isi.getValue().isEmpty() || !judul.getValue().isEmpty() || !foto.getValue().isEmpty()) {
            LaporanPostReq laporanPostReq = new LaporanPostReq();
            laporanPostReq.setIdLaporan("fgf");
            laporanPostReq.setBody(isi.getValue());
            laporanPostReq.setCreatedBy(MyUser.getInstance(context).getUser().getIdUser());
            laporanPostReq.setJudul(judul.getValue());
            laporanPostReq.setMediaLaporan(foto.getValue());
            laporanPostReq.setStatusLaporan("ada");
            laporanPostReq.setCreatedAt(String.valueOf(new Date().getTime()));
            laporanPostReq.setUpdatedAt(String.valueOf(new Date().getTime()));
            Log.e("Send Laporan", laporanPostReq.toString());
            apiService.postLaporan(laporanPostReq).enqueue(new Callback<ResponsePostPutDel>() {
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
            listener.onError("Isi semua data");
        }


    }
}
