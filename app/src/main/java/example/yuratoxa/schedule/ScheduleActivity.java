package example.yuratoxa.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {

    View drawView;
    TranslatingView trView;
    EditText editArgument;
    PointView pView;
    BuildingSchedule buildingSchedule;
    TextView textCoordinates;

    String equation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        drawView = (View)findViewById(R.id.customView1);
        trView = new TranslatingView();

        editArgument = (EditText)findViewById(R.id.editArgument);
        pView = (PointView)findViewById(R.id.pointView);
        textCoordinates = (TextView) findViewById(R.id.textCoordinates);

        buildingSchedule = new BuildingSchedule();

        equation = CustomApplication.getPreferencesManager().getStringFromPrefs("equation");

        buildingSchedule.seeArgument(editArgument, pView, textCoordinates, equation);

    }

    public void plusZoom(View view) {
        trView.changeZoom(drawView, true);
        pView.setScaleX(drawView.getScaleX());
        pView.setScaleY(drawView.getScaleY());
    }

    public void minusZoom(View view){
        if (drawView.getScaleX() > 0.5)
            trView.changeZoom(drawView, false);
        pView.setScaleX(drawView.getScaleX());
        pView.setScaleY(drawView.getScaleY());
    }


    public boolean onTouchEvent(MotionEvent event) {
        return trView.onTouch(drawView, event, pView);
    }
}


