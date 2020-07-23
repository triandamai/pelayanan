package com.tdn.laporan_desa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.tdn.data.persistensi.MyUser;
import com.tdn.data.service.ApiService;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.auth.Login;
import com.tdn.laporan_desa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chat, R.id.nav_datauser, R.id.nav_komentar, R.id.nav_tambah_laporan)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        ImageView imageView = binding.navView.getHeaderView(0).findViewById(R.id.imageView);
        TextView nama = binding.navView.getHeaderView(0).findViewById(R.id.tv_nama);
        TextView nik = binding.navView.getHeaderView(0).findViewById(R.id.tv_nik);

        if (MyUser.getInstance(this).getUser().getLevel().equalsIgnoreCase("kades")) {
            binding.navView.inflateMenu(R.menu.activity_main_admin);
        } else {
            binding.navView.inflateMenu(R.menu.activity_main_user);
        }
        nama.setText(MyUser.getInstance(this).getUser().getNama());
        nik.setText("NIK : " + MyUser.getInstance(this).getUser().getNik());
        Picasso.get().load(ApiService.BASE_URL_IMAGE + MyUser.getInstance(this).getUser().getMedia()).into(imageView);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = controller.getCurrentDestination().getId();
            if (id == R.id.nav_tambah_laporan ||
                    id == R.id.nav_tambah_user ||
                    id == R.id.nav_komentar ||
                    id == R.id.nav_conversation) {
                fab.setVisibility(View.GONE);

            } else if (id == R.id.nav_home) {
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(v -> {
                    navController.navigate(R.id.nav_tambah_laporan);
                });
            } else if (id == R.id.nav_chat) {
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(v -> {
                    navController.navigate(R.id.nav_kontak);
                });
            } else if (id == R.id.nav_datauser) {
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(v -> {
                    navController.navigate(R.id.nav_tambah_user);
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_keluar:
                MyUser.getInstance(getApplicationContext()).signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
