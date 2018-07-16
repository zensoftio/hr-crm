package com.erkprog.zensofthrcrm.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateConverter {
  private static final String TAG = "DATE CONVERTER";

  public static Date getDate(String dateInString) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    Log.d(TAG, "getDate: input: " + dateInString);
    try {
      Date date = formatter.parse(dateInString);
      Log.d(TAG, "getDate: date" + date);
      Log.d(TAG, "getDate: " + "time zone : " + TimeZone.getDefault().getID());

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      Log.d(TAG, "getDate: year " + calendar.get(Calendar.YEAR));
      Log.d(TAG, "getDate: month" + calendar.get(Calendar.MONTH));
      Log.d(TAG, "getDate: day" + calendar.get(Calendar.DAY_OF_MONTH));
      Log.d(TAG, "getDate: Hour " + calendar.get(Calendar.HOUR_OF_DAY));
      Log.d(TAG, "getDate: minute" + calendar.get(Calendar.MINUTE));

      return date;

    } catch (ParseException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("wrong date format");
    }
  }

  public static String getDisplayDate(String dateFromResponse) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    try {
      //Convert String date from response to Date object
      Date date = getDate(dateFromResponse);

      //Convert to String to display in views
      return formatter.format(date);
    } catch (IllegalArgumentException e) {
      return dateFromResponse;
    }
  }

  public static String getFormattedDate(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    return formatter.format(date);
  }
}
