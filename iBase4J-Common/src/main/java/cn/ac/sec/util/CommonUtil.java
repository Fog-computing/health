package cn.ac.sec.util;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
    private static Calendar calendar = Calendar.getInstance ();

    public static int getAge(Date date) {
        calendar.setTime (new Date ());
        Integer now = calendar.get (Calendar.YEAR);
        calendar.setTime (date);
        Integer birth = calendar.get (Calendar.YEAR);
        return now - birth;
    }

    public static Date getDay(Date date) {
        calendar.setTime (date);
        calendar.set (Calendar.HOUR_OF_DAY, 0);
        calendar.set (Calendar.MINUTE, 0);
        calendar.set (Calendar.MILLISECOND, 0);
        calendar.set (Calendar.SECOND, 0);
        return calendar.getTime ();
    }

    public static Date getEndDay(Date date) {
        calendar.setTime (date);
        calendar.set (Calendar.HOUR_OF_DAY, 23);
        calendar.set (Calendar.MINUTE, 59);
        calendar.set (Calendar.SECOND, 59);
        return calendar.getTime ();
    }

    public static Date nowFallDownTime(Date date) {
        Calendar now = Calendar.getInstance ();
        calendar.setTime (date);
        calendar.set (now.get (Calendar.YEAR), now.get (Calendar.MONTH), now.get (Calendar.DATE));
        return calendar.getTime ();
    }

    public static Date getYesterDay(Date date) {
        return new Date (date.getTime () - 24 * 60 * 60 * 1000);
    }

    public static void main(String args[]) {
        System.out.println (new Date (1512489600000L));
    }
}
