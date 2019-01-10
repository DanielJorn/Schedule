package example.yuratoxa.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class ScheduleActivity extends AppCompatActivity {

    SeekBar seekBar;
    VerticalSeekBar verticalSeekBar;
    View drawView;
    TranslatingView trView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        verticalSeekBar = (VerticalSeekBar)findViewById(R.id.vSeekBar);
        drawView = (View)findViewById(R.id.customView1);
        trView = new TranslatingView();


        trView.translateView(verticalSeekBar, drawView, true);

        trView.translateView(seekBar, drawView, false);

    }

    public void plusZoom(View view) {
        trView.changeZoom(drawView, true);
    }

    public void minusZoom(View view){
        trView.changeZoom(drawView, false);
    }
}


