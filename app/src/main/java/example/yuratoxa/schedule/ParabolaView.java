package example.yuratoxa.schedule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.math.RoundingMode;
import java.text.DecimalFormat;


class ParabolaView extends View {

        float eventX = 0;
        float eventY = 0;
        Paint paint;
        Path path;
        float absoluteStep = CustomApplication.getPreferencesManager().getCount("absoluteStep", 30);
        float centerWidth = CustomApplication.getPreferencesManager().getCount("centerWidth", 1);
        float centerHeight = CustomApplication.getPreferencesManager().getCount("centerHeight", 1);

        public ParabolaView(Context context)
        {
            super(context);

        }
        public ParabolaView(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            path = new Path();
            paint = new Paint();
            paint.setStrokeWidth(20);
        }
        public ParabolaView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            BuildingSchedule buildingSchedule = new BuildingSchedule();
            Paint p = new Paint();
            Paint paintForPoint = new Paint();
            paintForPoint.setColor(getResources().getColor(R.color.colorViolet));
            paintForPoint.setStrokeWidth(2);

            buildingSchedule.buildCoordinateSystem(canvas, p);
            buildingSchedule.buildParabola(canvas, p);

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            super.onDraw(canvas);
            canvas.drawPoint(eventX, eventY, paintForPoint);
            float pointCoordX = (eventX - centerWidth)/absoluteStep;
            float pointCoordY = -((eventY - centerHeight)/absoluteStep);
            String roundedPointCoordX = df.format(pointCoordX);
            String roundedPointCoordY = df.format(pointCoordY);
            canvas.drawText("(" + roundedPointCoordX + ";" + roundedPointCoordY + ")", eventX + 15, eventY - 15, paintForPoint);

            }

        public boolean onTouchEvent(MotionEvent event){
            if (event.getAction() == MotionEvent.ACTION_DOWN
                    | event.getAction() == MotionEvent.ACTION_MOVE){
                Log.d("ACTION DOWN ", "ACTION");
                eventX = event.getX();
                eventY = event.getY();
                invalidate();
            }
            return true;
        }




        }



