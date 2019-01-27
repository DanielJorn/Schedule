package example.yuratoxa.schedule;


import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ScheduleActivity extends AppCompatActivity {

    long endTime, time;
    long startTime = System.currentTimeMillis();

    View drawView;
    TranslatingView trView;
    EditText editX;
    PointView pointView;
    BuildingSchedule buildingSchedule;
    TextView textX;
    GraphSurfaceView graphSurfaceView;
    TextView textView;

    String equation = CustomApplication.getPreferencesManager().getStringFromPrefs("equation");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        graphSurfaceView = new GraphSurfaceView(this);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        trView = new TranslatingView();

        textView = findViewById(R.id.textView2);
        editX = findViewById(R.id.editX);
        pointView = findViewById(R.id.pointView);
        textX = findViewById(R.id.textX);

        buildingSchedule = new BuildingSchedule();

        buildingSchedule.seeArgument(editX, pointView, textX, equation);

        endTime = System.currentTimeMillis();
        time = endTime - startTime;
    textView.setText("TIME ACT" + " " + time);
    }

    public void plusZoom(View view) {
        trView.changeZoom(drawView, true);
        pointView.setScaleX(drawView.getScaleX());
        pointView.setScaleY(drawView.getScaleY());
    }

    public void minusZoom(View view) {
        if (drawView.getScaleX() > 0.5)
            trView.changeZoom(drawView, false);
        pointView.setScaleX(drawView.getScaleX());
        pointView.setScaleY(drawView.getScaleY());
    }


   /* public boolean onTouchEvent(MotionEvent event) {
        return trView.onTouch(drawView, event, pointView);
    }
*/




}

/* ScheduleActivity(float coordinatesArray[]){
        equation = CustomApplication.getPreferencesManager().getStringFromPrefs("equation");

        for (float x = -5; x <= 5; x += 0.1) {

            float y = PolishNotation.eval(equation, x);
            float pointX = findPointCoordinates(x, true);
            float pointY = findPointCoordinates(y, false);

            pointF.set(pointX, pointY);
            points.add(pointF);
        }

        for (int i = 0; i < points.size() - 1; i++){
            coordinatesArrayList.add(points.get(i).x);
            coordinatesArrayList.add(points.get(i).y);
        }

    }*/



/*public static float[] convertFloats(ArrayList<Float> floats) {
        float[] coordinates = new float[floats.size()];
        Iterator<Float> iterator = floats.iterator();
        for (int i = 0; i < coordinates.length; i++)
            coordinates[i] = iterator.next().floatValue();
        return coordinates;
    }*/




