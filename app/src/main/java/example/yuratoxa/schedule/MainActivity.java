package example.yuratoxa.schedule;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
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
    public boolean endsWithNumber (String string){
        return !endsWithOperator(string) & !string.endsWith("(") || !string.endsWith(")");
    }

    EditText firstEditText;
    EditText secondEditText;
    EditText thirdEditText;
    EditText divisionNumber;
    EditText setStroke;
    EditText setNumber;
    String error = "Введіть данні";
    TextView equation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstEditText = (EditText) findViewById(R.id.firstCof);
        secondEditText = (EditText) findViewById(R.id.secondCof);
        thirdEditText = (EditText) findViewById(R.id.thirdCof);
        divisionNumber = (EditText) findViewById(R.id.divisionNumber);
        setStroke = (EditText) findViewById(R.id.setStroke);
        equation = (TextView)findViewById(R.id.equation);
        setNumber = (EditText)findViewById(R.id.setNumber);

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
        startActivity(intent);




            try {
                float firstCof = Float.parseFloat(firstEditText.getText().toString());
                float secondCof = Float.parseFloat(secondEditText.getText().toString());
                float thirdCof =  Float.parseFloat(thirdEditText.getText().toString());
                if (firstCof != 0 & secondCof != 0 & thirdCof != 0 ){
                CustomApplication.getPreferencesManager().saveCount("a", firstCof);
                CustomApplication.getPreferencesManager().saveCount("b", secondCof);
                CustomApplication.getPreferencesManager().saveCount("c", thirdCof);
                if (!divisionNumber.getText().toString().isEmpty()){
                    CustomApplication.getPreferencesManager().saveCount("division", Float.parseFloat(divisionNumber.getText().toString()));
                }
                CustomApplication.getPreferencesManager().saveCount("stroke", Float.parseFloat(setStroke.getText().toString()));

            }
                else Toast.makeText(this, error, Toast.LENGTH_SHORT).show();


            }
            catch (Throwable throwable){
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();



        }

    }

    public void goToBuildSchedule(View view) {
        String equationString = equation.getText().toString();
        CustomApplication.getPreferencesManager().saveStringInPrefs("equation", equationString);

        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void addChar(char symbol) {
        String equationString = (String) equation.getText();

        if (isOperator(symbol) & (!endsWithOperator(equationString) & !equationString.endsWith("="))
                || isPlusOrMinus(symbol) & equationString.endsWith("=")
                || symbol == '(' & endsWithOperator(equationString)
                || (symbol == ')' & (!endsWithOperator(equationString) & !endsWithBow(equationString)))
                || (symbol == 'x' & (endsWithOperator(equationString) | equationString.endsWith("="))))
            equation.setText(equationString + symbol);
       /* else if (isPlusOrMinus(symbol) & equationString.endsWith("="))
            equation.setText(equationString + symbol);
        else if  (symbol == '(' & endsWithOperator(equationString))
            equation.setText(equationString + symbol);
        else if (symbol == ')' & (!endsWithOperator(equationString) & !endsWithBow(equationString)))
            equation.setText(equationString + symbol);
        else if (symbol == 'x' & (endsWithOperator(equationString) | equationString.endsWith("=")))
            equation.setText(equationString + symbol);*/
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


    public void removeLast(View view) {
        String equationString = (String) equation.getText();
        if (!equationString.endsWith("=")){
            equationString = equationString.substring(0, equationString.length() - 1);
        equation.setText(equationString);}
    }

    public void setNumber(View view) {
        String equationString = (String) equation.getText();
        if (!equationString.endsWith("x") & !equationString.endsWith(")"))
        equation.setText(equation.getText() + setNumber.getText().toString());
    }


}