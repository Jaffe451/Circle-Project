package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link scoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link scoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class scoreFragment extends Fragment {


    private int type; //0 is based on level 1 is based on user

    private int level;
    private String name;

    private TableLayout scoresTable;

    private int rowCount;

    private OnFragmentInteractionListener mListener;

    public scoreFragment() {
        // Required empty public constructor
        this.rowCount = 0;
    }

    public void setType(int t){
        //if t is 0 this displays scores based on level
        //if t is 1 this displays scores based on user
        this.type = t;
    }

    public void setlevel(int n){
        this.level = n;
    }

    public void setName(String n){
        this.name = n;
    }


    // TODO: Rename and change types and number of parameters
    public static scoreFragment newInstance(int level) {
        scoreFragment fragment = new scoreFragment();

        fragment.level = level;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public void onResume(){
        super.onResume();

        update(level);

        getActivity().findViewById(R.id.hsPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level > 1){
                    update(level-1);
                }
            }
        });

        getActivity().findViewById(R.id.hsNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level <= 15){
                    update(level +1 );
                }
            }
        });

        getActivity().findViewById(R.id.hsToMM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                int count = fm.getBackStackEntryCount();
                for(int i = 0; i < count; ++i) {
                    fm.popBackStack();
                }
            }
        });

        getActivity().findViewById(R.id.hsPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction t = fm.beginTransaction();
                t.replace(R.id.base, Player.newInstance(level) );
                t.addToBackStack(null);
                t.commit();
            }
        });

        getActivity().findViewById(R.id.hsLevels).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack("select", 1);
            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    public void onPause(){
        super.onPause();
        if(rowCount > 0){
            ((TableLayout)getActivity().findViewById(R.id.scoreTable)).removeViews(2,this.rowCount);
            rowCount = 0;
        }

    }

    public void update(int level){
        this.type = 0;
        this.level=level;

        Log.i("rowCount","calling update with rowCount:" + rowCount);
        if(rowCount > 0){
            ((TableLayout)getActivity().findViewById(R.id.scoreTable)).removeViews(2,this.rowCount);
            rowCount = 0;
        }

        daoThread daot = new daoThread();

        daot.start();
    }

    public void update(String name){
        //name searching not yet supported

        return;
    }

    private TableRow makeRow(scores s){
        TableRow newrow = new TableRow(getActivity());

        if(this.type == 0){
            //level row
            TextView name = new TextView(getActivity());
            name.setText(s.getUser());
            name.setGravity(Gravity.CENTER);

            newrow.addView(name);

            TextView score = new TextView(getActivity());
            score.setText(Integer.toString(s.getScore()));
            score.setGravity(Gravity.CENTER);

            newrow.addView(score);
        }

        this.rowCount++;

        return newrow;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
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

    private class daoThread extends Thread{

        private List<scores> results;

        public daoThread(){

        }

        public void run(){

            AppDatabase instance = AppDatabase.getDatabase(getActivity());
            ScoresDao dao = instance.scoresDao();


            results = dao.loadLevel(scoreFragment.this.level);
            Log.i("runOnUiThread", "level: " + scoreFragment.this.level + " results " + results.size() );


            if(type == 0){
                //level based high scores

                getActivity().runOnUiThread(new Runnable(){
                    public void run(){
                        scoresTable = getActivity().findViewById(R.id.scoreTable);

                        ((TextView)getActivity().findViewById(R.id.tableHeader)).setText("Level: " + Integer.toString(scoreFragment.this.level) );
                        TextView scoretype = ((TextView)getActivity().findViewById(R.id.scoreType));
                        scoretype.setText("Name");
                        scoretype.setGravity(Gravity.CENTER);

                        for(scores s: daoThread.this.results){
                            scoresTable.addView(makeRow(s));
                        }

                    }
                });

            }
        }
    }
}
