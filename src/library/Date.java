package library;

import java.util.Calendar;

/**
 * Date class contains year, month, and day.
 * @author Lipika
 */
public class Date implements Comparable<Date> {
   private int year;
   private int month;
   private int day;

   public Date(){
      Date date = new Date(Calendar.getInstance().toString());
   }
   /**
    * Method takes “mm/dd/yyyy” and creates a Date object.
    * @param date
    * @return Date obj
    */
   public Date(String date) {}

   /**
    * Creates an object with today’s date (see Calendar class)
    */
   public Date() {

   }
   public boolean isValid() {
   return false;
   }

   @Override
   public int compareTo(Date date) {
      return 0;
   }
   //cannot use System.out statements
//implement constructors and methods
//override Java compareTo - use for sorting by release dates
//isValid()
   //Design testcases for this method with a testbed main - check "SD ground rules" in project doc
   //invalid
   //year less than 1980
   //date beyond todays
   //Jan, March, May, July, Aug, Oct, Dec - 31 days
   //April, June, Sept, Nov - 30 days
   //Feb - 28 days (non-leap year) & 29 days (leap year)
   //**DO NOT** use *magic numbers
   //Defining identifiers:
   public static final int QUADRENNIAL = 4;
   public static final int CENTENNIAL = 100;
   public static final int QUATERCENTENNIAL = 400;
   public static final int THE_EIGHTYS = 1980;
//Defining a **leap year:
//Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
//Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
//Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
//Step 4. The year is a leap year.
//Step 5. The year is not a leap year.***
}