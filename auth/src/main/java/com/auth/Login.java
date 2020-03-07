package com.auth;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindView(R.id.toolbar_log)
    Toolbar toolbar;
    @BindView(R.id.appbar_log)
    AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.toolbar_log, R.id.appbar_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_log:
                break;
            case R.id.appbar_log:
                break;
        }
    }
}
