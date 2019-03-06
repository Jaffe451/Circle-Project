package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pogCreatorShapeChoice.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pogCreatorShapeChoice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pogCreatorShapeChoice extends Fragment {


    private OnFragmentInteractionListener mListener;

    private int color;
    private Drawable shape;
    private PogShape pog;

    public pogCreatorShapeChoice() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static pogCreatorShapeChoice newInstance(int color) {
        pogCreatorShapeChoice fragment = new pogCreatorShapeChoice();
        fragment.color = color;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        shape = ContextCompat.getDrawable(getContext(), R.drawable.circleicon);
    }

    public void onResume(){
        super.onResume();


        updateMe();

        getActivity().findViewById(R.id.circleS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.circleicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.squareS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.squareicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.pentaS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.pnetagonicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.hexaS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.hexagonicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.heartS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.hearticon);
                updateMe();
            }
        });


        getActivity().findViewById(R.id.diamondS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.diamondicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.staricon);
                updateMe();
            }
        });


        getActivity().findViewById(R.id.triangleS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.triangleicon);
                updateMe();
            }
        });


        getActivity().findViewById(R.id.hourglassS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.hourglassicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.thinkingS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.thinkingicon);
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starsixS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shape = ContextCompat.getDrawable(getContext(), R.drawable.staricon2);
                updateMe();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pog_creator_shape_choice, container, false);
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

    public PogShape getShape(){ return pog; }

    private void updateMe(){
        pog = new PogShape(getActivity(), shape , color);
        ((FrameLayout) getActivity().findViewById(R.id.pogCreatorShapeDisp)).removeAllViews();
        ((FrameLayout) getActivity().findViewById(R.id.pogCreatorShapeDisp)).addView(pog);
    }
}
