package com.tdn.laporan_desa.ui.home;

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

import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.AdapterLaporanClicked;
import com.tdn.laporan_desa.databinding.HomePageFragmentBinding;

public class HomePage extends Fragment {

    private HomePageViewModel mViewModel;
    private HomePageFragmentBinding binding;
    private AdapterLaporan adapterLaporan;

    public static HomePage newInstance() {
        return new HomePage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_page_fragment, container, false);
        binding.setVm(mViewModel);
        binding.setISrefresh(false);
        binding.setRefresh(refreshListener);
        adapterLaporan = new AdapterLaporan(onClicked);
        binding.rv.setAdapter(adapterLaporan);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext())).get(HomePageViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(HomePageViewModel mViewModel) {
        mViewModel.getLaporanObjectLiveData().observe(getViewLifecycleOwner(), laporanObjects -> {
            binding.setISrefresh(false);
            if (laporanObjects != null) {

                adapterLaporan.setData(laporanObjects);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.setISrefresh(true);
            mViewModel.fetchfromApi();
            mViewModel.fetchfromLocal();
        }
    };
    private AdapterLaporanClicked onClicked = new AdapterLaporanClicked() {
        @Override
        public void onComment(int pos) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.nav_komentar);
        }

        @Override
        public void onDetail(int pos) {

        }
    };
}
