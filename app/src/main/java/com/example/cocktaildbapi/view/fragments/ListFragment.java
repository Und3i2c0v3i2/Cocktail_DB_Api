package com.example.cocktaildbapi.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.FragmentListBinding;
import com.example.cocktaildbapi.entity.Category;
import com.example.cocktaildbapi.view.OnActionPerformedListener;
import com.example.cocktaildbapi.view.adapters.RVAdapterMaster;

import java.util.ArrayList;
import java.util.List;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_ADD;


public class ListFragment extends Fragment {

    public static final String ARG_LIST = "arg_list";

    private FragmentListBinding binding;
    private List<Category> list;

    private OnActionPerformedListener listener;



    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance(List<Category> list) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, (ArrayList<? extends Parcelable>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        setHasOptionsMenu(true);

        setupAdapter(list);
        return binding.getRoot();
    }


    /* ************* ADAPTER **************** */
    private void setupAdapter(List<Category> list) {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RVAdapterMaster adapter = new RVAdapterMaster(list, listener);
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
