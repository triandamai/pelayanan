package com.auth;

import com.auth.R;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    private static String TAG = "Login Activity";




    AppBarLayout appbar;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.link_register)
    TextView linkRegis;
    @BindView(R.id.link_forget)
    TextView linkForget;
    @BindView(R.id.input_email)
    EditText eMail;
    @BindView(R.id.input_password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_login, R.id.link_forget, R.id.link_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.link_register:
                linkRegister();
                break;
            case R.id.link_forget:
                linkForget();
                break;
            default:
                break;

        }
    }

    public void login(){
        Log.d(TAG, "Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login.this, ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating....");
        progressDialog.show();

        String inputEmail = eMail.getText().toString();
        String inputPassword = password.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSucces();
                        progressDialog.dismiss();
                    }
                },3000
        );

    }

    private void onLoginSucces() {
        Toast.makeText(this, "Login Succes..!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void onLoginFailed() {
        Toast.makeText(this, "Login Failed.", Toast.LENGTH_SHORT).show();
        btnLogin.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String inputMail = eMail.getText().toString();
        String inputPassword = password.getText().toString();

        if (inputMail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(inputMail).matches()){
            eMail.setError("Enter a valid email address");
            valid = false;
        }else {
            eMail.setError(null);
        }

        if (inputPassword.isEmpty() || password.length() < 8){
            password.setError("Between 8 alphanumeric characters");
            valid = false;
        }else {
            password.setError(null);
        }
        return valid;


    }

    private void linkRegister(){
        Intent mIntent = new Intent(Login.this, Daftar.class);
        startActivity(mIntent);
    }
    private void linkForget(){

    }


}
