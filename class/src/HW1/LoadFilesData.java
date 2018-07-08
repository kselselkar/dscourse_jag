package HW1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoadFilesData {

    static String[] yearsData = null;

    static Map<String, String> daysMap = new HashMap<>();

    static Map<String, Integer> monthsMap = new HashMap<>();

    protected static void loadYearsData() {
        try {
            InputStream is = Thread.currentThread().getClass().getResourceAsStream("/HW1/years_data.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String st = br.readLine();
            yearsData = st.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void loadMonthsData() {
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

    protected static String findYearCode(int year) {
        for (String s : yearsData) {
            if (s.startsWith("" + year)) {
                return s.replaceAll("" + year, "");
            }
        }
        return "";
    }

    protected static Map<String, String> loadDaysData() {
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int k = 0; k <= 6; k++) {
            for (int i = k; i < 31 + k; i++) {
                int index = i % 7;
                daysMap.put((k + 1) + ":" + (k > 1 ? ((i - k) + 1) : (i + 1)), days[index]);
            }
        }
        return daysMap;
    }

    public static void main(String[] args) throws IOException {
        int m = 4 ;
        int d = 7 ;
        int y = 2018 ;
        loadYearsData();
        loadMonthsData();
        loadDaysData();
        String yearCode = findYearCode(y);
        Integer monthCode = monthsMap.get(yearCode + ":" + m);
        String day = daysMap.get(monthCode + ":" + d);

        System.out.println( yearCode +" "+monthCode+" "+ day);
        //System.out.println(monthsMap);

    }
}
