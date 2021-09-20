package library;

import java.util.Calendar;

public class RunProject1 {
   public static void main(String[] args) {
      Album album = new Album("When life throws u lemons", "Michael jackson", "Jazz", "05/01/2000");
      System.out.println(album.toString());
      System.out.println("Lmao world is ending in infinity yrs...");

      //Calendar cal = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
      int day = Calendar.getInstance().get(Calendar.DATE);
      System.out.println(day);
      int year = Calendar.getInstance().get(Calendar.YEAR);
      System.out.println(year);
      int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
      System.out.println(month);

   }
}
