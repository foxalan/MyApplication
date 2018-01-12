package com.example.sportlogs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class TimeUtil {
	private static SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfD1 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdfS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfSF = new SimpleDateFormat(
			"yyyy-MM-dd_HH-mm-ss");
	private static SimpleDateFormat sdfSF1 = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private static SimpleDateFormat sdfM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:ms");
	private static SimpleDateFormat sdfM1 = new SimpleDateFormat("HHmm");

	public static String getCurrentTimeSF() {
		return (sdfSF.format(new Date()));
	}

		private static SimpleDateFormat sdfA = new SimpleDateFormat(
			"yyyy-MM-dd  kk:mm:ss");

	
	public static String getCurrentTimeD() {
		return (sdfD.format(new Date()));
	}

	public static String getCurrentTimeA() {
		return (sdfA.format(new Date()));
	}

	
	public static String getCurrentTimeD1() {
		return (sdfD1.format(new Date()));
	}

	public static String getCurrentTimeS() {
		return (sdfS.format(new Date()));
	}

	public static String getCurrentTimeM() {
		return (sdfM.format(new Date()));
	}

	
	public static String getCurrentTimeM1() {
		return (sdfM1.format(new Date()));
	}

	public static Integer getCurrentYear() {
		return Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
	}

	public static Integer getCurrentMonth() {
		return Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
	}

	public static Integer getCurrentDay() {
		return Integer.valueOf(new SimpleDateFormat("dd").format(new Date()));
	}

	public static Integer getCurrentHour() {
		return Integer.valueOf(new SimpleDateFormat("HH").format(new Date()));
	}

	public static Integer getCurrentMinute() {
		return Integer.valueOf(new SimpleDateFormat("mm").format(new Date()));
	}

	public static Integer getCurrentSecond() {
		return Integer.valueOf(new SimpleDateFormat("ss").format(new Date()));
	}

	public static Integer getCurrentMilisecond() {
		return Integer.valueOf(new SimpleDateFormat("ms").format(new Date()));
	}

	
	public static Date parseDateS(String dateStr) {
		try {
			return sdfS.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static Date parseDateSF(String dateStr) {
		try {
			return sdfSF1.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

		public static String getCurrentDateTime() {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mYear = String.valueOf(c.get(Calendar.YEAR));
		String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);
		String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mMinute = String.valueOf(c.get(Calendar.MINUTE));
		String mSecond = String.valueOf(c.get(Calendar.SECOND));
		if (mHour.length() == 1) {
			mHour = "0" + mHour;
		}
		if (mMinute.length() == 1) {
			mMinute = "0" + mMinute;
		}
		if (mSecond.length() == 1) {
			mSecond = "0" + mSecond;
		}
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		String dateTime = mYear + "年" + mMonth + "月" + mDay + "日" + "  " + "星期"
				+ mWay + "  " + mHour + ":" + mMinute + ":" + mSecond;
		return dateTime;
	}

	
	public static Integer getCurrentWeekIndex() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE));
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}

	
	public static Date getDate(String strTime) {
		if (strTime == null || strTime.length() != 19) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(strTime.substring(0, 4)),
				(Integer.parseInt(strTime.substring(5, 7)) - 1),
				Integer.parseInt(strTime.substring(8, 10)),
				Integer.parseInt(strTime.substring(11, 13)),
				Integer.parseInt(strTime.substring(14, 16)),
				Integer.parseInt(strTime.substring(17)));
		return cal.getTime();
	}

	
	public static boolean isOverdue(String strTime) {
		boolean flag = false;
		if (strTime != null) {
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(strTime.substring(0, 4)),
					(Integer.parseInt(strTime.substring(5, 7)) - 1),
					Integer.parseInt(strTime.substring(8, 10)));
			if (cal.getTime().before(new Date())) {
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}

	
	public static String youbaoTime(long seconds) {
		long youbaotime = 1293811200000l;
		Date d = new Date();
		d.setTime(youbaotime + seconds * 1000);
		return sdfS.format(d);
	}

	
	private final static int SECONDOFLEAPYEAR = 31622400;
	
	private final static int SECONDOFYEAR = 31536000;
	
	private final static int MAXSECONDOFDAY = 86400;
	
	final static char month_s[][] = {
			{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };

	
	private static int calcSecondsFrom2011(int wYear, int wMonth, int wDay,
			int wHour, int wMinute, int wSecond) {
		int tTemp = 0;
		int tSecond = 0;
		int nCount = 0;
		int i = 0;
		int j = 0;
		tSecond = wHour * 3600 + wMinute * 60 + wSecond;
		for (i = 2011; i < wYear; ++i) {
			if (isLeadYear(i) == 1) {
				++nCount;
			}
		}

		tTemp += (wYear - 2011 - nCount) * SECONDOFYEAR + nCount
				* SECONDOFLEAPYEAR;
		if (wMonth > 1) {
			if (isLeadYear(wYear) == 1) {
				for (j = 0; j < wMonth - 1; ++j) {
					tTemp += month_s[1][j] * MAXSECONDOFDAY;
				}
				tTemp += (wDay - 1) * MAXSECONDOFDAY + tSecond;
			} else {
				for (j = 0; j < wMonth - 1; ++j) {
					tTemp += month_s[0][j] * MAXSECONDOFDAY;
				}
				tTemp += (wDay - 1) * MAXSECONDOFDAY + tSecond;
			}
		} else {
			tTemp += (wDay - 1) * MAXSECONDOFDAY + tSecond;
		}
		return tTemp;
	}

	private static int isLeadYear(int year) {
		
		if ((year % 100 != 0) && (year % 400 == 0)) {
			return 1;
		}
		if ((year % 100 == 0) && (year % 400 == 0)) {
			return 1;
		}
		return 0;
	}

	
	public static void displayDiffTime(Date startTime, Date endTime) {
		long l = endTime.getTime() - startTime.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long ms = l - s * 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;

		System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒"
				+ ms + "毫秒");
	}

	
	public static String getCurrentTimeByMsec() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String date = formatter.format(new Date());
		return date;
	}

	
	public static long getTimeMillis(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " "
					+ time);
			return curDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
