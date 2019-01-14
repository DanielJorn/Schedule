package example.yuratoxa.schedule;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=';
    }
    public boolean isPlusOrMinus(char c){
        return c == '-' || c == '+';
    }
    public boolean endsWithOperator(String string){
        return string.endsWith("+") || string.endsWith("-") || string.endsWith("*") || string.endsWith("/");
    }
    public boolean endsWithBow (String string){
        return string.endsWith(")") || string.endsWith("(");
    }


    String error = "Введіть данні";
    TextView equation;
    EditText editEquation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equation = (TextView)findViewById(R.id.equation);
        editEquation = (EditText)findViewById(R.id.editEquation);
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




    public void goToBuildSchedule(View view) {
        String equationString = editEquation.getText().toString();
        CustomApplication.getPreferencesManager().saveStringInPrefs("equation", equationString);

        Intent intent = new Intent(this, ScheduleActivity.class);
        if(!equationString.endsWith("(") | !endsWithOperator(equationString))
            startActivity(intent);
    }

    public void addChar(char symbol) {
        String equationString = (String) editEquation.getText().toString();

        if (isOperator(symbol) & (!endsWithOperator(equationString) & !equationString.endsWith("="))
                || isPlusOrMinus(symbol) & equationString.endsWith("=")
                || symbol == '(' & (endsWithOperator(equationString) || equationString.isEmpty())
                || (symbol == ')' & (!endsWithOperator(equationString) & !endsWithBow(equationString)))
                || (symbol == 'x' & (endsWithOperator(equationString) | equationString.isEmpty()))) {
            editEquation.setText(equationString + symbol);
            editEquation.setSelection(equationString.length() + 1);
        }


            }




    public void addPlus(View view){
       addChar('+');
    }
    public void addMinus(View view){addChar('-'); }
    public void addMult(View view){
        addChar('*');
    }
    public void addDiv(View view){
        addChar('/');
    }
    public void addX (View view){
        addChar('x');
    }
    public void addOpeningBow(View view) {
        addChar('('); }
    public void addClosingBow(View view) {
        addChar(')'); }


}