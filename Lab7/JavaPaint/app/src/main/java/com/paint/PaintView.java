package com.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PaintView extends SurfaceView implements SurfaceHolder.Callback {

    ArrayList<ObiektDoNarysowania> punkty = new ArrayList<>();
    Paint paint = new Paint();
    private int color= Color.RED;
    private int type =1;

    public PaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        punkty = new ArrayList<RectF>();
//        paint = new Paint();
        //color = Color.RED;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setType(int type) {
        this.type = type;
    }
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public PaintView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
    }

    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
    }

    public boolean onTouchEvent(MotionEvent event) {
        RectF oval = new RectF(event.getX() - 50, event.getY() - 50, event.getX() + 50, event.getY() + 50);
        punkty.add(new ObiektDoNarysowania(color, oval, type));
        invalidate();
        return true;
    }

    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);

        for (ObiektDoNarysowania punkt : punkty) {
            paint.setColor(punkt.kolor);
            switch(punkt.type){
                case 0:
                    canvas.drawOval(punkt.figura, paint);
                    break;
                case 1:
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawOval(punkt.figura, paint);
                    paint.setStyle(Paint.Style.FILL);
                    break;
                case 2:
                    canvas.drawRect(punkt.figura, paint);
                    break;
            }

        }
    }

}