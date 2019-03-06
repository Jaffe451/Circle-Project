package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by jaffe on 4/17/2018.
 */

public class Circle extends View {

    private final float x;
    private final float y;
    private final Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius;

    private static int growthRate = 5;
    private static int startRadius = 100;

    private Drawable background;

    public Circle(Context context, float x, float y) {
        super(context);
        this.x = x;
        this.y = y;
        myPaint.setColor(Color.RED);

        this.radius = Circle.startRadius;

        this.background = null;
    }

    public Circle(Context context, float x, float y, Drawable d){
        super (context);

        this.x = x;
        this.y = y;
        myPaint.setColor(Color.RED);

        this.radius = Circle.startRadius;

        this.background = d;

    }

    protected void onDraw(Canvas canvas){
       // Log.i("Andrew", "onDraw Called x, y, r:" + x + ", " + y + ", " + radius);
        super.onDraw(canvas);
        Drawable d;

        if(this.background == null) {
            d = ContextCompat.getDrawable(getContext(), R.drawable.bluepog);
        }else{
            d = background;
        }

        d.setBounds((int)(this.x-this.radius),(int)(this.y-this.radius),(int)(this.x+this.radius),(int)(this.y+this.radius));
        d.draw(canvas);
        //canvas.drawCircle(this.x, this.y, radius, myPaint);
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getRadius(){
        return radius;
    }

    public void grow(){
        radius += Circle.growthRate;
       // Log.i("Andrew", "cirlce.grow() called radius: " + radius);
        postInvalidate();
    }

}
