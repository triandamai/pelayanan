package com.tdn.laporan_desa.ui.tambahlaporan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.tdn.data.service.ApiService;
import com.tdn.laporan_desa.BaseFragment;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.TambahLaporanFragmentBinding;

import java.io.IOException;
import java.util.List;

public class TambahLaporan extends BaseFragment {

    private TambahLaporanViewModel mViewModel;
    private TambahLaporanFragmentBinding binding;
    private ApiService apiService;

    public static TambahLaporan newInstance() {
        return new TambahLaporan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tambah_laporan_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext(), actionListener)).get(TambahLaporanViewModel.class);

        binding.setVm(mViewModel);
        binding.ivPickImage.setOnClickListener(v -> {
            Dexter.withActivity(getActivity())
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                //showImagePickerOptions();
                                launchCameraIntent();
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
        return binding.getRoot();
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            mViewModel.isLoading.set(true);
        }

        @Override
        public void onSuccess(String message) {
            mViewModel.isLoading.set(false);
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_home);
        }

        @Override
        public void onError(String message) {
            mViewModel.isLoading.set(false);
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
                    mViewModel.foto.set(encodeImage(uri.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
