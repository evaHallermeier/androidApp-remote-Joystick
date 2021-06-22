package com.example.androidapp_remote_joystick;
import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;

import android.graphics.LinearGradient;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.graphics.Paint;
import android.graphics.Color;
import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.BitmapShader;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.MotionEvent;
import android.view.View;
import 	android.graphics.Shader;
import android.graphics.PorterDuff;

public class Joystick extends View implements View.OnTouchListener {

    private Paint bigCircle;
    private Paint littleCircle;
    private final int big_radius;
    private final int little_radius;
    private float x_center;
    private float y_center;
    private float xPosition;
    private float yPosition;
    private Boolean init;
    private final ArrayList<JoystickMoved> joystickMovedArrayList = new ArrayList<>();


    public Joystick(Context context) {
        super(context);
        this.big_radius=280;
        this.little_radius=80;
        init = false;
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.big_radius=280;
        this.little_radius=80;
        init = false;
    }

    public Joystick(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        this.big_radius=280;
        this.little_radius=80;
        init = false;
    }

    //for the view model to implements this and be in touch with the joystick
    public interface JoystickMoved {
        void joystickMoved(double x, double y) throws InterruptedException;
    }

    public void addMoved(JoystickMoved joystickMoved){
        joystickMovedArrayList.add(joystickMoved);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // Canvas myCanvas = this.getHolder().lockCanvas(); //Stuff to draw
        bigCircle = new Paint();
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); // Clear the BG
       bigCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        bigCircle.setColor(getResources().getColor(R.color.lpink));
        bigCircle.setStyle(Paint.Style.FILL_AND_STROKE);

        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
       // bigCircle.setARGB(255, 100, 100, 100); //for test




       // canvas.drawPaint(bigCircle);
        littleCircle = new Paint();
       // littleCircle.setColor(getResources().getColor(R.color.purple_500));
        littleCircle.setShader(new LinearGradient(xPosition, yPosition, xPosition+little_radius, yPosition+little_radius, getResources().getColor(R.color.lpink), Color.WHITE, Shader.TileMode.MIRROR));
        littleCircle.setStyle(Paint.Style.FILL);

       x_center = (getWidth() / 2) + 50;
        y_center = (getHeight() / 2) + 50;
       // x_center = (getWidth() / 2);
        //y_center = (getHeight() / 2);
        System.out.println("getwitdh " + getWidth());
        System.out.println("getHeight() " + getHeight());


        // painting the main circle
        canvas.drawCircle((int) x_center, (int) y_center, big_radius,
                bigCircle);
        // painting the secondary circle
        if(!init){
            canvas.drawCircle((int) x_center, (int) y_center, little_radius,
                    littleCircle);
            init = true;
        }
        else{
            canvas.drawCircle((int) xPosition, (int) yPosition, little_radius,
                    littleCircle);
        }


        setOnTouchListener(this);//for him to be moved
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float valueX, valueY=0;
        if(event.getAction() == MotionEvent.ACTION_UP) {
            xPosition = x_center;
            yPosition = y_center;
        }
        else{
            xPosition = event.getX();
            yPosition = event.getY();
            float distance = (float) (Math.sqrt((Math.pow(xPosition-x_center, 2)) + (Math.pow(yPosition-y_center, 2))));

            if(distance > (big_radius)){
                xPosition = (xPosition - x_center) * (big_radius / distance) + x_center;
                yPosition = (yPosition - y_center) * (big_radius / distance) + y_center;
            }
        }

        invalidate();

        for(JoystickMoved j : joystickMovedArrayList){
            try {
               valueX =0;
                if (xPosition>x_center) {

                    valueX = xPosition-x_center;
                    valueX= valueX/big_radius;
                }
                if (xPosition<x_center) {

                    valueX = x_center-xPosition;
                    valueX= -1 * (valueX/big_radius);
                }
                if (xPosition==x_center) {
                    valueX =0;
                }
                valueY =0; //calculate y value for elevator/aileron/? need to check
                if (yPosition>y_center) {

                    valueY = yPosition-y_center;
                    valueY= -1* (valueY/big_radius);
                }
                if (yPosition<y_center) {

                    valueY = y_center-yPosition;
                    valueY= (valueY/big_radius);
                }
                if (yPosition==y_center) {
                    valueX =0;
                }
                j.joystickMoved(valueX, valueY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    }
