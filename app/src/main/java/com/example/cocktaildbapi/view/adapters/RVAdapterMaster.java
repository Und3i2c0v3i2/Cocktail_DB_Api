package com.example.cocktaildbapi.view.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.HolderMasterBinding;
import com.example.cocktaildbapi.entity.Category;
import com.example.cocktaildbapi.view.OnActionPerformedListener;

import java.util.List;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_PARCELABLE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_PARENT_DETAILS;

public class RVAdapterMaster extends RecyclerView.Adapter<RVAdapterMaster.ViewHolder> {

    private List<Category> list;
    private OnActionPerformedListener listener;

    public RVAdapterMaster(List<Category> list, OnActionPerformedListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HolderMasterBinding binding = DataBindingUtil.inflate(inflater, R.layout.holder_master, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = list.get(position);
        holder.holderBinding.setVar(category);

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        HolderMasterBinding holderBinding;

        public ViewHolder(HolderMasterBinding holderBinding) {
            super(holderBinding.getRoot());
            this.holderBinding = holderBinding;
            this.holderBinding.setClickHandler(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.holder) {
                Bundle bundle = new Bundle();
                bundle.putInt(BUNDLE_KEY, OPEN_PARENT_DETAILS);
                bundle.putParcelable(OBJECT_PARCELABLE, list.get(getAdapterPosition()));
                listener.onActionPerformed(bundle);
            }
        }
    }


}