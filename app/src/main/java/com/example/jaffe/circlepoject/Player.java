package com.example.jaffe.circlepoject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Player.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Player#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Player extends Fragment implements View.OnTouchListener {


    private FrameLayout display;
    private Random rand;

    private ArrayList<Float> horizontalStops = new ArrayList<Float>(); //Array contaning the start and stop points of all vertical barriers
    private ArrayList<Float> verticalStops = new ArrayList<Float>(); //Array contaning the start and stop points of all horizontal barriers
    private int[] collisions;

    private OnFragmentInteractionListener mListener;

    private boolean threadRunning;
    private Circle circle;
    private Drawable pog;

    private int score;
    private int level;


    public Player() {


        rand = new Random();
        score = 0;
        level = 0;

    }




    public static Player newInstance(int i) {
        Player fragment = new Player();
        fragment.level = i;

        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }


    public void onResume() {
        super.onResume();

        display = getActivity().findViewById(R.id.display);


        display.setOnTouchListener(this);

        redrawThread pen = new redrawThread(5);
        pen.start();



        getActivity().findViewById(R.id.horBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeHBar();
            }
        });

        getActivity().findViewById(R.id.verBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeVBar();
            }
        });

        getActivity().findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.removeAllViews();
                circle = null;
                horizontalStops.clear();
                verticalStops.clear();

                ((TextView) getActivity().findViewById(R.id.score)).setText("Score: 0");
            }
        });

        getActivity().findViewById(R.id.HighScores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load high scores fragmetn for current level
            }
        });

        getActivity().findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player.daoThread daot = new Player.daoThread(0);//0 is submit
                daot.start();
            }
        });

        getActivity().findViewById(R.id.levels).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack("select",1);
            }
        });

        getActivity().findViewById(R.id.playToMM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                int count = fm.getBackStackEntryCount();
                for(int i = 0; i < count; ++i) {
                    fm.popBackStack();
                }

            }
        });

        getActivity().findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level > 1){
                    level--;
                    clear(display);
                    drawLevel();
                    TextView scoreDisp = getActivity().findViewById(R.id.score);

                    scoreDisp.setText("Score: " + 0);
                }
            }
        });

        getActivity().findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level <= 15){
                    level++;
                    clear(display);
                    drawLevel();

                    TextView scoreDisp = getActivity().findViewById(R.id.score);

                    scoreDisp.setText("Score: " + 0);
                }
            }
        });

        getActivity().findViewById(R.id.HighScores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction t = fm.beginTransaction();
                t.replace(R.id.base, scoreFragment.newInstance(level) );
                t.addToBackStack(null);
                t.commit();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false);
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

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //Log.i("Andrew", "onTouch called: " + event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN :

                if(circle != null){
                    display.removeView(circle);
                    ((TextView) getActivity().findViewById(R.id.score)).setText("Score: 0");
                } else {
                    //this is the first circle drawn
                    //initialize edge barriers
                    horizontalStops.add((float) 0);//left edge
                    horizontalStops.add((float) display.getWidth());//right edge

                    verticalStops.add((float) 0);//top edge
                    verticalStops.add((float) display.getHeight());//bottom edge
                }
                if(pog != null) {
                    circle = new Circle(getActivity(), event.getX(), event.getY(), pog);
                }else{
                    circle = new Circle(getActivity(), event.getX(), event.getY());
                }
                display.addView(circle);


                collisions = findCollisions(((int) event.getX()),((int) event.getY() ));



                threadRunning = true;
                Player.circleThread cThread = new Player.circleThread(circle);
                cThread.start();


                return true;
            case MotionEvent.ACTION_UP :

                if(threadRunning){
                    setScore(circle);
                }
                threadRunning = false;


                return true;

        }
        return false;

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

    private int[] findCollisions(int x, int y){
        //After writing this function and fully testing it I have realized that All i need to return is the closest barrier to the center of the circle
        //Due to time constraints I have not updated the code for this function
        // TODO: 5/1/2018 See Comments Above

        //Finds the top right bottom and left barriers closest to the given cordinates and returns them in an array in that order
        int[] barriers = new int[4];

        float lessX = 0, greaterX = display.getWidth(), lessY = 0, greaterY = display.getHeight();

        //find the left and right stops in horizontal stops
        for(float bar: horizontalStops) {
            //find the left bound
            if ((bar < x) && (bar >= lessX)) {
                //bar is left of x and right of lessX
                lessX = bar;
            }

            //find the right bound
            if ((bar > x) && (bar <= greaterX)) {
                //bar is right of x and left of greaterX
                greaterX = bar;
            }
        }

        for(float bar: verticalStops){
            //find top bound
            if( (bar < y) && (bar >= lessY ) ){
                //bar is above y and below lessY
                lessY = bar;
            }

            //find bottom bound
            if( (bar > y) && (bar <= greaterY ) ){
                //bar is below y and above greaterY
                greaterY = bar;
            }
        }
        //barriers is top rght bottom left in order
        barriers[0] = (int)lessY; //top barrier closest value less than y coordinate
        barriers[1] = (int)greaterX; //right barrier closest value greater than x coordinate
        barriers[2] = (int)greaterY; //bottom barrier closest value greater than y coordinate
        barriers[3] = (int)lessX; //left barrier closest value less than x coordinate

        Log.i("Andrew", "Returning barrires for circle centered at " + x +","+y +" barriers top,right,bottom,left: " + Arrays.toString(barriers));
        return barriers;
    }

    private boolean checkCollisions(Circle checkC, int[] barriers){
        //returns true if a colision is detected

        int x = (int)checkC.getX();
        int y = (int)checkC.getY();
        int r = (int)checkC.getRadius();

        //barriers is in order top right bottom left (clockwise)

        //check top collision
        if( ( y - r ) <= barriers[0] ){
            Log.i("Collision", "Top collision circle at " + x + "," + y + " radius: " + r + " top barrier at " + barriers[0] );
            return true;
        }

        //check right collision
        if( (x + r) >= barriers[1] ){
            Log.i("Collision", "Right collision circle at " + x + "," + y + " radius: " + r + " right barrier at " + barriers[1] );
            return true;
        }

        //check bottom collision
        if( (y + r) >= barriers[2] ){
            Log.i("Collision", "Bottom collision circle at " + x + "," + y + " radius: " + r + " bottom barrier at " + barriers[2] );
            return true;
        }

        //check left collision
        if( (x - r) <= barriers[3] ){
            Log.i("Collision", "Left collision circle at " + x + "," + y + " radius: " + r + " left barrier at " + barriers[3] );
            return true;
        }

        return false;
    }

    public void clear(View v){
        ((FrameLayout)v).removeAllViews();
        circle = null;
        horizontalStops.clear();
        verticalStops.clear();
    }

    private void makeVBar(){
        float displacment;


        displacment = (rand.nextFloat() * (display.getWidth() - 100 )) + 50;
        Log.i("Andrew", "Making vertical Barrier " + displacment);
        Barrier vertBarrier = new Barrier(getActivity(), false, displacment);

        display.addView(vertBarrier);

        horizontalStops.add(displacment);
        horizontalStops.add(displacment+Barrier.width);
    }

    private void makeHBar(){

        float displacment;

        displacment = (rand.nextFloat() * (display.getHeight()- 150)) + 50; // reduce max height by 150 so the barrier doesn't spawn at the bottom
        Log.i("Andrew", "Making horizontal Barrier with displacment " + displacment);
        Barrier horBarrier = new Barrier(getActivity(), true, displacment);

        display.addView(horBarrier);

        verticalStops.add(displacment);
        verticalStops.add(displacment+Barrier.width);
    }

    private void drawLevel(){

        Button levelSelector = getActivity().findViewById(R.id.levels);
        levelSelector.setText("Level: " + Integer.toString(level) );

        if(level < 0){
            //opening player in level creator
            getActivity().findViewById(R.id.pScoreMenu).setVisibility(View.GONE);
            getActivity().findViewById(R.id.prefs).setVisibility(View.GONE);
        }else{
            //standard call
            getActivity().findViewById(R.id.pScoreMenu).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.prefs).setVisibility(View.VISIBLE);
        }

        switch( level ){
            case -1:
                clear(display);
                return;

            case 1:

                rand.setSeed(101010);
                clear(display);
                makeHBar();
                return;
            case 2:

                rand.setSeed(123456);

                clear(display);
                makeHBar();
                makeVBar();
                return;

            case 3:
                rand.setSeed(55555);
                clear(display);
                makeHBar();
                makeVBar();
                makeHBar();
                return;

            case 4:
                rand.setSeed(481516);

                clear(display);
                makeHBar();
                makeHBar();
                makeVBar();
                makeVBar();
                return;
            case 5:

                rand.setSeed(2342);

                clear(display);
                makeHBar();
                makeHBar();
                makeHBar();
                makeVBar();
                makeVBar();
                return;

            default:
                rand.setSeed(level);
                clear(display);
                makeHBar();
                makeHBar();
                makeVBar();
                makeVBar();
                return;
        }
    }

    private void setScore(Circle c){
        this.score = (int)(c.getRadius() - (c.getRadius()%10));

        String sScore = Integer.toString(this.score);
        TextView scoreDisp = getActivity().findViewById(R.id.score);

        scoreDisp.setText("Score: " + sScore);
    }


    class circleThread extends Thread {

        Circle circle;


        circleThread(Circle circle) {

            this.circle = circle;
        }

        public void run() {

            while(threadRunning) {
                circle.grow();

                if( checkCollisions(circle,collisions ) ){
                    //check collisions will return true if a collision occurs
                    threadRunning = false;
                    break;
                }
                //Log.i("Andrew", "circle stuff: " + circle.getX() + "," + circle.getY() + " r: " + circle.getRadius() );


                try {
                    sleep(35);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return;
        }
    }


    private class daoThread extends Thread {

        private int type;

        daoThread(int t) {
            this.type = t;
        }

        @Override
        public void run() {

            AppDatabase instance = AppDatabase.getDatabase(getActivity());
            ScoresDao dao = instance.scoresDao();

            if(this.type == 0) {
                //submit type thread;
                if(Player.this.score > 0 && Player.this.level > 0) {
                    scores submit = new scores();

                    submit.setUser("Andrew");
                    submit.setScore(Player.this.score);
                    submit.setLevel(Player.this.level);

                    dao.insertAll(submit);
                }
                Player.this.score = 0; //set score to 0 so the score cannot be resubmitted
            }


        }
    }


    private class redrawThread extends Thread{
        private int wait;

        redrawThread(int w){
            this.wait = w;
        }

        public void run(){

            try {
                sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            getActivity().runOnUiThread(new Runnable(){
                public void run(){
                    drawLevel();
                }


            });


        }
    }
}
