package example.yuratoxa.schedule;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity {

    float width;
    float height;
    float centerWidth;
    float centerHeight;
    TextView lastEquationTextView1;
    TextView lastEquationTextView2;
    TextView lastEquationTextView3;
    String lastEquationString1;
    String lastEquationString2;
    String lastEquationString3;
    String equationString;
    String error = "Вибачте, щось не так введено";
    EditText editEquation;
    String numberOfTextView = "numberOfTextView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editEquation = findViewById(R.id.editEquation);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        centerWidth = size.x / 2;
        centerHeight = size.y / 2;
        height = size.y;
        width = size.x;

        lastEquationTextView1 = findViewById(R.id.lastFunction1);
        lastEquationTextView2 = findViewById(R.id.lastFunction2);
        lastEquationTextView3 = findViewById(R.id.lastFunction3);

        CustomApplication.getPreferencesManager().saveCount("height", height);
        CustomApplication.getPreferencesManager().saveCount("width", width);

    }

    @Override
    protected void onResume() {
        super.onResume();
        lastEquationString1 = CustomApplication.getPreferencesManager().getStringFromPrefs("lastEquationString1");
        lastEquationString2 = CustomApplication.getPreferencesManager().getStringFromPrefs("lastEquationString2");
        lastEquationString3 = CustomApplication.getPreferencesManager().getStringFromPrefs("lastEquationString3");

        lastEquationTextView1.setText(lastEquationString1);
        lastEquationTextView2.setText(lastEquationString2);
        lastEquationTextView3.setText(lastEquationString3);
    }

    public void buildGraph(View view) {
        equationString = editEquation.getText().toString();
        CustomApplication.getPreferencesManager().saveStringInPrefs("equation", equationString);
        int number = CustomApplication.getPreferencesManager().getCountInt(numberOfTextView, 1);

        Log.d("tag", "num " + number);

        try {
            PolishNotation.eval(equationString, 0);
            Intent intent = new Intent(this, ScheduleActivity.class);
            if (equationCorrect(equationString)) {
                startActivity(intent);
            } else
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
        catch (Throwable throwable){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }

        switch (number){
            case 1:
                CustomApplication.getPreferencesManager().saveStringInPrefs("lastEquationString1", equationString);
                CustomApplication.getPreferencesManager().addToCount(numberOfTextView);
                Log.d("tag", "case 1, num " + number);
                lastEquationTextView1.setVisibility(View.VISIBLE);
                break;
            case 2:
                CustomApplication.getPreferencesManager().saveStringInPrefs("lastEquationString2", equationString);
                CustomApplication.getPreferencesManager().addToCount(numberOfTextView);
                Log.d("tag", "case 2 num " + number);
                lastEquationTextView2.setVisibility(View.VISIBLE);
                break;
            case 3:
                CustomApplication.getPreferencesManager().saveStringInPrefs("lastEquationString3", equationString);
                CustomApplication.getPreferencesManager().saveCount(numberOfTextView, 1);
                Log.d("tag", "case 3 num " + number);
                lastEquationTextView3.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void transferToGraphActivity(View view){
        TextView textView = (TextView) view;
        equationString = textView.getText().toString();
        CustomApplication.getPreferencesManager().saveStringInPrefs("equation", equationString);

        try {
            PolishNotation.eval(equationString, 0);

            if (equationCorrect(equationString)) {
                startActivity(new Intent(this, ScheduleActivity.class));
            } else
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
        catch (Throwable throwable){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }


    public void addPlus(View view) {
        addChar('+', editEquation);
    }

    public void addMinus(View view) {
        addChar('-', editEquation);
    }

    public void addMult(View view) {
        addChar('*', editEquation);
    }

    public void addDiv(View view) {
        addChar('/', editEquation);
    }

    public void addX(View view) {
        addChar('x', editEquation);
    }

    public void addOpeningBow(View view) {
        addChar('(', editEquation);
    }

    public void addClosingBow(View view) {
        addChar(')', editEquation);
    }


    public void addChar(char symbol, EditText editText) {
        String equationString = editText.getText().toString();

        int cursorIndex = editText.getSelectionStart();
        int nextIndex = cursorIndex + 1;

        String rightPart = equationString.substring(cursorIndex, equationString.length());
        String leftPart = equationString.substring(0, cursorIndex);
        editText.setText(leftPart + symbol + rightPart);
        editText.setSelection(nextIndex);
    }

    boolean equationCorrect (String equationString){
        return !equationString.endsWith("(") && !endsWithOperator(equationString) && !equationString.isEmpty() ;
    }

    public boolean endsWithOperator(String string) {
        return string.endsWith("+") || string.endsWith("-") || string.endsWith("*") || string.endsWith("/");
    }



}