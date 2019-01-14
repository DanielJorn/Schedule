package example.yuratoxa.schedule;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

public class BuildingSchedule {

    float width = CustomApplication.getPreferencesManager().getCount("width", 1);
    float height = CustomApplication.getPreferencesManager().getCount("height", 1);
    float centerWidth = CustomApplication.getPreferencesManager().getCount("centerWidth", 1);
    float centerHeight = CustomApplication.getPreferencesManager().getCount("centerHeight", 1);
    float stroke = CustomApplication.getPreferencesManager().getCount("stroke", 3);
    int sizeOfTag = 10;
    float absoluteStep = 30;

    float division = CustomApplication.getPreferencesManager().getCount("division", 1);
    float a = CustomApplication.getPreferencesManager().getCount("a", 1) / division;
    float b = CustomApplication.getPreferencesManager().getCount("b", 0);
    float c = CustomApplication.getPreferencesManager().getCount("c", 0);
    float D;
    int indexOfArray = -1;
    int countOfIterations = 0;
    int offset = 0;



    float points [] = new float[1000];


    public float findPointCoordinates (float coordinate, boolean isX){
        if(isX)
            return centerWidth + (coordinate * absoluteStep);
        else
            return centerHeight + (-coordinate * absoluteStep);
    }

    public void buildPoints(float startX, float startY, Canvas canvas, Paint p){

            canvas.drawPoint(startX, startY, p);
            indexOfArray += 1;
            points[indexOfArray] = startX;
            indexOfArray += 1;
            points[indexOfArray] = startY;
            countOfIterations += 1;

            if (countOfIterations >= 2){
                canvas.drawLines(points, offset, 4, p);
                offset += 2;
            }
    }

    public float square (float a){
        return a * a;
    }

    public float findD (float a, float b, float c) {
        float first = square(b);
        float sec = 4 * a * c;
        return first - sec;
    }

    public void findStartOfParabola(Canvas canvas, Paint p) {
        //шукає х нульове
        float D;

        D = findD(a, b, c);

        float x0 = -b / (2 * a);
        float y0 = -D / (4 * a);
        p.setStrokeWidth(stroke);
        p.setColor(Color.BLUE);
        canvas.drawPoint(centerWidth +x0 *absoluteStep,centerHeight -y0 *absoluteStep,p);
        p.setStrokeWidth(stroke);
        p.setColor(Color.BLACK);
        canvas.drawText("X нульове "+x0,100,100,p);
        canvas.drawText("Y нульове "+y0,100,150,p);

    }

    public void buildCoordinateSystem(Canvas canvas, Paint p) {


       Paint paintForCellular = new Paint(Paint.ANTI_ALIAS_FLAG);
       paintForCellular.setStrokeWidth(1.5f);
       paintForCellular.setColor(Color.BLUE);

        p.setTextSize(20);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.CENTER);
        p.setStrokeWidth(stroke);
        canvas.drawPoint(centerWidth, centerHeight, p);
        canvas.drawLine(0, centerHeight, width, centerHeight, p);
        canvas.drawLine(centerWidth, 0, centerWidth, height, p);

//рисує систему координат

        for (float i1 = absoluteStep; i1 + centerWidth < width; i1 += absoluteStep) {//справа від осі ігриків
            canvas.drawLine(centerWidth + i1, centerHeight, centerWidth + i1, centerHeight - sizeOfTag, p);
            canvas.drawLine(centerWidth + i1, height, centerWidth + i1, 0, paintForCellular);
            canvas.drawText("" + (int) i1 / (int) absoluteStep, centerWidth + i1, centerHeight + 20, p);
        }

        for (float i1 = absoluteStep; i1 > -centerWidth; i1 -= absoluteStep) {//зліва від осі ігриків
            if (i1 < 0) {
                canvas.drawLine(centerWidth + i1, centerHeight, centerWidth + i1, centerHeight - sizeOfTag, p);
                canvas.drawText("" + (int) i1 / (int) absoluteStep, centerWidth + i1, centerHeight + 20, p);
                canvas.drawLine(centerWidth + i1, height, centerWidth + i1, 0, paintForCellular);
            }
        }

        for (float i1 = absoluteStep; i1 + centerHeight < height; i1 += absoluteStep) { //під оссю іксів
            canvas.drawLine(centerWidth, centerHeight + i1, centerWidth + sizeOfTag, centerHeight + i1, p);
            canvas.drawText("-" + (int) i1 / (int) absoluteStep, centerWidth - 20, centerHeight + i1 + 6, p);
            canvas.drawLine(width, centerHeight + i1, 0, centerHeight + i1, paintForCellular);
        }


        for (float i1 = absoluteStep; i1 > -centerHeight; i1 -= absoluteStep) {//над віссю іксів
            if (i1 < 0) {
                canvas.drawLine(centerWidth, centerHeight + i1, centerWidth + sizeOfTag, centerHeight + i1, p);
                canvas.drawText("" + (int) -i1 / (int) absoluteStep, centerWidth - 20, centerHeight + i1 + 6, p);
                canvas.drawLine(width, centerHeight + i1, 0, centerHeight + i1, paintForCellular);
            }
        }

    }


//строє параболу

    public void buildParabola(Canvas canvas, Paint p){
        findStartOfParabola(canvas, p); //шукаю початок параболи
        p.setColor(Color.RED);
        p.setStrokeWidth(stroke/2);
        float x0 = -b / (2 * a);
        for (float x = x0 - 10; x <=10 + x0; x += 0.1) {

            float y = a * x * x + b * x + c;
            float startX = findPointCoordinates(x, true);
            float startY = findPointCoordinates(y, false);

            buildPoints(startX, startY, canvas, p);

            }


    }


    public void buildSchedule(String equation, Canvas canvas, Paint p) {
        p.setColor(Color.RED);
        p.setStrokeWidth(stroke / 2);
        for (float x = -10; x <= 10.1; x += 0.1) {
            float y = PolishNotation.eval(equation, x);
           float startX = findPointCoordinates(x, true);
           float startY =  findPointCoordinates(y, false);
           buildPoints(startX, startY, canvas, p);
        }
    }

    public void buildPoint(String equation, Canvas canvas, Paint p, float x){
        p.setColor(Color.RED);
        p.setStrokeWidth(stroke * 2f);
        p.setAntiAlias(true);
        p.setTextSize(20);
        float y = PolishNotation.eval(equation, x);
        float startX = findPointCoordinates(x, true);
        float startY =  findPointCoordinates(y, false);
        canvas.drawPoint(startX, startY, p);
        String coordinates = "(" + x + ";" + y + ")";
        CustomApplication.getPreferencesManager().saveCount("x", x);
        canvas.drawText(coordinates, startX + 10, startY - 10, p);

    }

    public void seeArgument(final EditText editText, final PointView pView, final TextView textCoordinates, final String equation){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String argument = editText.getText().toString();
                if (!argument.isEmpty() & !argument.endsWith(".")) {
                    float x = Float.parseFloat(argument);
                    CustomApplication.getPreferencesManager().saveCount("x", x);
                    float y = PolishNotation.eval(equation, x);
                    String coordinates = "(" + x + ";" + y + ")";
                    textCoordinates.setText(coordinates);
                    pView.invalidate();

                }
                }
            });
        }
    }



