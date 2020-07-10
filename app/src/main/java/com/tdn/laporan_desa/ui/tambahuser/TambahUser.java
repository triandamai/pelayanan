package com.tdn.laporan_desa.ui.tambahuser;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tdn.laporan_desa.BaseFragment;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.TambahUserFragmentBinding;
import com.tdn.laporan_desa.datepicker.DatePickerMax;

import java.io.IOException;
import java.util.List;

public class TambahUser extends BaseFragment {

    private TambahUserViewModel mViewModel;
    private TambahUserFragmentBinding binding;
    private DatePickerMax datePickerMax;

    public static TambahUser newInstance() {
        return new TambahUser();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tambah_user_fragment, container, false);
        binding.setIsLoading(false);

        mViewModel = new ViewModelProvider(this, new VmFactory(getContext(), actionListener)).get(TambahUserViewModel.class);
        binding.setVm(mViewModel);
        datePickerMax = new DatePickerMax();
        datePickerMax.setDateListener((tahun, bulan, hari) -> {
            binding.etTanggallahir.setText(tahun + "-" + bulan + "-" + hari);
            mViewModel.tanggallahir.setValue(tahun + "-" + bulan + "-" + hari);
        });
        binding.etTanggallahir.setOnClickListener(v -> {
            datePickerMax.show(getParentFragmentManager(), "Pick");
        });
        binding.etLevel.setOnClickListener(v -> {
            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Ambil foto");

            // add a list
            String[] animals = {"Kepala Desa", "Perangkat Desa", "Warga"};
            builder.setItems(animals, (dialog, which) -> {
                switch (which) {
                    case 0:
                        binding.etLevel.setText("Kepala Desa");
                        mViewModel.level.setValue("kades");
                        break;
                    case 1:
                        binding.etLevel.setText("Perangkat Desa");
                        mViewModel.level.setValue("perangkat");
                        break;
                    case 2:
                        binding.etLevel.setText("Warga");
                        mViewModel.level.setValue("warga");
                        break;
                    default:
                        binding.etLevel.setText("Warga");
                        binding.etLevel.setText("warga");
                }
            });

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        });
        binding.ivPickImage.setOnClickListener(v -> {
            Dexter.withActivity(getActivity())
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                showImagePickerOptions();

                            }

                            if (report.isAnyPermissionPermanentlyDenied()) {
                                showSettingsDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        });
        binding.etNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.nama.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etNik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.nik.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.username.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.password.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etTempatlahir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.tempatlahir.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etAlamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.alamat.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return binding.getRoot();
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String message) {
            binding.setIsLoading(false);
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_datauser);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    binding.ivPickImage.setImageBitmap(bitmap);
                    //   Picasso.get().load(uri.toString()).into(binding.ivPickImage);
                    mViewModel.media.setValue(encodeImage(uri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
