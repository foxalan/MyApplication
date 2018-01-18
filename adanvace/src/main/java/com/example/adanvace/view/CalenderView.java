package com.example.adanvace.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.adanvace.info.DayInfo;
import com.example.adanvace.util.WindowUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Alan
 *         签到VIEW
 */

public class CalenderView extends View {

    private int mScreenWidth;
    private int mScreenHeight;

    private int rect_width;
    private int rect_height;

    private int[] length = new int[1];

    private Paint paint_title;
    private Paint paint_week;
    private Paint paint_day;

    private int paddingLeft = 25;
    private int paddingRight = 25;

    private int mWidth;
    private int mHeight;

    private Calendar calendar;
    private String str_current_date;
    private String[] week = {"一", "二", "三", "四", "五", "六", "日"};

    private Rect rect_title;
    private List<Rect> rectList = new ArrayList<>();
    private List<Rect> rectList_day = new ArrayList<>();
    private List<String> dayList = new ArrayList<>();

    private int currentMonthDay;
    private Calendar calendar_first_week;
    private int firstDayofWeek;
    private int month;
    private int currentDay;

    private String signed_name;
    private int currentPosition;

    public int getmScreenWidth() {
        return mScreenWidth;
    }

    public void setmScreenWidth(int mScreenWidth) {
        this.mScreenWidth = mScreenWidth;
    }

    public String getSigned_name() {
        return signed_name;
    }

    public void setSigned_name(String signed_name) {
        this.signed_name = signed_name;
    }

    public String getSigned_context() {
        return signed_context;
    }

    public void setSigned_context(String signed_context) {
        this.signed_context = signed_context;
    }

    private String signed_context;


    private List<DayInfo> dayInfoList = new ArrayList<>();

    public CalenderView(Context context) {
        this(context, null);
    }

    public CalenderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalenderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        length = WindowUtil.getSystemLength(context);

        mScreenWidth = length[0];
        mScreenHeight = length[1];

        initPaint();
        initData();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint_title = new Paint();
        paint_title.setAntiAlias(true);
        paint_title.setColor(Color.WHITE);
        paint_title.setStyle(Paint.Style.STROKE);
        paint_title.setStrokeWidth(2);
        paint_title.setTextSize(30);

        paint_week = new Paint();
        paint_week.setAntiAlias(true);
        paint_week.setColor(Color.WHITE);
        paint_week.setStyle(Paint.Style.STROKE);
        paint_week.setTextSize(50);

        paint_day = new Paint();
        paint_day.setAntiAlias(true);
        paint_day.setColor(Color.WHITE);
        paint_day.setStyle(Paint.Style.STROKE);
        paint_day.setTextSize(30);
    }

    private void initData() {
        calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("sign", "===" + currentDay);

        calendar_first_week = Calendar.getInstance();
        calendar_first_week.set(year, month - 1, 1);
        firstDayofWeek = printDayOfWeek(calendar_first_week);
        Log.e("sign", "===" + firstDayofWeek + "===");

        str_current_date = year + "年" + month + "月" + "  " + "签到列表";

        currentMonthDay = isLeapYear(year, month);
        dayList.clear();
        for (int i = 0; i < (firstDayofWeek - 1); i++) {
            dayList.add("");
        }

        for (int i = 0; i < currentMonthDay; i++) {
            dayList.add(i + 1 + "");
        }

        currentPosition = firstDayofWeek + currentDay - 1;

    }

    private int printDayOfWeek(Calendar c) {

        int day = 0;
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                day = 7;
                break;
            case Calendar.MONDAY:
                day = 1;
                break;
            case Calendar.TUESDAY:
                day = 2;
                break;
            case Calendar.WEDNESDAY:
                day = 3;
                break;
            case Calendar.THURSDAY:
                day = 4;
                break;
            case Calendar.FRIDAY:
                day = 5;
                break;
            case Calendar.SATURDAY:
                day = 6;
                break;
            default:
                break;
        }

        return day;
    }

    /**
     * 判断是否是闰年
     *
     * @param year
     * @param month
     * @return
     */
    private int isLeapYear(int year, int month) {

        int day = 30;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            default:
                break;
        }

        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            if (month == 2) {
                day = 29;
            }
        } else {
            if (month == 2) {
                day = 28;
            }
        }

        return day;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        rect_width = (mScreenWidth - 2 * paddingLeft) / 7;
        rect_height = rect_width;

        mWidth = MeasureSpec.getSize(widthMeasureSpec);

        if (heightSpec == MeasureSpec.EXACTLY) {
            mHeight = rect_width * 10;
        } else {
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        }

        initRect();
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 初始化RECT工具
     */
    private void initRect() {

        rect_title = new Rect();
        rect_title.set(0, 0, mScreenWidth, rect_height);

        int startHeight = rect_height;
        int startWidth = paddingLeft;

        rectList.clear();
        for (int i = 0; i < 7; i++) {
            Rect rect = new Rect();
            rect.set(startWidth + i * rect_width, startHeight, startWidth + (i + 1) * rect_width, startHeight + rect_height);
            rectList.add(rect);
        }

        rectList_day.clear();
        dayInfoList.clear();

        int startDayHeight = rect_height * 2;
        int startDayWidth = paddingLeft;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Rect rect = new Rect();
                rect.set(startDayWidth + j * rect_width, startDayHeight + i * rect_height, startDayWidth + (j + 1) * rect_width, startDayHeight + (i + 1) * rect_height);
                rectList_day.add(rect);

                DayInfo dayInfo = new DayInfo();
                dayInfo.setRect(rect);
                dayInfo.setPosition(i * 7 + j);
                if ((i * 7 + j) == (currentPosition-1)) {
                    dayInfo.setStatus(1);
                    Log.e("huiye", "==" + i + "===" + j);
                }else {
                    dayInfo.setStatus(0);
                }
//                if (signContain(i * 7 + j)) {
//                    dayInfo.setStatus(1);
//                } else {
//                    dayInfo.setStatus(0);
//                }
                dayInfoList.add(dayInfo);
            }
        }


    }

    private boolean signContain(int i) {

        boolean isContain = false;

//        List<Integer> list = SignDayUtil.findDayByName(signed_name, month, signed_context);
//
//        for (int j = 0; j < list.size(); j++) {
//            if ((i + 1) == (list.get(j) + firstDayofWeek - 1)) {
//                isContain = true;
//            }
//        }

        return isContain;
    }

    public void updateView() {
        initRect();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTitle(canvas);
        drawWeek(canvas);
        drawDay(canvas);

    }


    /**
     * @param canvas
     */
    private void drawTitle(Canvas canvas) {
        canvas.drawRect(rect_title, paint_title);

        Paint.FontMetrics fm = paint_title.getFontMetrics();

        float y1 = rect_height / 2 - (fm.ascent + fm.descent) / 2;
        canvas.drawText(str_current_date, paddingLeft, y1, paint_title);
    }

    private void drawWeek(Canvas canvas) {

        Paint.FontMetrics fm = paint_title.getFontMetrics();

        for (int i = 0; i < rectList.size(); i++) {
            Rect rect = rectList.get(i);

            float x = (rect.left + rect.right - paint_week.getTextSize()) / 2;
            float y = (rect.bottom + rect.top) / 2 - (fm.ascent + fm.descent) / 2;

            canvas.drawRect(rect, paint_week);
            canvas.drawText(week[i], x, y, paint_week);
        }

    }

    private void drawDay(Canvas canvas) {

        Paint.FontMetrics fm = paint_day.getFontMetrics();

        for (int i = 0; i < rectList_day.size(); i++) {
            Log.e("huiye",rectList_day.size()+"=======");
            Rect rect = dayInfoList.get(i).getRect();
            canvas.drawRect(rect, paint_day);
            Log.e("huiye",dayInfoList.get(i).toString());
            if (dayInfoList.get(i).getStatus() == 1) {
                canvas.drawCircle((rect.left + rect.right) / 2, (rect.bottom + rect.top) / 2, rect_width / 2, paint_day);
                Log.e("tang", i + "draw canvas");
            }

            if (dayList.size() > i) {
                float x = (rect.left + rect.right - paint_day.getTextSize()) / 2;
                float y = (rect.bottom + rect.top) / 2 - (fm.ascent + fm.descent) / 2;

                canvas.drawText(dayList.get(i), x, y, paint_day);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}
