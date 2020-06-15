package com.tdn.laporan_desa;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tdn.domain.model.KomentarModel;
import com.tdn.domain.model.UserModel;
import com.tdn.domain.serialize.req.LaporanPostReq;
import com.tdn.laporan_desa.auth.LoginViewModel;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.ui.chat.ChatViewModel;
import com.tdn.laporan_desa.ui.datauser.DataUserViewModel;
import com.tdn.laporan_desa.ui.home.HomePageViewModel;
import com.tdn.laporan_desa.ui.komentar.KomentarViewModel;
import com.tdn.laporan_desa.ui.tambahlaporan.TambahLaporanViewModel;
import com.tdn.laporan_desa.ui.tambahuser.TambahUserViewModel;

public class VmFactory implements ViewModelProvider.Factory {
    private Context context;
    private ActionListener actionListener;
    private LaporanPostReq laporanPostReq;
    private UserModel userModel;
    private KomentarModel komentarModel;

    public VmFactory(@NonNull Context context) {
        this.context = context;
    }

    public VmFactory(@NonNull Context context, ActionListener actionListener) {
        this.context = context;
        this.actionListener = actionListener;
    }

    public VmFactory(@NonNull Context context, ActionListener actionListener, LaporanPostReq laporanPostReq) {
        this.context = context;
        this.actionListener = actionListener;
        this.laporanPostReq = laporanPostReq;
    }

    public VmFactory(@NonNull Context context, ActionListener actionListener, UserModel userModel) {
        this.context = context;
        this.actionListener = actionListener;
        this.userModel = userModel;
    }

    public VmFactory(@NonNull Context context, ActionListener actionListener, KomentarModel komentarModel) {
        this.context = context;
        this.actionListener = actionListener;
        this.komentarModel = komentarModel;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(HomePageViewModel.class)) {
            return (T) new HomePageViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(DataUserViewModel.class)) {
            return (T) new DataUserViewModel(context);
        } else if (modelClass.isAssignableFrom(TambahUserViewModel.class)) {
            return (T) new TambahUserViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(DataUserViewModel.class)) {
            return (T) new DataUserViewModel(context);
        } else if (modelClass.isAssignableFrom(TambahLaporanViewModel.class)) {
            return (T) new TambahLaporanViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(TambahUserViewModel.class)) {
            return (T) new TambahUserViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(ChatViewModel.class)) {
            return (T) new ChatViewModel(context, actionListener);
        } else if (modelClass.isAssignableFrom(KomentarViewModel.class)) {
            return (T) new KomentarViewModel(context, actionListener);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
