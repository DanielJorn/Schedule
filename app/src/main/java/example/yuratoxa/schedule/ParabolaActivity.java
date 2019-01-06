package example.yuratoxa.schedule;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class ParabolaActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    VerticalSeekBar verticalSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_parabola);

 seekBar = (SeekBar) findViewById(R.id.seekBar);
 verticalSeekBar = (VerticalSeekBar)findViewById(R.id.vSeekBar);


 verticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

     @Override
     public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

         View drawView = (View)findViewById(R.id.customView1);
         drawView.setY(progress - 500);
     }

     @Override
     public void onStartTrackingTouch(SeekBar seekBar) {

     }

     @Override
     public void onStopTrackingTouch(SeekBar seekBar) {

     }
 });

 seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
     @Override
     public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

         View drawView = (View)findViewById(R.id.customView1);
        drawView.setX(progress - 1000);
     }

     @Override
     public void onStartTrackingTouch(SeekBar seekBar) {

     }

     @Override
     public void onStopTrackingTouch(SeekBar seekBar) {

     }
 });
    }

    public void plusZoom(View view) {
        View drawView = (View)findViewById(R.id.customView1);

        drawView.setScaleX(drawView.getScaleX() + 0.5f);
        drawView.setScaleY(drawView.getScaleY() + 0.5f);
    }

    public void minusZoom(View view){
        View drawView = (View)findViewById(R.id.customView1);

        drawView.setScaleX(drawView.getScaleX() - 0.5f);
        drawView.setScaleY(drawView.getScaleY() - 0.5f);
    }


    public void minusCoordinatesOfTouch(View view) {

    }

    public void plusCoordinatesOfTouch(View view) {

    }
}


