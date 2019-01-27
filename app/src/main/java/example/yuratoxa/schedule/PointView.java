package example.yuratoxa.schedule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PointView extends View {


    Paint p = new Paint();
    BuildingSchedule buildingSchedule = new BuildingSchedule();

    public PointView(Context context) {
        super(context);

    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PointView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = CustomApplication.getPreferencesManager().getCount("x", 0);

        String equation = CustomApplication.getPreferencesManager().getStringFromPrefs("equation");

        buildingSchedule.buildPoint(equation, canvas, p, x);
    }
}
