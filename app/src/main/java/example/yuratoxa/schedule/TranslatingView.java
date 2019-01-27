package example.yuratoxa.schedule;

import android.view.MotionEvent;
import android.view.View;

public class TranslatingView {

    float dX, dY;

    public void changeZoom(View view, boolean bigWay) {
        if (bigWay) {
            view.setScaleX(view.getScaleX() + 0.5f);
            view.setScaleY(view.getScaleY() + 0.5f);
        } else {
            view.setScaleX(view.getScaleX() - 0.5f);
            view.setScaleY(view.getScaleY() - 0.5f);
        }
    }


    public boolean onTouch(View drawView, MotionEvent event, View point) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = drawView.getX() - event.getRawX();
                dY = drawView.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                drawView.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();

                point.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();


                break;
            default:
                return false;
        }
        return true;
    }

}
