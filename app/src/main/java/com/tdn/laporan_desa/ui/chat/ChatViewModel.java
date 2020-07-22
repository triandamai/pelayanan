package com.tdn.laporan_desa.ui.chat;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.tdn.data.service.ApiService;
import com.tdn.laporan_desa.callback.ActionListener;

public class ChatViewModel extends ViewModel {
    public String TAG = this.getClass().getSimpleName();
    private Context context;
    private ApiService apiService;
    private ActionListener listener;
    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public ChatViewModel(Context context, ActionListener actionListener) {
        this.context = context;
        this.apiService = ApiService.Factory.create();
        this.isLoading.set(false);
        this.listener = actionListener;
    }
    // TODO: Implement the ViewModel
}