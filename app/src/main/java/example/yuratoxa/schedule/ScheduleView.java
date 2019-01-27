package example.yuratoxa.schedule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class ScheduleView extends View {

    Paint p = new Paint();
    BuildingSchedule buildingSchedule = new BuildingSchedule();
    //String equation = CustomApplication.getPreferencesManager().getStringFromPrefs("equation");


    long endTime, time;
    long startTime = System.currentTimeMillis();

    Paint paintForCellular = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ScheduleView(Context context) {
        super(context);

    }

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ScheduleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pressedX = event.getX();
                pressedY = event.getY();

            case MotionEvent.ACTION_MOVE:
                movingX = event.getX();
                movingY = event.getY();

        }

    }*/

    /*@Override
    protected void onDraw(Canvas canvas) {


        buildingSchedule.buildCoordinateSystem(canvas, p, paintForCellular);


        buildingSchedule.buildGraph(equation, canvas, p);
        endTime = System.currentTimeMillis();

        time = endTime - startTime;
        canvas.drawText("" + time, 300, 300, p);

    }*/




}
