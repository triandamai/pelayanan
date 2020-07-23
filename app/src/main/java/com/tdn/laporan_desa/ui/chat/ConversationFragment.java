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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.VmFactory;
import com.tdn.laporan_desa.callback.ActionListener;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.ConversationFragmentBinding;

public class ConversationFragment extends Fragment {

    private ConversationViewModel mViewModel;
    private ConversationFragmentBinding binding;
    private AdapterConversation adapterConversation;

    public static ConversationFragment newInstance() {
        return new ConversationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.conversation_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity(), new VmFactory(getContext(), actionListener)).get(ConversationViewModel.class);
        adapterConversation = new AdapterConversation(getContext(), adapterClicked);
        binding.setIsRefresh(false);
        binding.setRefresh(refreshListener);
        binding.rv.setAdapter(adapterConversation);
        //binding.setVm(mViewModel);
        onClick();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(ConversationViewModel mViewModel) {
        mViewModel.getUserObjectLiveData().observe(getViewLifecycleOwner(), conversationObjects -> {
            if (conversationObjects != null) {
                adapterConversation.setData(conversationObjects);
            }
        });
    }

    private void onClick() {
        binding.fabSend.setOnClickListener(v -> {
            mViewModel.body.set(binding.etIsi.getText().toString());
            mViewModel.sendChat();
        });
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onStart() {
            Snackbar.make(binding.getRoot(), "Mengirim", BaseTransientBottomBar.LENGTH_LONG).show();
        }

        @Override
        public void onSuccess(String message) {
            Snackbar.make(binding.getRoot(), "Terkirim", BaseTransientBottomBar.LENGTH_LONG).show();
            binding.etIsi.setText("");
            mViewModel.fetchfromApi();
            mViewModel.fetchfromLocal();
        }

        @Override
        public void onError(String message) {
            Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        mViewModel.fetchfromApi();
        mViewModel.fetchfromLocal();
        binding.setIsRefresh(false);
    };
    private AdapterClicked adapterClicked = pos -> {

    };

}