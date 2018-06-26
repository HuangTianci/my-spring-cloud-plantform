package com.huangtianci.commonfunction.utils;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * @author Huang Tianci
 * @date 2017/11/23
 * 时间格式化工具类
 */
public class DateFormatUtil {

	public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	public static final String PATTERN_ISO_ON_SECOND = "yyyy-MM-dd'T'HH:mm:ssZZ";
	public static final String PATTERN_ISO_ON_DATE = "yyyy-MM-dd";

	public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DEFAULT_NOSPCE_SECOND = "yyyyMMddHHmmss";

	public static final FastDateFormat ISO_FORMAT = FastDateFormat.getInstance(PATTERN_ISO);
	public static final FastDateFormat ISO_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_SECOND);
	public static final FastDateFormat ISO_ON_DATE_FORMAT = FastDateFormat.getInstance(PATTERN_ISO_ON_DATE);

	public static final FastDateFormat DEFAULT_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT);
	public static final FastDateFormat DEFAULT_ON_SECOND_FORMAT = FastDateFormat.getInstance(PATTERN_DEFAULT_ON_SECOND);

	public static String formatDate( String pattern,  Date date) {
		return FastDateFormat.getInstance(pattern).format(date);
	}

	public static String formatDate( String pattern, long date) {
		return FastDateFormat.getInstance(pattern).format(date);
	}

	public static String formatDuration( Date startDate,  Date endDate) {
		return DurationFormatUtils.formatDurationHMS(endDate.getTime() - startDate.getTime());
	}

	public static String formatDuration(long durationMillis) {
		return DurationFormatUtils.formatDurationHMS(durationMillis);
	}

	public static String formatDurationOnSecond( Date startDate,  Date endDate) {
		return DurationFormatUtils.formatDuration(endDate.getTime() - startDate.getTime(), "HH:mm:ss");
	}

	public static String formatDurationOnSecond(long durationMillis) {
		return DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss");
	}

}
