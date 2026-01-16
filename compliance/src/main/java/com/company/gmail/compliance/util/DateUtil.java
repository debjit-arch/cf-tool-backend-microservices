package com.company.gmail.compliance.util;

import java.time.LocalDate;

public class DateUtil {
    public static String yesterday() {
        return LocalDate.now().minusDays(1).toString();
    }
}
