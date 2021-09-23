package library;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Date class creates Date obj and has respective Date methods.
 *
 * @author Lipika
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THE_EIGHTYS = 1980;
    public static final int JAN = 1;
    public static final int DEC = 12;
    public static final int FEB = 2;
    public static final int FEB_LEAP_DAYS = 29;
    public static final int FEB_NONLEAP_DAYS = 28;
    public static final int CURR_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    public static final int CURR_DAY = Calendar.getInstance().get(Calendar.DATE);
    public static final int CURR_MONTH = Calendar.getInstance().get(Calendar.MONTH) + 1;
    public static final int MONTH_DAYS = 30;
    public static final int MOD_TWO = 2;

    /**
     * Created Date obj for current date.
     */
    public Date() {
        this.day = CURR_DAY;
        this.year = CURR_YEAR;
        this.month = CURR_MONTH;
    }

    /**
     * Method takes “mm/dd/yyyy” and creates a Date object.
     *
     * @param date - Refers to release date of Album.
     */
    public Date(String date) {
        StringTokenizer list = new StringTokenizer(date, "/");
        int dateList[] = new int[3];
        int i = 0;
        while (i < dateList.length) {
            dateList[i] = Integer.parseInt(list.nextToken());
            i++;
        }
        this.year = dateList[2];
        this.day = dateList[1];
        this.month = dateList[0];
    }

    /**
     * Checks if date is valid or not.
     *
     * @return true if date is valid or else returns false.
     */
    public boolean isValid() {
        if (this.day > 0 && this.year >= THE_EIGHTYS && (this.month >= JAN && this.month <= DEC) && this.year <= CURR_YEAR) { // valid date
            if (this.year == CURR_YEAR) { // year matches CURR_YEAR
                if (this.month > CURR_MONTH || (this.month == CURR_MONTH && this.day > CURR_DAY)) {
                    return false;
                }
            }
            if (isLeapYear(this.year)) { // leap year
                if (month != FEB && day <= (MONTH_DAYS + month % MOD_TWO)) return true;
                if (month == FEB && day <= FEB_LEAP_DAYS) return true;
            } else {// not leap year
                if (month != FEB && day <= (MONTH_DAYS + month % MOD_TWO)) return true;
                if (month == FEB && day <= FEB_NONLEAP_DAYS) return true;
            }
        }
        return false; // not valid date
    }

    /**
     * Checks if year is leap year.
     *
     * @param year Refers to year Album was released.
     * @return true if leap year or else return false.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) return true;
            } else return true;
        }
        return false;
    }

    /**
     * Compares dates if one date is >= or < the other date.
     *
     * @param date - Date inputted by user.
     * @return 1 if date1 > date 2, 0 if date 1 = date 2 and -1 if date 1 < date 2
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.year) return 1;
        else if (this.year < date.year) return -1;

        // year matches
        if (this.month > date.month) return 1;
        else if (this.month < date.month) return -1;

        // month matches
        if (this.day > date.day) return 1;
        else if (this.day < date.day) return -1;

        return 0; // year matches case
    }

    /**
     * Gets date in String format.
     * @return String format of the date.
     */
    public String getDate() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Testbed main for Date class which tests isValid() method.
     *
     * @param args Refers to arguments.
     */
    public static void main(String[] args) {
        Date date = new Date("11/21/800");
        //Test Case #1
        if (!date.isValid()) System.out.println("Pass.");
        else System.out.println("Fail.");

        //Test Case #2
        Date date2 = new Date("2/29/2018");
        if (!date2.isValid()) System.out.println("Pass.");
        else System.out.println("Fail.");

        //Test Case #3
        Date date3 = new Date("13/20/2018");
        if (!date3.isValid()) System.out.println("Pass.");
        else System.out.println("Fail.");
    }
}
