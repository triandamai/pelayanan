package com.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Daftar extends AppCompatActivity {
    public static String TAG = "Register Activity";


    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.back_to_login)
    TextView backToLogin;
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_repassword)
    EditText inputRepassword;
    @BindView(R.id.input_nik)
    EditText inputNik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);



    }


    @OnClick({R.id.btn_register, R.id.back_to_login})
    public void bindViewOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                signup();
                break;
            case R.id.back_to_login:
                linkLogin();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
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
        btnRegister.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Daftar.this, ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        String username = inputUsername.getText().toString();
        String nik = inputNik.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String rePassword = inputRepassword.getText().toString();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onSignupSuccess();
                progressDialog.dismiss();
            }
        },3000);



    }

    private void onSignupSuccess() {
        Toast.makeText(getBaseContext(), "congratulations registration successful!", Toast.LENGTH_SHORT).show();
        btnRegister.setEnabled(false);
    }

    private void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registrasi Failed!! please check problem and try again", Toast.LENGTH_SHORT).show();
        btnRegister.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String nik = inputNik.getText().toString();
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();
        String rePassword = inputRepassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("Invalid email address!!");
            valid = false;
        }else {
            inputEmail.setError(null);
        }

        if (nik.isEmpty() || inputNik.length() <= 16) {
            inputNik.setError("NIK needs 16 numbers");
            valid = false;
        }else {
            inputNik.setError(null);
        }

        if (password.isEmpty() || inputPassword.length() < 8){
            inputPassword.setError("Between 8 alphanumeric characters");
            valid = false;
        }else{
            inputPassword.setError(null);
        }

        if (!password.equals(rePassword)) {
            inputRepassword.setError("Password not matching");
            valid = false;
        }else{
            inputRepassword.setError(null);
        }

        if (username.isEmpty() || inputUsername.length() < 6){
            inputUsername.setError("The username cannot be less than 6");
        }else{
            inputUsername.setError(null);
        }
        return valid;
    }
}
