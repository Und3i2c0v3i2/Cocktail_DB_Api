package com.example.aad9exam.view.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;

import com.example.aad9exam.R;
import com.example.aad9exam.databinding.DialogLayoutBinding;
import com.example.aad9exam.entity.Drink;
import com.example.aad9exam.view.OnActionPerformedListener;

import java.util.Objects;

public class InputDialogFragment extends AppCompatDialogFragment {

    /*
     TODO in calling fragment:
        setTargetFragment(target) lets the "called" fragment know where to send the result.
        InputDialogFragment dialogFragment = new InputDialogFragment();
                dialogFragment.setTargetFragment(this, 1);
                dialogFragment.getData(this.touristAttraction);
                dialogFragment.show(getFragmentManager(), "dialog");
     */

    // for activity
    private OnActionPerformedListener listener;
    // for fragment
    private OnInputFragmentListener inputListener;

    private DialogLayoutBinding binding;
//    private Child child;
//    private Master parent;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil
                .inflate(getActivity().getLayoutInflater(), R.layout.dialog_layout, null, false);

//        child = new Child();
        binding.spinnerPhone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                child.setType(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return getDialog(binding.getRoot());
    }


    private Dialog getDialog(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setView(view)
//                .setTitle("Add phone for contact " + parent.getName())
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //TODO get data from input field and pass to calling fragment

                        // set field
//                        phone.setNumber(Objects.requireNonNull(binding.addPhone.getEditText()).getText().toString());
//                        phone.setContact(contact);

                        // pass data to activity
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable(OBJECT_PARCELABLE, child);
//
//                        listener.onActionPerformed(bundle);

//                        inputListener.getInput(child);
                    }
                });


        return builder.create();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnActionPerformedListener) {
            listener = (OnActionPerformedListener) context;
        }

        try{
            inputListener = (OnInputFragmentListener) getTargetFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }


    // For fragment-to-fragment communications
    // TODO another fragment should implement this interface
    public interface OnInputFragmentListener {

        void getInput(Drink drink);
    }

}
