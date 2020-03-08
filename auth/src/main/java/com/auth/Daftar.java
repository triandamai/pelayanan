package com.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Daftar extends AppCompatActivity {
    public static String TAG = "Register Activity";


    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.back_to_login)
    TextView backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);



    }
    @OnClick({R.id.btn_register, R.id.back_to_login})
    private void bindViewOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                signup();
                break;
            case R.id.back_to_login:
                linkLogin();
                break;
        }
    }

    private void linkLogin() {
        Intent mIntent = new Intent(Daftar.this, Login.class);
        startActivity(mIntent);
    }

    private void signup() {
        Log.d(TAG, "Signup");

        if (!validate()){
            onSignupFailed();
            return;
        }



    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registrasi Failed!! please check problem and try again", Toast.LENGTH_SHORT).show();
        btnRegister.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;
    }
}
