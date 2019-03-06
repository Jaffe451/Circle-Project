package com.example.jaffe.circlepoject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LevelCeator.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LevelCeator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelCeator extends Fragment {

    private OnFragmentInteractionListener mListener;

    private FrameLayout lcDisplay;

    private ArrayList<MoveBarrier> barriers;


    public LevelCeator() {
        // Required empty public constructor
        barriers = new ArrayList<MoveBarrier>();
    }


    public static LevelCeator newInstance() {
        LevelCeator fragment = new LevelCeator();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public void onResume(){
        super.onResume();

        lcDisplay = getActivity().findViewById(R.id.lcDisplay);



        //button listeners

        //undo
        getActivity().findViewById(R.id.lcUndo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        //delete
        getActivity().findViewById(R.id.lcDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!barriers.isEmpty()){
                    for(MoveBarrier b: barriers){
                        if(b.isSelected()){
                            barriers.remove(b);
                            updateMe();
                        }
                    }
                }
            }
        });

        //back
        getActivity().findViewById(R.id.lcBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        //clear
        getActivity().findViewById(R.id.lcClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barriers.removeAll(barriers);
                updateMe();
            }
        });

        //vbar
        getActivity().findViewById(R.id.lcVbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barriers.add(new MoveBarrier(getContext(), lcDisplay.getWidth()/2, lcDisplay.getHeight()/2, 90));
                updateMe();
            }
        });

        //hbar
        getActivity().findViewById(R.id.lcHbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barriers.add(new MoveBarrier(getContext(), lcDisplay.getWidth()/2, lcDisplay.getHeight()/2, 0));
                updateMe();
            }
        });

        //save
        getActivity().findViewById(R.id.lcSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@todo
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_ceator, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void updateBarrier(MoveBarrier b, int deltaX, int deltaY, boolean delete){

    }

    private void updateMe(){
        lcDisplay.removeAllViews();
        if(!barriers.isEmpty()){
            for(MoveBarrier b: barriers){
                lcDisplay.addView(b);
            }
        }
    }


}
