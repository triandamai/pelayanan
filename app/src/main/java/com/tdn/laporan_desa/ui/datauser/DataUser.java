package com.tdn.laporan_desa.ui.datauser;

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

import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
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
        adapterDataUser = new AdapterDataUser(onClicked);
        binding.rv.setAdapter(adapterDataUser);
        binding.setVm(mViewModel);
        binding.setRefresh(refreshListener);


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new VmFactory(getContext())).get(DataUserViewModel.class);

        // TODO: Use the ViewModel
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

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.setIsRefresh(true);
            mViewModel.fetchfromApi();
            mViewModel.fetchfromLocal();
        }
    };
    private AdapterClicked onClicked = pos -> {

    };
}
