package com.tdn.laporan_desa.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.realmobject.LaporanObject;
import com.tdn.laporan_desa.R;
import com.tdn.laporan_desa.callback.AdapterLaporanClicked;
import com.tdn.laporan_desa.databinding.ItemLaporanBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.MyViewHolder> {
    private List<LaporanObject> laporanObjects = new ArrayList<>();
    private AdapterLaporanClicked listener;


    public AdapterLaporan(AdapterLaporanClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLaporanBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_laporan, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setBind(laporanObjects.get(position));
        holder.binding.setClick(listener);
        holder.binding.setPos(position);
    }

    public void setData(List<LaporanObject> l) {
        if (this.laporanObjects == null) {
            this.laporanObjects = l;
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterLaporan.this.laporanObjects.size();
                }

                @Override
                public int getNewListSize() {
                    return l.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterLaporan.this.laporanObjects.get(oldItemPosition).getIdLaporan() == l.get(newItemPosition).getIdLaporan();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    LaporanObject lama = l.get(oldItemPosition);
                    LaporanObject baru = l.get(newItemPosition);
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
        private ItemLaporanBinding binding;

        public MyViewHolder(@NonNull ItemLaporanBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
