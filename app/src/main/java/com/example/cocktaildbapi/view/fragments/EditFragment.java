package com.example.cocktaildbapi.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.databinding.FragmentDetailsBinding;
import com.example.cocktaildbapi.databinding.FragmentEditBinding;
import com.example.cocktaildbapi.entity.Drink;
import com.example.cocktaildbapi.view.OnActionPerformedListener;

import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_CONFIRM;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.ACTION_UPDATE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.BUNDLE_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.CONFIRM_KEY;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_ID;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OBJECT_PARCELABLE;
import static com.example.cocktaildbapi.view.OnActionPerformedListener.OPEN_CHILD_DETAILS;


public class EditFragment extends Fragment implements View.OnClickListener {


    private static final String ARG_OBJECT = "object";

    private Drink drink;
    private OnActionPerformedListener listener;

    private FragmentEditBinding binding;


    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(Drink drink) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_OBJECT, drink);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drink = getArguments().getParcelable(ARG_OBJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false);
        setHasOptionsMenu(true);
        binding.setVar(drink);
        binding.setClickHandler(this);
        return binding.getRoot();

    }



    /* ******* CLICK EVENTS ******* */
    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        switch (v.getId()) {

            case R.id.btn_update:
                bundle.putInt(BUNDLE_KEY, ACTION_CONFIRM);
                bundle.putInt(CONFIRM_KEY, ACTION_UPDATE );
                bundle.putParcelable(OBJECT_PARCELABLE, binding.getVar());
                listener.onActionPerformed(bundle);
//                getFragmentManager().popBackStack(HOME_FRAGMENT, 0);
                break;

            case R.id.btn_cancel:
                bundle.putInt(BUNDLE_KEY, OPEN_CHILD_DETAILS);
                bundle.putString(OBJECT_ID, binding.getVar().getIdDrink());
                listener.onActionPerformed(bundle);
                break;

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


}
