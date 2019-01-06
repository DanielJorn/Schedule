package example.yuratoxa.schedule;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText firstEditText;
    EditText secondEditText;
    EditText thirdEditText;
    EditText divisionNumber;
    EditText step;
    EditText setStroke;
    String error = "Введіть данні";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstEditText = (EditText) findViewById(R.id.firstCof);
        secondEditText = (EditText) findViewById(R.id.secondCof);
        thirdEditText = (EditText) findViewById(R.id.thirdCof);
        divisionNumber = (EditText) findViewById(R.id.divisionNumber);
        setStroke = (EditText) findViewById(R.id.setStroke);
        step = (EditText) findViewById(R.id.step);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float width = size.x;
        float height = size.y;
        float centerWidth = size.x/2;
        float centerHeight = size.y/2;
        CustomApplication.getPreferencesManager().saveCount("width", width);
        CustomApplication.getPreferencesManager().saveCount("height", height);
        CustomApplication.getPreferencesManager().saveCount("centerWidth", centerWidth);
        CustomApplication.getPreferencesManager().saveCount("centerHeight", centerHeight);
    }


    public void buildParabola(View view) {
        Intent intent = new Intent(this, ParabolaActivity.class);

        float firstCof = Float.parseFloat(firstEditText.getText().toString());
        float secondCof = Float.parseFloat(secondEditText.getText().toString());
        float thirdCof =  Float.parseFloat(thirdEditText.getText().toString());


        if (firstCof != 0 & secondCof != 0 & thirdCof != 0 ){
        try {
            CustomApplication.getPreferencesManager().saveCount("a", firstCof);
            CustomApplication.getPreferencesManager().saveCount("b", secondCof);
            CustomApplication.getPreferencesManager().saveCount("c", thirdCof);
            if (!divisionNumber.getText().toString().isEmpty()){
                CustomApplication.getPreferencesManager().saveCount("division", Float.parseFloat(divisionNumber.getText().toString()));
            }
            CustomApplication.getPreferencesManager().saveCount("stroke", Float.parseFloat(setStroke.getText().toString()));
            CustomApplication.getPreferencesManager().saveCount("step", Float.parseFloat(step.getText().toString()));

        }

        catch (Throwable throwable){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }


        startActivity(intent);
    }
    else Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void goToBuildSchedule(View view) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }
}