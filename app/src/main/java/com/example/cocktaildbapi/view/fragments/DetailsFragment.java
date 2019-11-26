package com.example.cocktaildbapi.view.fragments;

import android.content.Context;
import android.os.Bundle;
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
import com.example.cocktaildbapi.databinding.FragmentDetailsBinding;
import com.example.cocktaildbapi.entity.Category;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;
import com.example.cocktaildbapi.view.adapters.RVAdapterDetails;

import java.util.List;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_SAVED;


public class DetailsFragment extends Fragment implements View.OnClickListener {


    private static final String ARG_PARENT = "category";

    private Category category;
    private List<Drink> drinks;
    private OnActionPerformedListener listener;

    private FragmentDetailsBinding binding;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(Category parent) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARENT, parent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getParcelable(ARG_PARENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        setHasOptionsMenu(true);
        binding.setClickHandler(this);
        binding.setVar(category);

        drinks = category.getDrinkDetails();
        setupAdapter(drinks);

        return binding.getRoot();

    }


    // TODO if needed
    /* ******* CHILD ADAPTER ******* */
    private void setupAdapter(List<Drink> list) {

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RVAdapterDetails adapter = new RVAdapterDetails(list, listener);
        recyclerView.setAdapter(adapter);
    }



    /* ************* TOOLBAR & MENU ************** */

    //TODO inflate menu resource
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_saved, menu);
    }


    // TODO toolbar options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Bundle bundle = new Bundle();
        switch (item.getItemId()) {

            case R.id.menu_saved:
                bundle.putInt(BUNDLE_KEY, OPEN_SAVED);
                listener.onActionPerformed(bundle);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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

    // TODO clicks inside fragment if needed
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        switch (v.getId()) {

//            case R.id.btn_schedule:
//                bundle.putInt(BUNDLE_KEY, ACTION_SCHEDULE);
//                listener.onActionPerformed(bundle);
//                break;
//
//            case R.id.phone_dial:
//                bundle.putInt(BUNDLE_KEY, ACTION_DIAL);
//                bundle.putParcelable(OBJECT_PARCELABLE, realty);
//                listener.onActionPerformed(bundle);
        }

    }
}
