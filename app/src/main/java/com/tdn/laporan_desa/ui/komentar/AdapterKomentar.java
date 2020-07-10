package com.tdn.laporan_desa.ui.komentar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.realmobject.KomentarObject;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.callback.AdapterClicked;
import com.tdn.laporan_desa.databinding.ItemKomentarBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterKomentar extends RecyclerView.Adapter<AdapterKomentar.MyViewHolder> {
    private List<KomentarObject> laporanObjects = new ArrayList<>();
    private AdapterClicked listener;


    public AdapterKomentar(AdapterClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKomentarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_komentar, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setBind(laporanObjects.get(position));
        holder.binding.setClick(listener);
        holder.binding.setPos(position);
    }

    public void setData(List<KomentarObject> l) {
        if (this.laporanObjects == null) {
            this.laporanObjects = l;
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterKomentar.this.laporanObjects.size();
                }

                @Override
                public int getNewListSize() {
                    return l.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterKomentar.this.laporanObjects.get(oldItemPosition).getIdLaporan() == l.get(newItemPosition).getIdLaporan();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    KomentarObject lama = l.get(oldItemPosition);
                    KomentarObject baru = l.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });

            this.laporanObjects = l;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return laporanObjects == null ? 0 : laporanObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemKomentarBinding binding;

        public MyViewHolder(@NonNull ItemKomentarBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
