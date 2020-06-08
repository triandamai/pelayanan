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

import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.databinding.DataUserFragmentBinding;

public class DataUser extends Fragment {

    private DataUserViewModel mViewModel;
    private DataUserFragmentBinding binding;

    public static DataUser newInstance() {
        return new DataUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.data_user_fragment, container, false);
        binding.setVm(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext())).get(DataUserViewModel.class);
        // TODO: Use the ViewModel
    }

}
