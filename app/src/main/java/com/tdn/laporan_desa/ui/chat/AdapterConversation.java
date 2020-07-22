package com.tdn.laporan_desa.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.data.persistensi.MyUser;
import com.tdn.domain.realmobject.ConversationObject;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.ItemConversationMeBinding;
import com.tdn.laporan_desa.databinding.ItemConversationToBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterConversation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ConversationObject> user = new ArrayList<>();
    private AdapterClicked listener;
    private Context context;

    private final int VIEW_TIPE_TO = 1;
    private final int VIEW_TIPE_ME = 2;


    public AdapterConversation(Context context, AdapterClicked listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (user.get(position).getSenderId().equals(MyUser.getInstance(context).getUser().getIdUser())) {
            return VIEW_TIPE_ME;
        } else {
            return VIEW_TIPE_TO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConversationMeBinding bindingME = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_conversation_me, parent, false);
        ItemConversationToBinding bindingTO = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_conversation_to, parent, false);
        switch (viewType) {
            case VIEW_TIPE_ME:
                return new MyViewHolderME(bindingME);
            case VIEW_TIPE_TO:
                return new MyViewHolderTO(bindingTO);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TIPE_ME:
                MyViewHolderME viewme = (MyViewHolderME) holder;
                viewme.binding.tvIsi.setText(user.get(position).getBody());
                viewme.binding.tvNama.setText(user.get(position).getSenderNama());
                viewme.binding.tvWaktu.setText(user.get(position).getCreatedAt());
                break;
            case VIEW_TIPE_TO:
                MyViewHolderTO viewto = (MyViewHolderTO) holder;
                viewto.binding.tvIsi.setText(user.get(position).getBody());
                viewto.binding.tvNama.setText(user.get(position).getSenderNama());
                viewto.binding.tvWaktu.setText(user.get(position).getCreatedAt());
                break;
            default:
                MyViewHolderME view = (MyViewHolderME) holder;
                view.binding.tvIsi.setText(user.get(position).getBody());
                view.binding.tvNama.setText(user.get(position).getSenderNama());
                view.binding.tvWaktu.setText(user.get(position).getCreatedAt());
                break;
        }
    }


    public void setData(List<ConversationObject> l) {
        if (this.user == null) {
            this.user = l;
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterConversation.this.user.size();
                }

                @Override
                public int getNewListSize() {
                    return l.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterConversation.this.user.get(oldItemPosition).getIdDetailChat() == l.get(newItemPosition).getIdDetailChat();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ConversationObject lama = l.get(oldItemPosition);
                    ConversationObject baru = l.get(newItemPosition);
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

    public class MyViewHolderME extends RecyclerView.ViewHolder {
        private ItemConversationMeBinding binding;

        public MyViewHolderME(@NonNull ItemConversationMeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public class MyViewHolderTO extends RecyclerView.ViewHolder {
        private ItemConversationToBinding binding;

        public MyViewHolderTO(@NonNull ItemConversationToBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
