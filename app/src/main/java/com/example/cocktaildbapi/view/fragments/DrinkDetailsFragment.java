package com.example.cocktaildbapi.view.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.FragmentDetailsBinding;
import com.example.cocktaildbapi.databinding.FragmentDrinkDetailsBinding;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_CONFIRM;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_DELETE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_SAVE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.CONFIRM_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_PARCELABLE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_EDIT;


public class DrinkDetailsFragment extends Fragment implements View.OnClickListener {


    private static final String ARG_DRINK = "drink";

    private Drink drink;
    private OnActionPerformedListener listener;

    private FragmentDrinkDetailsBinding binding;


    public DrinkDetailsFragment() {
        // Required empty public constructor
    }

    public static DrinkDetailsFragment newInstance(Drink drink) {
        DrinkDetailsFragment fragment = new DrinkDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DRINK, drink);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drink = getArguments().getParcelable(ARG_DRINK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink_details, container, false);
        setHasOptionsMenu(true);
        binding.setVar(drink);
        binding.setClickHandler(this);


        return binding.getRoot();

    }




    /* ************* TOOLBAR & MENU ************** */

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_other, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Bundle bundle = new Bundle();
        switch (item.getItemId()) {

            case R.id.menu_edit:
                bundle.putParcelable(OBJECT_PARCELABLE, drink);
                bundle.putInt(BUNDLE_KEY, OPEN_EDIT);
                listener.onActionPerformed(bundle);
                return true;

            case R.id.menu_delete:
                bundle.putInt(BUNDLE_KEY, ACTION_CONFIRM);
                bundle.putInt(CONFIRM_KEY, ACTION_DELETE);
                bundle.putParcelable(OBJECT_PARCELABLE, drink);
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

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.fab) {
            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_KEY, ACTION_CONFIRM);
            bundle.putInt(CONFIRM_KEY, ACTION_SAVE);
            bundle.putParcelable(OBJECT_PARCELABLE, binding.getVar());
            listener.onActionPerformed(bundle);
        }
    }
}
