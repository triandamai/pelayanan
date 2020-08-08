package com.tdn.laporan_desa.callback;

public interface ActionChangePassListener {
    void onStart();

    void onSuccess(String message, String hash);

    void onError(String message);
}
