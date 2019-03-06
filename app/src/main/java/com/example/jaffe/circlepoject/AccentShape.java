package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class AccentShape extends View {

    private int color;
    private Drawable accent;

    private static int padding = 120;

    public AccentShape(Context context, Drawable d, int color) {
        super(context);

        Log.i("color","New accent created" + d + " " + String.format("0x%08X", color));

        this.color = color;

        this.accent = d;

        accent.mutate().setTint(color);


    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //background.setBounds((canvas.getWidth()/2)-(canvas.getHeight()/2), padding, (canvas.getWidth()/2)+(canvas.getHeight()/2), padding);
        accent.setBounds(500, 220, 1000, 720);

        accent.draw(canvas);

    }

}
