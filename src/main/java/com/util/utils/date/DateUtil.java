package com.util.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>针对时间以及日期操作的工具类。</p>
 * @author xlp
 *
 */
public class DateUtil {
	

	/**
	 * <p>判断当前Date对象是否是NULL。</p> 
	 * <pre>
	 * <code>DateTimeUtils.isNull(null) = true;
	 * DateTimeUtils.isNull(new Date()) = false;<code>
	 * </pre>
	 * @param dateTime 进行判断的Date对象。
	 * @return 如果Date为null，返回true；如果Date不为null，返回false。
	 */
	public static Boolean isNull(Date dateTime) {
		if (dateTime == null) {
			return true;
		}
		return false;
	}

	/**
	 * <p>判断当前Date对象是否不是NULL。</p>
	 * <pre>
	 * <code>DateTimeUtils.isNull(null) = false;
	 * DateTimeUtils.isNull(new Date()) = true;<code>
	 * </pre>
	 * @param dateTime 进行判断的Date对象。
	 * @return 如果Date为null，返回false；如果Date不为null，返回true。
	 */
	public static Boolean isNotNull(Date dateTime) {
		return !isNull(dateTime);
	}

	/**
	 * <p>将当前系统时间转为yyyy-MM-dd HH:mm:ss格式。</p>
	 * @return 格式为yyyy-MM-dd HH:mm:ss的日期字符串。
	 */
	public static String getStrDateTime() {
		return String.format("%tF %<tT", new Date());
	}

	/**
	 * <p>将当前系统时间转换为yyyy-MM-dd格式。</p>
	 * @return 格式为yyyy-MM-dd的日期字符串。
	 */
	public static String getStrDay() {
		return String.format("%tF", new Date());
	}

	/**
	 * <p>将指定时间转为yyyy-MM-dd HH:mm:ss格式。</p>
	 * @param date 指定的时间。
	 * @return 格式为yyyy-MM-dd HH:mm:ss的日期字符串。
	 */
	public static String getStrDateTime(Date date) {
		return String.format("%tF %<tT", date);
	}

	/**
	 * <p>将指定时间转为yyyy-MM-dd HH:mm:ss格式。</p>
	 * @param calendar 指定的日历对象。
	 * @return 格式为yyyy-MM-dd HH:mm:ss的日期字符串。
	 */
	public static String getStrDateTime(Calendar calendar) {
		return String.format("%tF %<tT", calendar.getTime());
	}

	/**
	 * <p>将指定时间转换为yyyy-MM-dd格式。</p>
	 * @param date 指定的时间。
	 * @return 格式为yyyy-MM-dd的日期字符串。
	 */
	public static String getStrDay(Date date) {
		return String.format("%tF", date);
	}

	/**
	 * <p>将指定格式的日期字符串转换为Date对象。</p>
	 * @param source 指定的日期字符串。
	 * @param dateFormat 指定格式。
	 * @return 转换为的Date对象。
	 */
	public static Date convertDateStr(String source, String dateFormat){
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			date = format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * <p>将Date对象转换为指定格式的日期字符串</p>
	 * @param source Date对象。
	 * @param dateFormat 指定的日期格式。{@link com.util.utils.date.Time.java}
	 * @return 指定格式的日期字符串。
	 * @throws ParseException 解析异常，一般是日期格式错误。
	 */
	public static String convertDateStr(Date source, String dateFormat) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String date = format.format(source);
		return date;
	}

	/**
	 * <p>将毫秒数(Long)转换为date对象。</p>
	 * @param millis 毫秒数(Long)。
	 * @return date对象。
	 */
	public static Date converDateMs(Long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * <p>将毫秒数(String)转换为date对象。</p>
	 * @param millis 毫秒数(String)。
	 * @return date对象。
	 */
	public static Date converDateMs(String millis) {
		return converDateMs(Long.valueOf(millis));
	}

	/**
	 * <p>将毫秒数（Long）转换为指定格式的日期字符串。</p>
	 * @param millis 毫秒数(Long)。
	 * @param dateFormat 指定的日期格式。{@link com.util.utils.date.Time}
	 * @return 指定格式的日期字符串。
	 * @throws ParseException 解析异常，一般是日期格式错误。
	 */
	public static String converDateMs(Long millis, String dateFormat) throws ParseException {
		return convertDateStr(converDateMs(millis), dateFormat);
	}

	/**
	 * <p>将毫秒数（String）转换为指定格式的日期字符串。</p>
	 * @param millis 毫秒数(String)。
	 * @param dateFormat 指定的日期格式。 {@link com.util.utils.date.Time.java}
	 * @return 指定格式的日期字符串。
	 * @throws ParseException 解析异常，一般是日期格式错误。
	 */
	public static String converDateMs(String millis, String dateFormat) throws ParseException {
		return convertDateStr(converDateMs(millis), dateFormat);
	}

	/**
	 * <p>获取当天剩余的秒数。</p>
	 * @return 毫秒数。
	 */
	public static Integer getDaySurplusMillis() {
		Calendar calendar = Calendar.getInstance();
		return getDaySurplusMillis(calendar);
	}

	/**
	 * <p>获取指定那天剩余的秒数。</p>
	 * @param calendar 指定的日历对象。
	 * @return 毫秒数。
	 */
	public static Integer getDaySurplusMillis(Calendar calendar) {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return (24 - hour + 1) * 60 * 60 + (60 - minute + 1) * 60 + (60 - second);
	}

	/**
	 * <p>获取一天的小时数量。</p>
	 * @return 小时。
	 */
	public static int getDayHours() {
		return getDayHours(1);
	}

	/**
	 * <p>获取指定天数的小时数量</p>
	 * @param i 天数。
	 * @return 小时。
	 */
	public static int getDayHours(int i) {
		return 24 * i;
	}

	/**
	 * <p>获取一天的分钟数量</p>
	 * @return 分钟。
	 */
	public static Long getDayMinute() {
		return getDayMinute(1);
	}

	/**
	 * <p>获取指定天数的分钟数量</p>
	 * @param i 天数。
	 * @return 分钟。
	 */
	public static Long getDayMinute(int i) {
		return 24L * 60L * i;
	}

	/**
	 * <p>获取一天的秒数量</p>
	 * @return 秒。
	 */
	public static Long getDaySecond() {
		return getDaySecond(1);
	}

	/**
	 * <p>获取指定天数的秒数量</p>
	 * @param i 天数。
	 * @return 秒。
	 */
	public static Long getDaySecond(int i) {
		return 24L * 60 * 60 * i;
	}

	/**
	 * <p>获取一天的毫秒数量</p>
	 * @return 毫秒。
	 */
	public static Long getDayMillis() {
		return getDayMillis(1);
	}

	/**
	 * <p>获取指定天数的毫秒数量</p>
	 * @param i 天数。
	 * @return 毫秒。
	 */
	public static Long getDayMillis(int i) {
		return 24L * 60 * 60L * 1000 * i;
	}
	
	/**
	 * <p>设置指定Date对象的时，分，秒，毫秒为0。</p>
	 * @param source 指定的Date对象 。
	 * @return 设置之后的Date对象。
	 */
	public static Date setDMSZero(Date source){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * <p>设置指定Date对象的时，分，秒，毫秒为0,并且天数+1。</p>
	 * @param source 指定的Date对象 。
	 * @return 设置之后的Date对象。
	 */
	public static Date setDMSZeroByAddDay(Date source){
		return setDMSZeroByAddDay(source, 1);
		
	}
	
	/**
	 * <p>设置指定Date对象的时，分，秒，毫秒为0,并且天数+day。</p>
	 * @param source 指定的Date对象 。
	 * @param day 增加的天数。
	 * @return 设置之后的Date对象。
	 */
	public static Date setDMSZeroByAddDay(Date source,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * <p>获取两个时间的天数间隔</p>
	 * @param time1 时间1
	 * @param time2 时间2
	 * @return 天数间隔,如果时间是同一天，则返回1。
	 */
	public static int intervalTimeDay(Date time1,Date time2){
		int day = (int) ((time2.getTime()-time1.getTime())/1000/3600/24);
		if(day<0) day = -day;
		return day==0?1:day;
	}
	

}
