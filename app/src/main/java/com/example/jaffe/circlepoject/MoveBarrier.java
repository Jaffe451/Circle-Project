package com.example.jaffe.circlepoject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MoveBarrier extends View implements OnTouchListener{

    public static int width = 100;
    private static int mainColor = 0xff000000; //barriers are black
    private static int selectedColor = 0xff555555;//less black when selected

    private int angle;//0 is horizontal 90 is vertical range is 0 to 180

    private int x;
    private int y;

    private final Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private boolean selected;


    public MoveBarrier(Context context, int x, int y, int angle) {
        super(context);

        this.x = x;
        this.y = y;
        this.angle = angle;

        selected = false;

    }

    protected void onDraw(Canvas c){
        super.onDraw(c);

        if(selected){
            myPaint.setColor(selectedColor);
        }else{
            myPaint.setColor(mainColor);
        }

        if(angle == 0){
            //horizontal
            c.drawRect(0, y, c.getWidth(), y+width, myPaint);
        }else
        if(angle == 90){
            c.drawRect(x, 0, x+width, c.getHeight(), myPaint);
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {
        switch( e.getAction()){
            case MotionEvent.ACTION_DOWN:
                //clicked on barrier
                //set to selected and make active
                selected = true;
                invalidate();


                int startX = x;
                int startY = y;

                return true;

            case MotionEvent.ACTION_MOVE:
                //moving....



                if(angle == 0){
                    //moving a horizontal bar
                }
                return true;

            case MotionEvent.ACTION_UP:
                //lifed finger

                selected = false;
                invalidate();
        }
        return false;
    }

    public boolean isSelected(){
        return selected;
    }
}
