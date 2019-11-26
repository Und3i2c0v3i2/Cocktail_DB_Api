package com.example.cocktaildbapi.view.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cocktaildbapi.databinding.FragmentAddBinding;
import com.example.cocktaildbapi.R;
import com.example.cocktaildbapi.view.OnActionPerformedListener;


public class AddFragment
        extends Fragment
        implements View.OnClickListener {


    private FragmentAddBinding binding;
    private OnActionPerformedListener listener;

    //TODO set model
    // Master parent;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);
        setHasOptionsMenu(true);

        binding.setClickHandler(this);
//        parent = new Master();
//        binding.setVar(realty);

        return binding.getRoot();
    }


    /* ******* CLICK EVENTS ******* */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.btn_add:
//                Realty realty = binding.getRealty();
//                Bundle bundle = new Bundle();
//                bundle.putInt(BUNDLE_KEY, ACTION_SAVE);
//                bundle.putParcelable(OBJECT_PARCELABLE, realty);
//                listener.onActionPerformed(bundle);
//                getFragmentManager().popBackStack(HOME_FRAGMENT, 0);
//                break;
//
//            case R.id.btn_cancel:
//                getFragmentManager().popBackStack(HOME_FRAGMENT, 0);
//                break;
//
//            case R.id.btn_add_img:
//                Bundle bundle1 = new Bundle();
//                bundle1.putInt(BUNDLE_KEY, ACTION_PICK_IMG);
//                listener.onActionPerformed(bundle1);
//                break;

        }
    }


    /* ******* LIFE CYCLE ******* */

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnActionPerformedListener) {
            listener = (OnActionPerformedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    // get data from Activity
    public void getData(Uri uri) {

//        Image image = new Image();
//        image.setUrl(uri.toString());
//        if(realty.getImgUrls() != null) {
//            image.setRealty(realty);
//            realty.getImgUrls().add(image);
//        } else {
//            List<Image> list = new ArrayList<>();
//            list.add(image);
//            realty.setImgUrls(list);
//            image.setRealty(realty);
//        }
//
//        Bitmap bitmap = null;
//        try {
//            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        binding.imgPreview.setImageBitmap(bitmap);
    }
}
