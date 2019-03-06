package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by jaffe on 4/19/2018.
 */

public class Barrier extends View {

    public static int width = 100;

    private final boolean isHorizontal;

    private final float displacment;

    private final Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public Barrier(Context context, boolean isHor, float displacment) {
        super(context);

        this.displacment = displacment;
        this.isHorizontal = isHor;

        myPaint.setColor(Color.BLACK);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);

        if(isHorizontal){
            //drawing a horizontal bar
            c.drawRect(0, displacment, c.getWidth(), displacment+width, myPaint);
        }

        if(!isHorizontal){
            //drawing a vertical bar
            c.drawRect(displacment, 0, displacment+width, c.getHeight(), myPaint );
        }

    }


}
