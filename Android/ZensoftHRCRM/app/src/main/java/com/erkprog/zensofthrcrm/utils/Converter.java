package com.erkprog.zensofthrcrm.utils;

import java.util.Arrays;
import java.util.List;

public class Converter {

  private static String SEPARATOR = "_separator_";

  public static String convertListToString(List<String> stringList) {

    StringBuffer stringBuffer = new StringBuffer();
    for (String str : stringList) {
      stringBuffer.append(str).append(SEPARATOR);
    }

    int lastIndex = stringBuffer.lastIndexOf(SEPARATOR);
    stringBuffer.delete(lastIndex, lastIndex + SEPARATOR.length() + 1);

    return stringBuffer.toString();
  }

  public static List<String> convertStringToList(String str) {
    return Arrays.asList(str.split(SEPARATOR));
  }

}
