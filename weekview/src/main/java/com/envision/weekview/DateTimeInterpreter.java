package com.envision.weekview;

import java.util.Calendar;

public interface DateTimeInterpreter {
    String interpretDate(Calendar date);
    String interpretTime(int hour);
}
