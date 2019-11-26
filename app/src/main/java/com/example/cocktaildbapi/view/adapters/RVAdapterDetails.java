package com.example.cocktaildbapi.view.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.HolderDetailsBinding;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;

import java.util.List;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_ID;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_CHILD_DETAILS;

public class RVAdapterDetails extends RecyclerView.Adapter<RVAdapterDetails.ViewHolder> {


    //TODO implement adapter for parent element
    private List<Drink> list;
    private OnActionPerformedListener listener;

    public RVAdapterDetails(List<Drink> list, OnActionPerformedListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HolderDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.holder_details, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Drink drink = list.get(position);
        holder.holderBinding.setVar(drink);

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
                            implements View.OnClickListener {

        HolderDetailsBinding holderBinding;


        public ViewHolder(HolderDetailsBinding holderBinding) {
            super(holderBinding.getRoot());
            this.holderBinding = holderBinding;
            this.holderBinding.setClickHandler(this);
        }



        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.holder) {
                Bundle bundle = new Bundle();
                bundle.putInt(BUNDLE_KEY, OPEN_CHILD_DETAILS);
                bundle.putString(OBJECT_ID, list.get(getAdapterPosition()).getIdDrink());
                listener.onActionPerformed(bundle);
            }
        }
    }


}