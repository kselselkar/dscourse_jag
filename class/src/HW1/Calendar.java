package HW1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * File Name: Calendar.java
 * Calendar concrete class
 * <p>
 * To Compile: IntUtil.java RandomInt.java  CalendarBase.java Calendar.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2018
 */

class Calendar extends CalendarBase {
    //You can have any number of private variables
    //You can have any number of private functions


    static String[] yearsData = null;

    static Map<String, String> daysMap = new HashMap<>();

    static Map<String, Integer> monthsMap = new HashMap<>();

    Calendar() {
        super();
        loadData();
        testBench();
    }


    private static void loadData() {
        loadYearsData();
        loadMonthsData();
        loadDaysData();
    }

    private static void loadYearsData() {
        try {
            InputStream is = Thread.currentThread().getClass().getResourceAsStream("/HW1/years_data.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String st = br.readLine();
            yearsData = st.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                isLeap = year % 400 == 0;
            } else
                isLeap = true;
        }
        return isLeap;
    }

    private static void loadMonthsData() {
        try {
            InputStream is = Thread.currentThread().getClass().getResourceAsStream("/HW1/months_data.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String st;
            while ((st = br.readLine()) != null) {
                String[] month = st.split(" ");
                int i = 1;
                String monthIndex = "";
                for (String s : month) {
                    try {
                        int monthNumber = Integer.parseInt(s);
                        monthsMap.put(monthIndex + ":" + i, monthNumber);
                        i = i + 1;
                    } catch (NumberFormatException e) {
                        // e.printStackTrace();
                        monthIndex = s;
                    }

                }
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    private static String findYearCode(int year) {
        for (String s : yearsData) {
            if (s.startsWith("" + year)) {
                return s.replaceAll("" + year, "");
            }
        }
        return "";
    }

    private static Map<String, String> loadDaysData() {
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int k = 0; k <= 6; k++) {
            for (int i = k; i < 31 + k; i++) {
                int index = i % 7;
                daysMap.put((k + 1) + ":" + (k >= 1 ? ((i - k) + 1) : (i + 1)), days[index]);
            }
        }
        return daysMap;
    }


    @Override
    protected void computeDay() {
        alg();
    }

    private void alg() {
        String yearCode = findYearCode(year);
        Integer monthCode = monthsMap.get(yearCode + ":" + month);
        String dayCode = daysMap.get(monthCode + ":" + date);
        for (Days days : Days.values()) {
            if (days.toString().equals(dayCode)) {
                if (isLeapYear(year) && date > 29) {
                    day = Days.Error;
                } else {
                    day = days;
                }
                break;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Calendar problem STARTS");
        Calendar m = new Calendar();
        m.alg();
        System.out.println("Calendar problem ENDS");
    }
}
