package com.tdn.laporan_desa.ui.komentar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.databinding.KomentarFragmentBinding;

public class Komentar extends Fragment {

    private KomentarViewModel mViewModel;
    private KomentarFragmentBinding binding;
    private AdapterKomentar adapterKomentar;
    private String id = "";

    public static Komentar newInstance() {
        return new Komentar();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.komentar_fragment, container, false);
        Bundle bundle = getArguments();

        if (bundle.getString("id_laporan") != null) {
            id = bundle.getString("id_laporan");
        }
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext(), actionListener, id)).get(KomentarViewModel.class);
        binding.setVm(mViewModel);
        mViewModel.idLap.setValue(id);


        adapterKomentar = new AdapterKomentar(pos -> {

        });
        binding.fabKomen.setOnClickListener(v -> {
            mViewModel.simpan();
        });
        binding.etKomentar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.isi.setValue(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.rv.setAdapter(adapterKomentar);
        return binding.getRoot();

    }


    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(KomentarViewModel mViewModel) {
        mViewModel.getKomentarObjectLiveData().observe(getViewLifecycleOwner(), laporanObjects -> {
            if (laporanObjects != null) {
                Log.e("tes", laporanObjects.toString());
                adapterKomentar.setData(laporanObjects);
            }
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Mengirim", BaseTransientBottomBar.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(String message) {
            mViewModel.fetchfromApi(id);
            mViewModel.fetchfromLocal(id);
            binding.etKomentar.setText("");
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
        }

        @Override
        public void onError(String message) {
            mViewModel.fetchfromApi(id);
            mViewModel.fetchfromLocal(id);
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
        }
    };
}
