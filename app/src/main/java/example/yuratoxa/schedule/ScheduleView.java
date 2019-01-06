package example.yuratoxa.schedule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ScheduleView extends View {

    public ScheduleView(Context context)
    {
        super(context);

    }
    public ScheduleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }
    public ScheduleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint p = new Paint();
        BuildingSchedule buildingSchedule = new BuildingSchedule();

        buildingSchedule.buildCoordinateSystem(canvas, p);
    }

}
