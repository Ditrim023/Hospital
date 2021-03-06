package com.hospital.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Sergii Manko
 */
public final class Util {
  private Util() {
    throw new IllegalStateException("Utility class");
  }

  public static String getAuthorizedUserName() {
    final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getUsername();
  }

  public static String getRealDate(String dateFromForm) {
    String [] temp = dateFromForm.split("-");
    return temp[2] + "." + temp[1] + "." + temp[0];
  }
}
