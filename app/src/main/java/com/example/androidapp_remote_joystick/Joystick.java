package com.example.androidapp_remote_joystick;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.Arrays;
import android.view.View;
import java.util.ArrayList;


public class Joystick extends View implements View.OnTouchListener {

    private float x_center;
    private float big_radius;
    private float small_radius;
    private float y_center;
    private float xPosition;
    private float yPosition;
    private final ArrayList<JoystickMoved> joystickMovedArrayList = new ArrayList<>(); //only view model so maybe need to change

    //The three constructor possible for the View
    public Joystick(Context context) {
        super(context);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Joystick(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
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
        super.onDraw(canvas);

         x_center = getWidth() / 2;
        y_center = getHeight() / 2;
        //
        System.out.println("getwitdh " + getWidth());
        System.out.println("getHeight() " + getHeight());
        int[] p = new int[2];
        p = getloc(findViewById(R.id.joystick));
        //System.out.println("p on draw center of joystick  is " + Arrays.toString(p));
       // x_center = p[0];
        //y_center = p[1];
        System.out.println("on draw x is " + x_center);
        System.out.println("on draw y is " + y_center);
        small_radius = findViewById(R.id.joystick).getHeight();
        System.out.println("small radius is " + small_radius);
        setOnTouchListener(this); //need to listen to touch event of joystick
    }

    public static int[] getloc(View view) {
        int[] p = new int[2];
        view.getLocationOnScreen(p);
        return p;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //MotionEvent.ACTION_DOWN: A pressed gesture has started, the motion contains the initial starting location.
       // MotionEvent.ACTION_MOVE : during touch and move event
       // MotionEvent.ACTION_UP : after touch event
        int action = event.getAction(); //get action to know what type of gesture
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("v.getX " + v.getX());
                System.out.println("event.getX() " + event.getX());
                System.out.println("v.getY " + v.getY());
                System.out.println("event.getY() " + event.getY());
                xPosition = v.getX() - event.getX();
                yPosition = v.getY() - event.getY();
                System.out.println("xPosition " + xPosition);
                System.out.println("yPosition " + yPosition);
                break;

            case MotionEvent.ACTION_MOVE:
                float x = (float) (x_center + (event.getX() + xPosition - x_center) * 0.5);
                float y = (float) (y_center + (event.getY() + yPosition - y_center) * 0.5);
                if (x > x_center * 2 + 10) {
                    x = x_center * 2 + 10;
                } else if (x < 0) {
                    x = 0;
                }
                if (y > y_center * 2) {
                    y = y_center * 2;
                } else if (y < 0) {
                    y = 0;
                }

                v.animate()
                        .x(x)
                        .y(y)
                        .setDuration(0)
                        .start();

                for(JoystickMoved j : joystickMovedArrayList) {
                    try {
                        //update the listeners
                        j.joystickMoved(x, y); //notify changes to view model
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                v.animate()
                        .x(x_center)
                        .y(y_center)
                        .setDuration(100)
                        .start();
                for(JoystickMoved j : joystickMovedArrayList) {
                    try {
                        j.joystickMoved(x_center, y_center);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }
        return true;
    }
}
