package com.tdn.laporan_desa.ui.tambahuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.TambahUserFragmentBinding;

public class TambahUser extends Fragment {

    private TambahUserViewModel mViewModel;
    private TambahUserFragmentBinding binding;

    public static TambahUser newInstance() {
        return new TambahUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.tambah_user_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VmFactory(getContext(), actionListener)).get(TambahUserViewModel.class);

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
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_datauser);
        }

        @Override
        public void onError(String message) {
            mViewModel.isLoading.set(false);
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };
}
