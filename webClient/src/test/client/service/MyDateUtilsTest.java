package client.service;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static client.service.MyDateUtils.*;
import static org.junit.Assert.assertEquals;

public class MyDateUtilsTest {

    @Test
    public void testSearshRange() throws Exception {
        Date firstBirthDate = ConvertToUtilDate("2009-10-23");
        Date lastBirthDate = ConvertToUtilDate("2009-10-23");
        String result = SearshRange(firstBirthDate, lastBirthDate);
        assertEquals(result, "for range date: '23-10-2009' - '23-10-2009'");

        firstBirthDate = ConvertToUtilDate("1985-04-03");
        lastBirthDate = ConvertToUtilDate("2009-10-23");
        result = SearshRange(firstBirthDate, lastBirthDate);
        assertEquals(result, "for range date: '03-04-1985' - '23-10-2009'");

        firstBirthDate = ConvertToUtilDate("2015-12-14");
        result = SearshRange(firstBirthDate, null);
        assertEquals(result, "for date: '14-12-2015'");
    }

    @Test
    public void testConvertToSQLDate() throws Exception {
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date testDate = ConvertToSQLDate("2015-12-14");
        String stribgDate = myDateFormat.format(testDate);
        assertEquals(stribgDate, "2015-12-14");
    }

    @Test
    public void testConvertToUtilDate() throws Exception {
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = ConvertToUtilDate("2015-12-14");
        String stribgDate = myDateFormat.format(testDate);
        assertEquals(stribgDate, "2015-12-14");
    }
}