package com.co.manuel.SpringBatchTasklet.helpers;

import java.util.List;

public class CastHelper {

  @SuppressWarnings("unchecked")
  public static <T> List<T> castList(Object obj, Class<T> clazz) {

    if (!(obj instanceof List<?> list)) {
      throw new IllegalArgumentException("Not a List");
    }

    for (Object item : list) {
      if (!clazz.isInstance(item)) {
        throw new IllegalArgumentException(
            "List contains invalid type: " + item.getClass());
      }
    }

    return (List<T>) list;
  }
}
