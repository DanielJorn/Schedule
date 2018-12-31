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

    EditText firstCof;
    EditText secondCof;
    EditText thirdCof;
    EditText divisionNumber;
    EditText step;
    EditText setStroke;
    String error = "Введіть данні";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstCof = (EditText) findViewById(R.id.firstCof);
        secondCof = (EditText) findViewById(R.id.secondCof);
        thirdCof = (EditText) findViewById(R.id.thirdCof);
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
        Intent intent = new Intent(this, Parabola.class);
        startActivity(intent);
        try {
            CustomApplication.getPreferencesManager().saveCount("a", Float.parseFloat(firstCof.getText().toString()));
            CustomApplication.getPreferencesManager().saveCount("b", Float.parseFloat(secondCof.getText().toString()));
            CustomApplication.getPreferencesManager().saveCount("c", Float.parseFloat(thirdCof.getText().toString()));
            if (!divisionNumber.getText().toString().isEmpty()){
                CustomApplication.getPreferencesManager().saveCount("division", Float.parseFloat(divisionNumber.getText().toString()));
            }
            CustomApplication.getPreferencesManager().saveCount("stroke", Float.parseFloat(setStroke.getText().toString()));
            CustomApplication.getPreferencesManager().saveCount("step", Float.parseFloat(step.getText().toString()));

        }
        catch (Throwable throwable){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

        /*CustomApplication.getPreferencesManager().saveCount("a", a);
        CustomApplication.getPreferencesManager().saveCount("b", b);
        CustomApplication.getPreferencesManager().saveCount("c", c);*/
    }

}