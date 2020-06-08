package com.tdn.laporan_desa.auth;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.data.persistensi.MyUser;
import com.tdn.data.repository.Repository;
import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.LoginPostReq;
import com.tdn.domain.serialize.res.ResponsePostLogin;
import com.tdn.laporan_desa.callback.ActionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tdn.data.service.ApiHandler.cek;

public class LoginViewModel extends ViewModel {
    public String TAG = LoginViewModel.this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();

    public LoginViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = Repository.getService(context);
        this.isLoading.set(false);
        this.listener = actionListener;
    }

    public void login(View v) {
        listener.onStart();
        isLoading.set(true);
        LoginPostReq loginPostReq = new LoginPostReq();
        loginPostReq.setUsername(username.get());
        loginPostReq.setPassword(password.get());
        apiService.login(loginPostReq).enqueue(new Callback<ResponsePostLogin>() {
            @Override
            public void onResponse(Call<ResponsePostLogin> call, Response<ResponsePostLogin> response) {
                isLoading.set(false);
                Log.e(TAG, response.toString());
                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode())) {
                        MyUser.getInstance(context).setUser(response.body().getData());
                        listener.onSuccess(response.body().getResponseMessage());
                    } else {
                        listener.onError(response.body().getResponseMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePostLogin> call, Throwable t) {
                isLoading.set(false);
                listener.onError(t.getMessage());
            }
        });

    }
}
