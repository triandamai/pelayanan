package com.tdn.laporan_desa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.model.UserModel;
import com.tdn.laporan_desa.auth.Login;
import com.tdn.laporan_desa.ui.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        UserModel userModel = MyUser.getInstance(getApplicationContext()).getUser();
        new Handler().postDelayed(() -> {
            if (userModel == null) {
                startActivity(new Intent(SplashScreen.this, Login.class));
                finish();
            } else {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }
        }, 3000);
    }
}
