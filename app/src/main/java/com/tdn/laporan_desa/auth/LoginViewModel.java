package com.tdn.laporan_desa.auth;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.data.persistensi.MyUser;
import com.tdn.data.service.ApiService;
import com.tdn.domain.serialize.req.LoginPostReq;
import com.tdn.domain.serialize.res.ResponsePostLogin;
import com.tdn.domain.serialize.res.ResponseUpdatePassword;
import com.tdn.laporan_desa.callback.ActionChangePassListener;
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
    private ActionChangePassListener passListener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();

    public LoginViewModel(Context context, ActionListener actionListener, ActionChangePassListener actionChangePassListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.isLoading.set(false);
        this.listener = actionListener;
        this.passListener = actionChangePassListener;
    }

    public void lupa(String username) {
        passListener.onStart();
        apiService.updatePass(username).enqueue(new Callback<ResponseUpdatePassword>() {
            @Override
            public void onResponse(Call<ResponseUpdatePassword> call, Response<ResponseUpdatePassword> response) {
                if (cek(response.code())) {
                    Log.e(TAG, response.body().toString());
                    if (cek(response.body().getResponseCode())) {

                        passListener.onSuccess(response.body().getResponseMessage(), response.body().getData());
                    } else {
                        passListener.onError(response.body().getResponseMessage());
                    }
                } else {
                    passListener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdatePassword> call, Throwable t) {
                passListener.onError(t.getMessage());
            }
        });

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
//                String responseX = "";
//                try {
//                    responseX = response.errorBody().string();
//                    longLog(responseX);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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
