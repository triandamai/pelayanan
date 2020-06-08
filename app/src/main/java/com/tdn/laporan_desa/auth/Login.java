package com.tdn.laporan_desa.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.ActivityLoginBinding;
import com.tdn.laporan_desa.ui.MainActivity;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new VmFactory(this, actionListener)).get(LoginViewModel.class);
        binding.setVm(loginViewModel);
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses Masuk", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        @Override
        public void onSuccess(String message) {
            Snackbar.make(binding.getRoot(), "" + message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        @Override
        public void onError(String message) {

            Snackbar.make(binding.getRoot(), "Login Gagal " + message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };
}
