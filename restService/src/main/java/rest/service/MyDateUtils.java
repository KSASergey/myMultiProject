package rest.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {

    /**
     * Generates the search range in the form of the string and converts the date to the form "dd-MM-yyyy"
     * @param firstBirthDate - the start date
     * @param lastBirthDate - the end Date
     * @return Returns a string type: "for date: '@firstBirthDate'"
     *         or "for range date: '@firstBirthDate' - '@lastBirthDate'" if lastBirthDate = null
     */
    public static String SearshRange(Date firstBirthDate, Date lastBirthDate) {
        DateFormat myDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String searshRange;
        if (lastBirthDate == null) {
            searshRange = "for date: '" + myDateFormat.format(firstBirthDate) + "'";
        }
        else {
            searshRange = "for range date: '" + myDateFormat.format(firstBirthDate) + "' - '"
                    + myDateFormat.format(lastBirthDate) + "'";
        }
        return searshRange;
    }

    /**
     * Converts the string date form "yyyy-MM-dd" to sql.Date
     * @param stringDate - the string date
     * @return Returns a sql.Date
     */
    public static java.sql.Date ConvertToSQLDate(String stringDate) throws ParseException {
        return new java.sql.Date (ConvertToUtilDate(stringDate).getTime());
    }

    /**
     * Converts the string date form "yyyy-MM-dd" to util.Date
     * @param stringDate - the string date
     * @return Returns a util.Date
     */
    public static Date ConvertToUtilDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }

}
