package com.example.aad9exam.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class SearchFragment extends Fragment implements View.OnClickListener {

    /*
    TODO if separate search view is needed
     set recycler view adapter to display results
     */


    public static final String ARG_MEALS = "meals";

//    private Category category;
//    private List<Meal> list;
//
//    private OnActionPerformedListener listener;
//    private FragmentSearchBinding binding;
//
//    private String search;
//
//    public SearchFragment() {
//        // Required empty public constructor
//    }
//
//
//    public static SearchFragment newInstance(List<Meal> list) {
//        SearchFragment fragment = new SearchFragment();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList(ARG_MEALS, (ArrayList<? extends Parcelable>) list);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            list = getArguments().getParcelableArrayList(ARG_MEALS);
//        }
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
//        setupAdapter(list);
//        binding.search.setClickListener(this);
//        binding.search.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                search = query;
//                Bundle bundle = getBundle();
//                listener.onActionPerformed(bundle);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                search = newText;
//                return true;
//            }
//        });
//
//
//        return binding.getRoot();
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnActionPerformedListener) {
//            listener = (OnActionPerformedListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnActionPerformedListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }
//
//
//    /* ******* ADAPTER ******* */
//    private void setupAdapter(List<Meal> list) {
//        RecyclerView recyclerView = binding.recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        RVAdapterDetails adapter = new RVAdapterDetails(list, listener);
//        recyclerView.setAdapter(adapter);
//    }
//
    @Override
    public void onClick(View v) {
//
//        if(v.getId() == R.id.btn_search) {
//            Bundle bundle = getBundle();
//            listener.onActionPerformed(bundle);
//        }
    }
//
//    private Bundle getBundle() {
//        Bundle bundle = new Bundle();
//        bundle.putInt(BUNDLE_KEY, ACTION_SEARCH);
//        bundle.putString(SEARCH, search);
//        return bundle;
//    }
}
