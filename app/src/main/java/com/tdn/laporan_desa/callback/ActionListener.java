package com.tdn.laporan_desa.callback;

public interface ActionListener {
    void onStart();

    void onSuccess(String message);

    void onError(String message);
}
