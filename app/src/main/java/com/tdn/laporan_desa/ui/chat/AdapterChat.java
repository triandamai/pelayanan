package com.tdn.laporan_desa.ui.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.realmobject.ChatObject;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.ItemChatBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.MyViewHolder> {
    private List<ChatObject> user = new ArrayList<>();
    private AdapterClicked listener;


    public AdapterChat(AdapterClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_chat, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setBind(user.get(position));
        holder.binding.setClick(listener);
        holder.binding.setPos(position);
    }

    public void setData(List<ChatObject> l) {
        if (this.user == null) {
            this.user = l;
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterChat.this.user.size();
                }

                @Override
                public int getNewListSize() {
                    return l.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterChat.this.user.get(oldItemPosition).getIdChat() == l.get(newItemPosition).getIdChat();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ChatObject lama = l.get(oldItemPosition);
                    ChatObject baru = l.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });

            this.user = l;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return user == null ? 0 : user.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemChatBinding binding;

        public MyViewHolder(@NonNull ItemChatBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
