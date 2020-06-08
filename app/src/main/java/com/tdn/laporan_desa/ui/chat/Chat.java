package com.tdn.laporan_desa.ui.chat;

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
import com.tdn.laporan_desa.databinding.ChatFragmentBinding;

public class Chat extends Fragment {

    private ChatViewModel mViewModel;
    private ChatFragmentBinding binding;

    public static Chat newInstance() {
        return new Chat();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);
        binding.setVm(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext())).get(ChatViewModel.class);
        // TODO: Use the ViewModel
    }

}
