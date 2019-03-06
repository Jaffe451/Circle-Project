package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pogCreatorAccentChoice.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pogCreatorAccentChoice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pogCreatorAccentChoice extends Fragment {


    private OnFragmentInteractionListener mListener;

    private int color;
    private PogShape shape;
    private ArrayList<AccentShape> accents;

    private static int ac = 0xFFC0C0C0;

    public pogCreatorAccentChoice() {
        // Required empty public constructor

        accents = new ArrayList<AccentShape>();
    }


    public static pogCreatorAccentChoice newInstance(int color, PogShape shape) {
        pogCreatorAccentChoice fragment = new pogCreatorAccentChoice();

        fragment.color = color;
        fragment.shape = shape;

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

        updateMe();

        getActivity().findViewById(R.id.pogCreatorAccentUndo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!accents.isEmpty()){
                    ((FrameLayout) getActivity().findViewById(R.id.pogCreatorAccentDisp)).removeView(accents.get(accents.size()-1));
                    accents.remove(accents.size()-1);
                }
            }
        });

        getActivity().findViewById(R.id.circleAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.circleicon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.circleAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.circleicon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.squareAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.squareicon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.squareAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.squareicon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.heartAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hearticon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.heartAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hearticon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.hexaAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hexagonicon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.hexaAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hexagonicon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.hourglassAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hourglassicon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.hourglassAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.hourglassicon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.pentaAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.pnetagonicon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.pentaAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.pnetagonicon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.staricon), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.staricon), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starsixAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.staricon2), color));
                updateMe();
            }
        });

        getActivity().findViewById(R.id.starsixAW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accents.add(new AccentShape(getActivity(),  getContext().getDrawable(R.drawable.staricon2), pogCreatorAccentChoice.ac));
                updateMe();
            }
        });









    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pog_creator_accent_choice, container, false);
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

    private void updateMe(){
        ((FrameLayout) getActivity().findViewById(R.id.pogCreatorAccentDisp)).removeAllViews();
        ((FrameLayout) getActivity().findViewById(R.id.pogCreatorAccentDisp)).addView(shape);

        if(!accents.isEmpty()) {
            for (AccentShape a : accents) {

                ((FrameLayout) getActivity().findViewById(R.id.pogCreatorAccentDisp)).addView(a);

            }
        }

    }
}
