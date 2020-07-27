package com.tdn.laporan_desa.ui.datauser;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.domain.realmobject.UserObject;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.DataUserFragmentBinding;

public class DataUser extends Fragment {

    private DataUserViewModel mViewModel;
    private DataUserFragmentBinding binding;
    private AdapterDataUser adapterDataUser;

    public static DataUser newInstance() {
        return new DataUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.data_user_fragment, container, false);
        mViewModel = new ViewModelProvider(this, new VmFactory(getContext(), actionListener)).get(DataUserViewModel.class);

        adapterDataUser = new AdapterDataUser(onClicked);
        binding.rv.setAdapter(adapterDataUser);
        binding.setVm(mViewModel);
        binding.setRefresh(refreshListener);


        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataUserViewModel mViewModel) {
        mViewModel.getUserObjectLiveData().observe(getViewLifecycleOwner(), userObjects -> {
            binding.setIsRefresh(false);
            if (userObjects != null) {
                adapterDataUser.setData(userObjects);

            }
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Proses..", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onError(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.setIsRefresh(true);
            mViewModel.fetchfromApi();
            mViewModel.fetchfromLocal();
        }
    };
    private AdapterClicked onClicked = pos -> {
        UserObject o = adapterDataUser.getFromPosition(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Hapus User");
        builder.setMessage("Hapus User" + o.getNama() + " ? ");
        builder.setPositiveButton("Iya, hapus", (dialog, which) -> {
            mViewModel.hapus(o.getIdUser());
        });
    };
}
