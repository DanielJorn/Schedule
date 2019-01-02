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





float zoom = CustomApplication.getPreferencesManager().getCount("zoom", 1);
        float width = CustomApplication.getPreferencesManager().getCount("width", 1);
        float height = CustomApplication.getPreferencesManager().getCount("height", 1);
        float centerWidth = CustomApplication.getPreferencesManager().getCount("centerWidth", 1);
        float centerHeight = CustomApplication.getPreferencesManager().getCount("centerHeight", 1);

        float division = CustomApplication.getPreferencesManager().getCount("division", 1);
        float a = CustomApplication.getPreferencesManager().getCount("a", 1) / division;
        float b = CustomApplication.getPreferencesManager().getCount("b", 0);
        float c = CustomApplication.getPreferencesManager().getCount("c", 0);
        float stroke = CustomApplication.getPreferencesManager().getCount("stroke", 3);



        float D;

        public float square (float a){
            float squareResult = a * a;
            return squareResult;
        }

        public float findD (float a, float b, float c) {
            float first = square(b);
            float sec = 4 * a * c;
            float third = first - sec;
            return third;
        }




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



        public void clearCanvas(Canvas canvas){
            canvas.drawColor(Color.WHITE);
        }

        @Override
        protected void onDraw(Canvas canvas) {


            canvas.scale(CustomApplication.getPreferencesManager().getCount("zoom", 1),
                    CustomApplication.getPreferencesManager().getCount("zoom", 1));


    canvas.save();
            Paint p = new Paint();
//розмір позначки
            int sizeOfTag = 10;
            //шаг одиничного одргризка
            float absoluteStep = CustomApplication.getPreferencesManager().getCount("step", 30);

            p.setTextSize(20);
            p.setColor(Color.BLACK);
            p.setTextAlign(Paint.Align.CENTER);
            p.setStrokeWidth(stroke);
            canvas.drawPoint(centerWidth, centerHeight, p);
            canvas.drawLine(0, centerHeight, width, centerHeight, p);
            canvas.drawLine(centerWidth, 0, centerWidth, height, p);

//рисує систему координат

            for (float i1 = absoluteStep; i1 + centerWidth < width; i1 += absoluteStep){
                canvas.drawLine(centerWidth + i1, centerHeight, centerWidth + i1, centerHeight - sizeOfTag, p);
                canvas.drawText("" + (int)i1 / (int)absoluteStep, centerWidth + i1, centerHeight +20, p);}

            for (float i1 = absoluteStep; i1 + centerHeight < height; i1 += absoluteStep) {
                canvas.drawLine(centerWidth, centerHeight + i1, centerWidth + sizeOfTag, centerHeight + i1, p);
                canvas.drawText("" +(int) - i1 /(int)absoluteStep, centerWidth - 20, centerHeight + i1 + 6, p); }

            for (float i1 = absoluteStep; i1 > -centerWidth; i1 -= absoluteStep){
                if(i1 < 0){
                    canvas.drawLine(centerWidth + i1, centerHeight, centerWidth + i1, centerHeight - sizeOfTag, p);
                    canvas.drawText("" +(int) i1 / (int)absoluteStep, centerWidth + i1, centerHeight + 20, p);}}

            for (float i1 = absoluteStep; i1 > -centerHeight; i1 -= absoluteStep){
                if (i1 < 0) {
                    canvas.drawLine(centerWidth, centerHeight + i1, centerWidth + sizeOfTag, centerHeight + i1, p);
                    canvas.drawText("" + (int)-i1/(int)absoluteStep, centerWidth - 20, centerHeight + i1 + 6, p);}}

            //шукає х нульове
            D = findD(a, b, c);
            float x0 = -b / (2 *a);
            float y0 = -D / (4 * a);
            p.setStrokeWidth(stroke * 2);
            canvas.drawPoint(centerWidth + x0 * absoluteStep, centerHeight - y0 * absoluteStep, p);
             p.setStrokeWidth(stroke);
            canvas.drawText("X нульове " + x0, 300, 300, p);
            canvas.drawText("Y нульове " + y0, 300, 350, p);
            //рисує параболу
             p.setColor(Color.RED);

            for (double x = -10; x <= 10; x += 0.1){
                double y = a * x * x + b * x + c;
                float startX = (float) centerWidth + ((float) x * absoluteStep);
                float startY = (float) centerHeight + ((float)-y * absoluteStep);
                canvas.drawPoint(startX,  startY, p);
            }




        }}



