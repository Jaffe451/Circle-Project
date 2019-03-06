package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PogCreatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PogCreatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PogCreatorFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private int progres;

    private int color;
    private PogShape shape;
    private int accents;

    private pogCreaterColorChoice colorChooser;
    private pogCreatorShapeChoice shapeChooser;
    private pogCreatorAccentChoice accentChooser;

    public PogCreatorFragment() {
        // Required empty public constructor

        progres = 1;
    }


    // TODO: Rename and change types and number of parameters
    public static PogCreatorFragment newInstance() {
        PogCreatorFragment fragment = new PogCreatorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        colorChooser = new pogCreaterColorChoice();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction t = fm.beginTransaction();
        t.add(R.id.creatorDisp, colorChooser);
        t.commit();

        color = colorChooser.getColor();


    }

    public void onResume(){
        super.onResume();




        //button listeners

        //backToMM
        getActivity().findViewById(R.id.pogBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progres > 1){
                    getFragmentManager().popBackStack("color", 1);
                    color = 0xffcc0000; //reset internal color to color choice fragmetn default
                              //reset shape to default
                              //reset accents to none
                }
                getFragmentManager().popBackStack("main", 1);
            }
        });

        //reset
        getActivity().findViewById(R.id.pogSelectClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progres > 1){
                    getFragmentManager().popBackStack("color", 1);
                    ((ProgressBar) getActivity().findViewById(R.id.pogProgress)).setProgress(28);

                }
            }
        });

        //next
        getActivity().findViewById(R.id.pogStepNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                if( progres == 1 ){
                    //currently chosing color goto choosing shape

                    color = colorChooser.getColor();//get then color
                    Log.i("color", "color selected as " + String.format("0x%08X", color));
                    shapeChooser = pogCreatorShapeChoice.newInstance(color);//create new fragment

                    FragmentTransaction t1 = fm.beginTransaction();
                    t1.replace(R.id.creatorDisp,shapeChooser);
                    t1.addToBackStack("color");
                    t1.commit();

                    ((ProgressBar) getActivity().findViewById(R.id.pogProgress)).setProgress(53);//update progress bar
                    ((Button) getActivity().findViewById(R.id.pogStepBack)).setTextColor(0xFF000000);//ungreyout back button
                    progres = 2;
                    return;

                }
                else
                if( progres == 2){
                    //currently chosing shape goto choosing accents

                    shape = new PogShape(shapeChooser.getShape());
                    Log.i("color","entering pogCreatorAccentChoice fragment with " + String.format("0x%08X", color) + " " + shape);
                    accentChooser = pogCreatorAccentChoice.newInstance(color, shape);

                    FragmentTransaction t2 = fm.beginTransaction();
                    t2.replace(R.id.creatorDisp, accentChooser);
                    t2.addToBackStack("shape");
                    t2.commit();

                    ((ProgressBar) getActivity().findViewById(R.id.pogProgress)).setProgress(100);
                    progres = 3;
                    ((Button) view).setText("Finish");
                    return;
                }
                else
                if( progres == 3){
                    //currently choosing accents and hit finished
                    //@todo save pog as play piece
                    fm.popBackStack("color", 1);
                }

            }
        });

        //back
        getActivity().findViewById(R.id.pogStepBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                if( progres == 1 ){
                    //currently on color going nowhere
                    return;
                }else
                if(progres == 2){
                    //curently on shape going to color
                    ((Button) view).setTextColor(0xFFaaa);//grey out back button
                    fm.popBackStack("color", 1);
                    ((ProgressBar) getActivity().findViewById(R.id.pogProgress)).setProgress(28);
                    progres = 1;
                    return;


                }else
                if(progres == 3){
                    //currently on accent going to shape

                    ((Button) getActivity().findViewById(R.id.pogStepNext)).setText("Next");
                    fm.popBackStack("shape", 1);
                    ((ProgressBar) getActivity().findViewById(R.id.pogProgress)).setProgress(53);
                    progres = 2;
                    return;

                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pog_creator, container, false);
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
}
