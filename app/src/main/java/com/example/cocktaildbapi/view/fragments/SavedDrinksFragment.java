package com.example.cocktaildbapi.view.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.FragmentDetailsBinding;
import com.example.cocktaildbapi.databinding.FragmentSavedDrinksBinding;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;
import com.example.cocktaildbapi.view.adapters.RVAdapterDetails;

import java.util.ArrayList;
import java.util.List;

public class SavedDrinksFragment extends Fragment {

    private static final String ARG_PARAM = "list";

    private List<Drink> drinks;
    private OnActionPerformedListener listener;

    private FragmentSavedDrinksBinding binding;


    public SavedDrinksFragment() {
        // Required empty public constructor
    }

    public static SavedDrinksFragment newInstance(List<Drink> list) {
        SavedDrinksFragment fragment = new SavedDrinksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinks = getArguments().getParcelableArrayList(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_drinks, container, false);
        setHasOptionsMenu(true);

        setupAdapter(drinks);

        return binding.getRoot();

    }


    /* ******* ADAPTER ******* */
    private void setupAdapter(List<Drink> list) {

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RVAdapterDetails adapter = new RVAdapterDetails(list, listener);
        recyclerView.setAdapter(adapter);
    }


    /* ******* LIFE CYCLE ******* */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnActionPerformedListener) {
            listener = (OnActionPerformedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnActionPerformedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}
