package example.yuratoxa.schedule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;


    class DrawViewNext extends View {


        public DrawViewNext(Context context)
        {
            super(context);

        }
        public DrawViewNext(Context context, AttributeSet attrs)
        {
            super(context, attrs);

        }
        public DrawViewNext(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            BuildingSchedule buildingSchedule = new BuildingSchedule();
            Paint p = new Paint();

            buildingSchedule.buildCoordinateSystem(canvas, p);

            buildingSchedule.findStartOfParabola(canvas, p);

            buildingSchedule.buildParabola(canvas, p);

            }




        }



