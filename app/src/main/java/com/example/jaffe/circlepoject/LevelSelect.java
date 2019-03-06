package com.example.jaffe.circlepoject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LevelSelect.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LevelSelect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelSelect extends Fragment {


    private int counter;
    private int type; //1 = level select 2 = high scores

    private OnFragmentInteractionListener mListener;

    public LevelSelect() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LevelSelect newInstance(int type) {
        LevelSelect fragment = new LevelSelect();

        fragment.type = type;

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


        //State updaters
        if(type == 1){
            //play pre made levels
            ((Button) getActivity().findViewById(R.id.levelSelectPlayerLevels)).setText("Play my Levels");
        }else if(type == 2){
            //view highscores for premade levels
            ((Button) getActivity().findViewById(R.id.levelSelectPlayerLevels)).setText("Highscores for my levels");
        }


        //button listeners

        //back
        getActivity().findViewById(R.id.back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        //my levels
        getActivity().findViewById(R.id.levelSelectPlayerLevels).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@ToDo
            }
        });

        //levels


        for(counter =  1; counter<=15; counter++) {

            Button newLevel = new Button(getActivity());
            newLevel.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            newLevel.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            newLevel.setText("Level " + Integer.toString(counter) );

            newLevel.setMaxEms(counter);

            newLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("Button","Calling Level Select pressed level " + ((Button )view).getText() );
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction t = fm.beginTransaction();
                    if(type == 1) {
                        t.replace(R.id.base, Player.newInstance(((Button) view).getMaxEms()));
                    }else
                    if(type == 2){
                        t.replace(R.id.base, scoreFragment.newInstance(((Button) view).getMaxEms()));
                    }
                    t.addToBackStack("select");
                    t.commit();
                }
            });

            ((LinearLayout) getActivity().findViewById(R.id.levelList)).addView( newLevel );


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level_select, container, false);
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
