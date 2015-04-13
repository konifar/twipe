package com.konifar.twipe.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.ocpsoft.prettytime.PrettyTime;

public class DateUtil {

  // Wed Aug 29 17:12:58 +0000 2012
  private static final String FORMAT_EEE_MMM_D_HHMMSS_ZZ_YYYY = "EEE MMM d HH:mm:ss zz yyyy";

  public static Date twitterStringToDate(String date) {
    return stringToDate(date, FORMAT_EEE_MMM_D_HHMMSS_ZZ_YYYY);
  }

  private static Date stringToDate(String date, String format) {
    if (date == null) return null;
    ParsePosition pos = new ParsePosition(0);
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    return dateFormat.parse(date, pos);
  }

  public static String getRelativeTime(Date date) {
    if (date != null) {
      PrettyTime p = new PrettyTime();
      return p.format(date);
    } else {
      return "";
    }
  }
}
