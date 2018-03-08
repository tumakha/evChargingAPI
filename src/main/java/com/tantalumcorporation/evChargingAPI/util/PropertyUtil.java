package com.tantalumcorporation.evChargingAPI.util;

import static java.util.Arrays.stream;

import java.lang.reflect.Field;
import org.springframework.beans.BeanUtils;

/**
 * @author Yuriy Tumakha
 */
public class PropertyUtil {

  public static void copyNonNullProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target, getNullProperties(source));
  }

  private static String[] getNullProperties(Object object) {
    return stream(object.getClass().getDeclaredFields())
        .filter(field -> getFieldValue(object, field) == null)
        .map(Field::getName)
        .toArray(String[]::new);
  }

  private static Object getFieldValue(Object object, Field field) {
    field.setAccessible(true);
    try {
      return field.get(object);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

}
