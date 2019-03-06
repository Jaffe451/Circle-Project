package com.example.jaffe.circlepoject;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

public class PogShape extends View {

    private Drawable background;
    private int color;

    private static int padding = 40;


    public PogShape(Context c, Drawable b, int color){
        super(c);

        background = b;
        this.color = color;
        
        background.mutate().setTint(color);

    }

    public PogShape(PogShape p){
        super(p.getContext());

        background = p.background;
        color = p.color;

        background.mutate().setTint(color);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);



        //background.setBounds((canvas.getWidth()/2)-(canvas.getHeight()/2), padding, (canvas.getWidth()/2)+(canvas.getHeight()/2), padding);
        background.setBounds(380, 100, 1120, 840);

        background.draw(canvas);

    }


}
