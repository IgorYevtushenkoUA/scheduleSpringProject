package com.example.faculty.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        System.out.println(getCurrentDays(2021, 12, 1));
        System.out.println(getLocalDate(2021, 1, 1, false));
    }

    public static List<Integer> getCurrentDays(int year, int month, int date) {
        List<Integer> days = new ArrayList<>();

        LocalDate initial = LocalDate.of(year, month, date);

        int dayOfMonth = initial.lengthOfMonth();
        int firstDayOfWeek = initial.getDayOfWeek().getValue();
        int weekOfMonth = (int) (Math.ceil((double) (dayOfMonth) / 7));
        int allDaysInMonthCalendar = 7 * weekOfMonth;
        while (firstDayOfWeek > 1) {
            days.add(null);
            firstDayOfWeek--;
        }
        for (int i = 1; i <= dayOfMonth; i++) {
            days.add(i);
        }
        for (int i = days.size(); i < allDaysInMonthCalendar; i++) {
            days.add(null);
        }
        return days;
    }

    public static LocalDate getLocalDate(Integer year, Integer month, Integer date, boolean next) {
        LocalDate localDate = LocalDate.now();
        if (month == null || year == null || date == null) {
            year = localDate.getYear();
            month = localDate.getMonth().getValue();
        } else if (next) {
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
        } else if (!next) {
            if (month == 1) {
                year--;
                month = 12;
            } else {
                month--;
            }
        }
        return LocalDate.of(year, month, date);
    }

}
