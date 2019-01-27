package example.yuratoxa.schedule;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class BuildingSchedule {


    float pointX;
    float pointY;
    float y;
    final String TAG = "buildGraph";

    long endTime, time;
    long startTime = System.currentTimeMillis();

    float width = CustomApplication.getPreferencesManager().getCount("width", 480);
    float height = CustomApplication.getPreferencesManager().getCount("height", 720);
    float centerWidth = width / 2;
    float centerHeight = height / 2;
    float stroke = 3;
    int sizeOfTag = 10;
    int absoluteStep = (int) width / 11;
    int index = 0;
    float[] coordinates = new float[4];


    public void buildGraph(String equation, Canvas canvas, Paint p, float offsetX, float offsetY) {

        buildCoordinateSystem(canvas, p, new Paint(), offsetX, offsetY);
        float centerWidth = (width / 2) + offsetX;
        float centerHeight = (height / 2) + offsetY;
        Log.d(TAG, "buildGraph: centerWidth " + centerWidth);

        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        p.setStrokeWidth(stroke);


        for (float x = -5; x <= 5; x += 0.1) {

            if (index != 4) {
                y = PolishNotation.eval(equation, x);

                pointX =  centerWidth + (x * absoluteStep);
                pointY = centerHeight + (-y * absoluteStep);

                coordinates[index] = pointX;
                ++index;
                coordinates[index] = pointY;
                ++index;
                canvas.drawPoint(pointX, pointY, p);
            } else {
                canvas.drawLines(coordinates, p);
                coordinates[0] = coordinates[2];
                coordinates[1] = coordinates[3];
                index = 2;

                y = PolishNotation.eval(equation, x);

                pointX = findPointCoordinates(x, true);
                pointY = findPointCoordinates(y, false);

                coordinates[index] = pointX;
                ++index;
                coordinates[index] = pointY;
                ++index;
                canvas.drawPoint(pointX, pointY, p);
            }
        }
        endTime = System.currentTimeMillis();
        time = endTime - startTime;
        Log.d("TIME", "" + time);
        p.setStrokeWidth(1);
        canvas.drawText("" + time, 100, 100, p);
        p.reset();
    }


    public float findPointCoordinates(float coordinate, boolean isX) {
        if (isX)
            return centerWidth + (coordinate * absoluteStep);
        else
            return centerHeight + (-coordinate * absoluteStep);
    }


    public void buildPoint(String equation, Canvas canvas, Paint p, float x) {
        p.setColor(Color.RED);
        p.setStrokeWidth(stroke * 2f);
        p.setAntiAlias(true);
        p.setTextSize(20);
        float y = PolishNotation.eval(equation, x);
        float startX = findPointCoordinates(x, true);
        float startY = findPointCoordinates(y, false);
        canvas.drawPoint(startX, startY, p);
        String coordinates = "(" + x + ";" + y + ")";
        CustomApplication.getPreferencesManager().saveCount("x", x);
        canvas.drawText(coordinates, startX + 10, startY - 10, p);
    }


    public void buildCoordinateSystem(Canvas canvas, Paint p, Paint paintForCellular, float offsetX, float offsetY) {

        canvas.drawColor(Color.WHITE);
        p.setTextSize(20);
        p.setColor(Color.BLACK);
        p.setTextAlign(Paint.Align.CENTER);
        p.setStrokeWidth(stroke);

        centerWidth += offsetX;
        centerHeight += offsetY;

        paintForCellular.setStrokeWidth(1.5f);
        paintForCellular.setColor(Color.BLUE);

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
        p.reset();
    }


    public void seeArgument(final EditText editText, final PointView pView, final TextView textCoordinates, final String equation) {

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
                if (!argument.isEmpty() & !argument.endsWith(".") & !argument.contains("-")) {
                    float x = Float.parseFloat(argument);
                    CustomApplication.getPreferencesManager().saveCount("x", x);
                    float y = PolishNotation.eval(equation, x);
                    String coordinates = "(" + x + ";" + y + ")";
                    textCoordinates.setText(coordinates);
                    pView.invalidate();
                } else if (argument.contains("-") & !argument.endsWith("-")) {
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



