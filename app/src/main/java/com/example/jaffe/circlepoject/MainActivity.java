package com.example.jaffe.circlepoject;


import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener,
        scoreFragment.OnFragmentInteractionListener, Player.OnFragmentInteractionListener, MainMenuFragment.OnFragmentInteractionListener,
        LevelSelect.OnFragmentInteractionListener, LevelCeator.OnFragmentInteractionListener, PogCreatorFragment.OnFragmentInteractionListener,
        pogCreaterColorChoice.OnFragmentInteractionListener, pogCreatorAccentChoice.OnFragmentInteractionListener, pogCreatorShapeChoice.OnFragmentInteractionListener
        {




    private Boolean highScoreFrag;
    private scoreFragment scoreFragment;

    private MenuItem current;

    private Drawable background;

    private int level;
    private int score;
    private int pogShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.base, new MainMenuFragment()).commit();

    }

    public void onBackPressed(){

        super.onBackPressed();

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return onOptionsItemSelected(menuItem);
    }

    public void close(View v){

        onBackPressed();
    }



    public void levelSelect(View v){
        PopupMenu menu = new PopupMenu(this, findViewById(R.id.levels));
        menu.getMenuInflater().inflate(R.menu.level_select, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
        menu.show();
    }

    public void pogSelect(View v){
        PopupMenu menu = new PopupMenu(this, findViewById(R.id.hsPreferences));
        menu.getMenuInflater().inflate(R.menu.preferences, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem m){
                switch(m.getItemId()){
                    case R.id.setBlue:
                        background = ContextCompat.getDrawable(MainActivity.this, R.drawable.bluepog);
                        break;
                    case R.id.setRed:
                        background = ContextCompat.getDrawable(MainActivity.this, R.drawable.redpog);
                        break;
                    case R.id.setGreen:
                        background = ContextCompat.getDrawable(MainActivity.this, R.drawable.greenpog);
                        break;
                }
                return true;
            }
        });
        menu.show();
    }

    public void submit(View v){




    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.level_select, menu);
        return true;
    }*/





    @Override
    public void onFragmentInteraction(Uri uri) {

    }

/*
    public boolean onOptionsItemSelected(MenuItem m){

        this.current = m;

        switch( m.getItemId() ){
            case R.id.level1:
                this.level = 1;
                rand.setSeed(101010);
                if(!highScoreFrag) {
                    clear(display);
                    makeHBar(display);
                    return true;
                }else{
                    //highScoreFrag == True
                    scoreFragment.update(this.level);
                    return true;
                }
            case R.id.level2:
                this.level = 2;
                rand.setSeed(123456);
                if(!highScoreFrag){
                    clear(display);
                    makeHBar(display);
                    makeVBar(display);
                    return true;
                }else{
                    //highScoreFrag == True
                    scoreFragment.update(this.level);
                    return true;
                }
            case R.id.level3:
                this.level = 3;
                rand.setSeed(55555);
                if(!highScoreFrag){
                    clear(display);
                    makeHBar(display);
                    makeVBar(display);
                    makeHBar(display);
                    return true;
                }else{
                    //highScoreFrag == True
                    scoreFragment.update(this.level);
                    return true;
                }
            case R.id.level4:
                this.level = 4;
                rand.setSeed(481516);
                if(!highScoreFrag){
                    clear(display);
                    makeHBar(display);
                    makeHBar(display);
                    makeVBar(display);
                    makeVBar(display);
                    return true;
                }else{
                    //highScoreFrag == True
                    scoreFragment.update(this.level);
                    return true;
                }
            case R.id.level5:
                this.level = 5;
                rand.setSeed(2342);
                if(!highScoreFrag){
                    clear(display);
                    makeHBar(display);
                    makeHBar(display);
                    makeHBar(display);
                    makeVBar(display);
                    makeVBar(display);
                    return true;
                }else{
                    //highScoreFrag == True
                    scoreFragment.update(this.level);
                    return true;
                }
        }


        return super.onOptionsItemSelected(m);
    }*/

}
