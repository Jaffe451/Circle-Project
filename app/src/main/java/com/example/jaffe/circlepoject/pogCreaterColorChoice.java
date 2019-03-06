package com.example.jaffe.circlepoject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pogCreaterColorChoice.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pogCreaterColorChoice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pogCreaterColorChoice extends Fragment {

    private OnFragmentInteractionListener mListener;

    private int color;

    public pogCreaterColorChoice() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static pogCreaterColorChoice newInstance() {
        pogCreaterColorChoice fragment = new pogCreaterColorChoice();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        color = 0xFFcc0000;

    }

    public void onResume(){
        super.onResume();

        updateMe(color);

        //button listeners

        //red
        getActivity().findViewById(R.id.pogCreatPresetRe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMe(0xFFcc0000);
            }
        });

        getActivity().findViewById(R.id.pogCreatPresetBk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMe(0xFF000000);
            }
        });

        getActivity().findViewById(R.id.pogCreatPresetBl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMe(0xFF0099cc);
            }
        });


        getActivity().findViewById(R.id.pogCreatPresetGr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMe(0xFF669900);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pog_creater_color_choice, container, false);
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

    public int getColor(){
        return color;
    }

    private void updateMe(int c){
        color = c;
        ((TextView) getActivity().findViewById(R.id.pogMakerColorDisp)).setTextColor(color);

    }
}
