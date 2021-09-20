package library;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Date class contains year, month, and day.
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

   /**
    * Created Date obj for Current Date
    */
   public Date(){
      this.day = Calendar.getInstance().get(Calendar.DATE);
      this.year = Calendar.getInstance().get(Calendar.YEAR);
      this.month = Calendar.getInstance().get(Calendar.MONTH) + 1;
   }

   /**
    * Method takes “mm/dd/yyyy” and creates a Date object.
    * @param date
    * @return Date obj
    */
   public Date(String date) {
      StringTokenizer list = new StringTokenizer(date, "/");
      int dateList [] = new int[3];
      int i = 0;
      while(i < dateList.length){
         dateList[i] = Integer.parseInt(list.nextToken());
         i++;
      }
      this.year = dateList[2];
      this.day = dateList[1];
      this.month = dateList[0];
   }

   /**
    * Checks if date is valid or not
    * @return true if date is valid or else returns false.
    */
   public boolean isValid() {
      if (this.year >= THE_EIGHTYS && (this.month >= JAN && this.month <= DEC)){ // valid date
         if (isLeapYear(this.year)){ // leap year
            if (month != FEB && day == (30 + month % 2)) return true;
            if (month == FEB && day == FEB_LEAP_DAYS) return true;
         }
         else {// not leap year
            if(month != FEB && day == ( 30 + month % 2)) return true;
            if (month == FEB && day == FEB_NONLEAP_DAYS) return true;
         }
      }
      return false; // not valid date
   }

   /**
    * Checks if year is Leap year.
    * @param year
    * @return true if leap year else false
    */
   private boolean isLeapYear(int year){
      if (year % QUADRENNIAL == 0){
         if (year % CENTENNIAL == 0){
            if (year % QUATERCENTENNIAL == 0) return true;
         }
         else return true;
      }
      return false;
   }

   @Override
   public int compareTo(Date date) {
      // need the method to sort by release Date
      // case - duplicates albums in collection

      return 0;
   }

   public void printDate(){
      System.out.print(this.month + "/" + this.day + "/" + this.year);
   }
}