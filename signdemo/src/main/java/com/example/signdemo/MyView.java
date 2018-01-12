package com.example.signdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class MyView extends View {

    private Paint paint_title;
    private Path path_title;
    private Paint paint_line;

    private final int PATH_START = 40;
    private final int TITLE_WIDTH = 780;
    private final int TiTLE_HEIGHT = 40;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint_title = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_title.setColor(Color.BLUE);
        paint_title.setStrokeWidth(2);
        paint_title.setStyle(Paint.Style.STROKE);
        paint_title.setTextSize(30);


        path_title = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTitle(canvas);
        drawDates(canvas);

    }

    /**
     * 画出签到的标题，日，一，二...
     *
     * @param canvas
     */

    private void drawTitle(Canvas canvas) {

        path_title.moveTo(PATH_START, PATH_START);
        path_title.lineTo(TITLE_WIDTH, PATH_START);
        path_title.lineTo(TITLE_WIDTH, PATH_START + TiTLE_HEIGHT);
        path_title.lineTo(PATH_START, PATH_START + TiTLE_HEIGHT);
        path_title.close();

        canvas.drawText("日", 40, 70, paint_title);
        canvas.drawText("一", 140, 70, paint_title);
        canvas.drawText("二", 240, 70, paint_title);
        canvas.drawText("三", 340, 70, paint_title);
        canvas.drawText("四", 240, 70, paint_title);
        canvas.drawText("五", 290, 70, paint_title);
        canvas.drawText("六", 340, 70, paint_title);

        canvas.drawPath(path_title, paint_title);
    }

    /**
     * 画出日历
     *
     * @param canvas
     */

    private void drawDates(Canvas canvas) {

    }
}
