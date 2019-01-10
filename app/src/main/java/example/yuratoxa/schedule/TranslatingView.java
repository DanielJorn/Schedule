package example.yuratoxa.schedule;

import android.view.View;
import android.widget.SeekBar;

public class TranslatingView {

    public void changeZoom (View view, boolean bigWay){
        if (bigWay){
            view.setScaleX(view.getScaleX() + 0.5f);
            view.setScaleY(view.getScaleY() + 0.5f);
        }
        else {view.setScaleX(view.getScaleX() - 0.5f);
         view.setScaleY(view.getScaleY() - 0.5f);}
    }

    public void translateView (SeekBar seekBar, final View translatingView, boolean vertical) {

        if (!vertical) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    translatingView.setX((progress - seekBar.getMax() / 2));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } else seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                translatingView.setY((progress - seekBar.getMax() / 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }}
