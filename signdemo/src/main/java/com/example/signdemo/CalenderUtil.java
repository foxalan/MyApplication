package com.example.signdemo;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Function Name : SetAudioMute
 * Author : Eddie
 * Modify Date :
 * Input Parameter &
 */

public class CalenderUtil {

    private Calendar calendar;


    public CalenderUtil() {


    }

    /**
     * 判断是否是闰年
     */
    public boolean isLeapYear(int year){
        if (year % 100 == 0 && year % 400 == 0){
            return true;
        }else if (year % 100 != 0 && year % 4==0){
            return true;
        }
        return false;
    }

    /**
     * 得到某月多少天
     * 哈哈少儿口诀：1357810腊三十一天永不差，469冬三十日平年二月28，闰年再把一天加。
     */
    public int getDaysOfMonth(boolean isLeapYear,int month){
        int days=0;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days=30;
                break;
            case 2:
                if (isLeapYear){
                    days=29;
                }else{
                    days=28;
                }
        }
        return days;
    }

    /**
     * 得到某年某月一号是星期几  （0-6 日-六）
     */

    public static int getWeekdayOfMonth(int mYear, int mMonth){
        Calendar cal=Calendar.getInstance();
        cal.set(mYear,mMonth,1);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }



}
