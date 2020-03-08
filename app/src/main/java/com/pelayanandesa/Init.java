package com.pelayanandesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Init extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Intent intent = new Intent();
        intent.setClassName(BuildConfig.APPLICATION_ID,"com.auth.Login");
        startActivity(intent);
    }
}
