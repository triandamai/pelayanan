package com.tdn.laporan_desa.ui.kontak;

import android.app.AlertDialog;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.KontakFragmentBinding;

public class KontakFragment extends Fragment {

    private KontakViewModel mViewModel;
    private KontakFragmentBinding binding;
    private AdapterKontak adapterDataUser;

    public static KontakFragment newInstance() {
        return new KontakFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.kontak_fragment, container, false);
        adapterDataUser = new AdapterKontak(onClicked);
        binding.rv.setAdapter(adapterDataUser);
        binding.setVm(mViewModel);
        binding.setRefresh(refreshListener);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new VmFactory(getContext(), actionListener)).get(KontakViewModel.class);

        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(KontakViewModel mViewModel) {
        mViewModel.getUserObjectLiveData().observe(getViewLifecycleOwner(), userObjects -> {
            binding.setIsRefresh(false);
            if (userObjects != null) {
                adapterDataUser.setData(userObjects);

            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.setIsRefresh(true);
            mViewModel.fetchfromApi();
            mViewModel.fetchfromLocal();
        }
    };
    private AdapterClicked onClicked = pos -> {
        String[] aksi = {"Chat ke " + adapterDataUser.getFromPosition(pos).getNama()};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Aksi");
        builder.setItems(aksi, (dialog, which) -> {
            switch (which) {
                case 0:
                    mViewModel.idReceiver.set(adapterDataUser.getFromPosition(pos).getIdUser());
                    mViewModel.startChat();
                    break;
            }
        }).create();
        builder.show();
    };


    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Mohon Tunggu..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(String message) {
            Snackbar.make(binding.getRoot(), "Memulai percakapan...", BaseTransientBottomBar.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_chat);
        }

        @Override
        public void onError(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };

}