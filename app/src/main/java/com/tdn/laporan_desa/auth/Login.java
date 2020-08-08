package com.tdn.laporan_desa.auth;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionChangePassListener;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.ActivityLoginBinding;
import com.tdn.laporan_desa.ui.MainActivity;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private MaterialAlertDialogBuilder alertDialog;
    private MaterialAlertDialogBuilder alertDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new VmFactory(this, actionListener, actionChangePassListener)).get(LoginViewModel.class);
        binding.setVm(loginViewModel);
        EditText input = new EditText(this);
        input.setHint("Masukkan Username");
        input.setInputType(
                InputType.TYPE_CLASS_TEXT);

        alertDialog = new MaterialAlertDialogBuilder(this, R.style.dialog);
        alertDialog2 = new MaterialAlertDialogBuilder(this, R.style.dialog);
        alertDialog.create();
        alertDialog.setTitle("Lupa Password");
        alertDialog.setView(input);
        alertDialog.setPositiveButton("OKE", (dialog, which) -> {
            if (!TextUtils.isEmpty(input.getText().toString())) {
                loginViewModel.lupa(input.getText().toString());
            } else {

            }
            dialog.dismiss();
        });
        binding.tvLupaPassword.setOnClickListener(v -> {
            alertDialog.show();
        });

    }

    private ActionChangePassListener actionChangePassListener = new ActionChangePassListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(String message, String hash) {
            alertDialog2.setTitle("Password baru anda");
            alertDialog2.setMessage(hash);
            alertDialog2.setCancelable(false);
            alertDialog2.setPositiveButton("Copy", (dialog, which) -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password_laporan", hash);
                clipboard.setPrimaryClip(clip);
            });
            alertDialog2.show();
        }

        @Override
        public void onError(String message) {

            alertDialog.setTitle("Gagal..");
            alertDialog.setMessage(message);
            alertDialog.setPositiveButton("Oke", (dialog, which) -> {
                dialog.dismiss();
            });
            alertDialog.show();
        }
    };
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
