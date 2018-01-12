package com.example.sportlogs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.sportlogs.R;
import com.example.sportlogs.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Author : Alan
 * Modify Date : 8/9/17
 * Issue : TODO
 * Whether solve :
 */

public class CustomProgressBar extends View {

    private int progress_width;
    private int progress_height;
    private int progress_color;

    private int mWidth;
    private int mHeight;

    private Paint paint_background;
    private Paint paint_progressbar;
    private Paint paint_insert;

    private RectF rect_background = new RectF();
    private RectF rect_progressbar = new RectF();


    private double progress = 0.5;

    private int center_x;
    private int center_y;

    private int progress_insert_length;
    private int progress_insert_count;

    private List<Point> listPointTop = new ArrayList<>();
    private List<Point> listPointBottom = new ArrayList<>();
    private int length = 30;


    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, 0, defStyleAttr);

        progress_width = (int) typedArray.getDimension(R.styleable.CustomProgressBar_progressbar_width, 100);
        progress_height = (int) typedArray.getDimension(R.styleable.CustomProgressBar_progressbar_height, 20);
        progress_color = typedArray.getColor(R.styleable.CustomProgressBar_progressbar_color, context.getResources().getColor(R.color.color_progress_bar));

        initPaint();



    }


    private void initPaint() {

        paint_background = new Paint();
        paint_background.setAntiAlias(true);
        paint_background.setColor(Color.LTGRAY);
        paint_background.setStyle(Paint.Style.FILL_AND_STROKE);
        paint_background.setStrokeWidth(5);

        paint_progressbar = new Paint();
        paint_progressbar.setStrokeWidth(5);
        paint_progressbar.setAntiAlias(true);
        paint_progressbar.setStyle(Paint.Style.FILL_AND_STROKE);
        paint_progressbar.setColor(progress_color);

        paint_insert = new Paint();
        paint_insert.setStrokeWidth(5);
        paint_insert.setStyle(Paint.Style.FILL_AND_STROKE);
        paint_insert.setColor(Color.WHITE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
            default:
                mWidth = progress_width + getPaddingLeft() + getPaddingRight();
                break;

        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                mHeight = progress_height + getPaddingTop() + getPaddingBottom();
                break;
        }

        mWidth = Math.max(mWidth, progress_width);
        mHeight = Math.max(mHeight, progress_height);

        initRect();

        setMeasuredDimension(mWidth, mHeight);
    }


    private void initRect() {

        center_x = mWidth / 2;
        center_y = mHeight / 2;


        rect_background.set(center_x - progress_width / 2, center_y - progress_height / 2, center_x + progress_width / 2, center_y + progress_height / 2);
        rect_progressbar.set(center_x - progress_width / 2, center_y - progress_height / 2, (float) (center_x - progress_width / 2 + progress_width * progress), center_y + progress_height / 2);

        progress_insert_count = (progress_width / 30) - 1;
        L.d("count" + progress_insert_count);
        for (int i = 0; i < progress_insert_count; i++) {
            Point point_top = new Point();
            point_top.set((i + 1) * 30 + center_x - progress_width / 2, center_y - progress_height / 2);
            Point point_bottom = new Point();
            point_bottom.set((i + 1) * 30 - 10 + center_x - progress_width / 2, center_y + progress_height / 2);
            L.d("x:" + point_bottom.x + ":" + point_bottom.y);
            listPointBottom.add(point_bottom);
            listPointTop.add(point_top);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(rect_background, 5, 5, paint_background);
        canvas.drawRoundRect(rect_progressbar, 4, 4, paint_progressbar);

        for (int i = 0; i < progress_insert_count; i++) {

            canvas.drawLine(listPointTop.get(i).x, listPointTop.get(i).y, listPointBottom.get(i).x, listPointBottom.get(i).y, paint_insert);
        }
    }
}
