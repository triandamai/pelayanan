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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tdn.Static;
import com.tdn.data.persistensi.MyUser;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.ChatFragmentBinding;

public class Chat extends Fragment {

    private ChatViewModel mViewModel;
    private ChatFragmentBinding binding;
    private AdapterChat adapterChat;

    public static Chat newInstance() {
        return new Chat();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext())).get(ChatViewModel.class);
        binding.setVm(mViewModel);
        adapterChat = new AdapterChat(adapterClicked);
        binding.setIsRefresh(false);
        binding.setRefresh(refreshListener);
        binding.rv.setAdapter(adapterChat);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(ChatViewModel mViewModel) {
        mViewModel.getUserObjectLiveData().observe(getViewLifecycleOwner(), chatObjects -> {
            if (chatObjects != null) {
                adapterChat.setData(chatObjects);
            }

        });
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        mViewModel.fetchfromApi();
        mViewModel.fetchfromLocal();
        binding.setIsRefresh(false);
    };
    private AdapterClicked adapterClicked = pos -> {
        MyUser.getInstance(getContext()).setId(Static.KEY_LAST_CHAT_ID, adapterChat.getFromPosition(pos).getIdChat());
    };

}
