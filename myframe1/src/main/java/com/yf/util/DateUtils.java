package com.yf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cykj.grcloud.util.Ognl;

public class DateUtils {
	static Logger log = LoggerFactory.getLogger(DateUtils.class);

	private static String datePattern = "yyyy-MM-dd";

	public static String getDateTimeString() {
		FastDateFormat dfmt = FastDateFormat.getInstance("yyyyMMddHHmmss");
		return dfmt.format(new java.util.Date());
	}

	public static String getDateString() {
		FastDateFormat dfmt = FastDateFormat.getInstance("yyyyMMdd");
		return dfmt.format(new java.util.Date());
	}

	public static String getYYYYMMDDHH24MISS(String strYYYY_MM_DD) {
		String tmp = "";
		try {
			java.util.Date d = getDate(strYYYY_MM_DD, "yyyy-mm-dd");
			FastDateFormat dfmt = FastDateFormat.getInstance("yyyyMMddHHmmss");
			return dfmt.format(d);
		} catch (ParseException e) {
			log.warn("", e);
		}

		return tmp;
	}

	/**
	 * 取某年某月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static String getYearMonth(Date date) {

		try {
			Calendar calendar = getCalendarFromDate(date);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			String _month = String.valueOf(month);
			if (_month.length() < 2) {
				_month = "0" + _month;
			}
			String value = String.valueOf(year).substring(2);
			String result = value + _month;
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 根据Date 得到对应的Calendar
	 * 
	 * @param date
	 * @return @
	 */
	public static Calendar getCalendarFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 字符串日期转换为标准日期
	 * 
	 * @param date
	 *            字符串日期
	 * @return
	 */
	public static Date getDateFromString(String date) {
		Date theDate = null;
		try {
			theDate = org.apache.commons.lang3.time.DateUtils.parseDate(date,
					datePattern);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return theDate;
	}

	public static String getDateString(String pattern) {
		FastDateFormat dfmt = FastDateFormat.getInstance(pattern);
		return dfmt.format(new java.util.Date());
	}

	public static String getDateString(int days, String pattern) {
		FastDateFormat dfmt = FastDateFormat.getInstance(pattern);
		long days2 = days;
		return dfmt.format(new java.util.Date(new java.util.Date().getTime()
				+ 86400000L * days2));
	}

	public static String getDateString(java.util.Date date) {
		if (date == null) {
			return "";
		}
		FastDateFormat dfmt = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		return dfmt.format(date);
	}

	public static String getShortDateString(java.util.Date date) {
		if (date == null) {
			return "";
		}
		FastDateFormat dfmt = FastDateFormat.getInstance("yyyy-MM-dd");
		return dfmt.format(date);
	}

	public static String getDateString(java.util.Date date, String pattern) {
		FastDateFormat dfmt = FastDateFormat.getInstance(pattern);
		return date != null ? dfmt.format(date) : "";
	}

	public static String getDateString(String date, String pattern) {
		if ((date == null) || ("".equals(date)) || ("null".equals(date)))
			return "";
		try {
			@SuppressWarnings("deprecation")
			java.util.Date d = new java.util.Date(date);
			return getDateString(d, pattern);
		} catch (Exception e) {
			log.warn("", e);
		}
		return "";
	}

	public static String getDateString(java.sql.Date date, String pattern) {
		FastDateFormat dfmt = FastDateFormat.getInstance(pattern);
		return date != null ? dfmt.format(date) : "";
	}

	public static java.sql.Date getSQLDate(String date) {
		try {
			if (Ognl.isNotEmpty(date)) {
				java.util.Date d = org.apache.commons.lang3.time.DateUtils
						.parseDate(date, "yyyy-MM-dd");
				return new java.sql.Date(d.getTime());
			}
			return null;
		} catch (ParseException e) {
			log.warn("", e);
		}
		return null;
	}

	public static java.util.Date getDate(String strDate) throws ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDate(strDate,
				"yyyyMMddHHmmss");
	}

	public static java.util.Date getDate_(String strDate) throws ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDate(strDate,
				"yyyy-MM-dd HH:mm:ss");
	}

	public static java.util.Date getDate(String strDate, String pattern)
			throws ParseException {
		return org.apache.commons.lang3.time.DateUtils.parseDate(strDate,
				pattern);
	}

	public static String getStringDate(String stringdate) {
		if (stringdate == null) {
			return null;
		}
		FastDateFormat formatter2 = FastDateFormat
				.getInstance("yyyy-MM-dd HH:mm:ss");
		String dateString = "";
		try {
			java.util.Date date = org.apache.commons.lang3.time.DateUtils
					.parseDate(stringdate, "yyyyMMddHHmmss");
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			log.warn("", e);
		}
		return dateString;
	}

	public static String getStringDate(String stringdate, String fpattern,
			String tpattern) {
		if (stringdate == null) {
			return null;
		}
		FastDateFormat formatter2 = FastDateFormat.getInstance(tpattern);
		String dateString = "";
		try {
			java.util.Date date = org.apache.commons.lang3.time.DateUtils
					.parseDate(stringdate.trim(), fpattern);
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			log.warn("", e);
		}
		return dateString;
	}

	public static String getChdate(int month_num) {
		Calendar c1 = Calendar.getInstance();
		String result = "";
		c1.add(2, month_num);
		result = String.valueOf(c1.get(1));
		if (c1.get(2) + 1 >= 10)
			result = result + String.valueOf(c1.get(2) + 1);
		else {
			result = result + "0" + String.valueOf(c1.get(2) + 1);
		}
		return result;
	}

	/**
	 * 得到当前系统的年份
	 */
	public static int getSysYear() {
		Calendar calendar = new GregorianCalendar();
		int iyear = calendar.get(1);
		return iyear;
	}

	/**
	 * 得到当前系统的月份
	 */
	public static int getSysMonth() {
		Calendar calendar = new GregorianCalendar();
		int imonth = calendar.get(2) + 1;
		return imonth;
	}

	public static String getDateOfSp(String sp) {
		String reday = "";
		int y = getSysYear();
		int m = getSysMonth();
		int d = getSysDay();
		reday = y + sp;
		if (m < 10)
			reday = reday + "0" + m + sp;
		else {
			reday = reday + m + sp;
		}
		if (d < 10)
			reday = reday + "0" + d;
		else {
			reday = reday + d;
		}
		return reday;
	}

	public static String getDateOfFirstDay(String sp) {
		String reday = "";
		int y = getSysYear();
		int m = getSysMonth();
		reday = y + sp;
		if (m < 10)
			reday = reday + "0" + m + sp + "01";
		else {
			reday = reday + m + sp + "01";
		}
		return reday;
	}

	/**
	 * 得到当前系统的天
	 */
	public static int getSysDay() {
		Calendar calendar = new GregorianCalendar();
		int idate = calendar.get(5);
		return idate;
	}

	public static String getDateString2() {
		String tmp = "";
		tmp = getSysYear() + "    " + getSysMonth() + "    " + getSysDay()
				+ "    ";
		return tmp;
	}

	public static int getTwoMonthNum(String startDate, String endDate) {
		int year1 = Integer.parseInt(startDate.substring(0, 4));
		int year2 = Integer.parseInt(endDate.substring(0, 4));
		int month1 = Integer.parseInt(startDate.substring(4));
		int month2 = Integer.parseInt(endDate.substring(4));
		return Math.abs(year1 - year2) * 12 - (month1 - month2) + 1;
	}

	public static String getNextMonth(String startDate, int i) {
		int start = Integer.parseInt(startDate);
		int next = start + i;
		int year = Integer.parseInt(String.valueOf(next).substring(0, 4));
		int month = Integer.parseInt(String.valueOf(next).substring(4));
		if (month > 12) {
			year++;
			month -= 12;
		}
		if (month < 10) {
			return year + "0" + month;
		}
		return year + "" + month;
	}

	public static int getDays(String yearMonth) {
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = Integer.parseInt(yearMonth.substring(0, 4));
		int month = Integer.parseInt(yearMonth.substring(4)) - 1;
		if (month == 1) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					return 28;
				}
				return 29;
			}

			return 28;
		}

		return days[month];
	}

	public static int isBetweenDays(String startDay, String endDay) {
		FastDateFormat formatter = FastDateFormat.getInstance("yyyyMMdd");
		java.util.Date date = new java.util.Date();
		String today = formatter.format(date);
		startDay = today.substring(0, 6) + startDay;
		endDay = today.substring(0, 6) + endDay;
		if ((today.compareTo(startDay) >= 0) && (today.compareTo(endDay) <= 0)) {
			return 0;
		}
		return 1;
	}

	public static boolean isDate(String dateStr, String dateFomrat) {
		boolean tmp = false;
		try {
			java.util.Date d = getDate(dateStr, dateFomrat);
			FastDateFormat formatter = FastDateFormat.getInstance(dateFomrat);
			formatter.format(d);
			tmp = true;
		} catch (ParseException e) {
			tmp = false;
		}
		return tmp;
	}

	public static boolean isBetweenDays(String startDay, String endDay,
			String dateFomrat) {
		boolean tmp = false;

		if ((isDate(startDay, dateFomrat)) && (isDate(endDay, dateFomrat))) {
			try {
				if (getDate(startDay, dateFomrat).getTime() > getDate(endDay,
						dateFomrat).getTime())
					tmp = false;
				else
					tmp = true;
			} catch (ParseException localParseException) {
			}
		}
		return tmp;
	}


	public static boolean isDateStr(String strDate, String pattern) {
		boolean tmp = true;
		try {
			getDate(strDate, pattern);
		} catch (ParseException e) {
			tmp = false;
		}

		return tmp;
	}

	public static java.util.Date getAfterNDaysDate(java.util.Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(6, days);

		return cal.getTime();
	}

	public static long DaysBetweenTwoDate(String firstString,
			String secondString) throws ParseException {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		java.util.Date firstDate = org.apache.commons.lang3.time.DateUtils
				.parseDate(firstString, pattern);
		java.util.Date secondDate = org.apache.commons.lang3.time.DateUtils
				.parseDate(secondString, pattern);

		return (secondDate.getTime() - firstDate.getTime()) / 60000L;
	}

	public static long daysBetweenTwoDate(java.util.Date date1,
			java.util.Date date2) {
		long nDay = (date1.getTime() - date2.getTime()) / 86400000L > 0L ? (date1
				.getTime() - date2.getTime()) / 86400000L
				: (date2.getTime() - date1.getTime()) / 86400000L;

		return nDay;
	}

	public static java.util.Date addYMD(java.util.Date date, int yearNum,
			int monthNum, int dayNum) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(1, yearNum);
		c.add(2, monthNum);
		c.add(5, dayNum);
		return c.getTime();
	}

	public static String getDayIncrease(String endtime, int n) {
		FastDateFormat formatter = FastDateFormat.getInstance("yyyy-MM-dd");
		try {
			java.util.Date todate = org.apache.commons.lang3.time.DateUtils
					.parseDate(endtime, "yyyy-MM-dd");
			Calendar gc = Calendar.getInstance();
			gc.setTime(todate);
			gc.add(5, n);
			endtime = formatter.format(gc.getTime());
		} catch (ParseException e) {
			log.warn("getDayIncrease exception: ", e);
		}
		return endtime;
	}

	public static String getCurrentTime() {
		java.util.Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);

	}

	/**
	 * 取某年某月的第一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 取某年某月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
}