package rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static rest.service.MyDateUtils.*;

public class MyDateUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDateUtilsTest.class);

    @Test
    public void testSearshRange() throws Exception {
        logger.debug("Start testSearshRange");
        Date firstBirthDate = ConvertToUtilDate("2009-10-23");
        Date lastBirthDate = ConvertToUtilDate("2009-10-23");
        String result = SearshRange(firstBirthDate, lastBirthDate);
        Assert.assertEquals(result, "for range date: '23-10-2009' - '23-10-2009'");

        firstBirthDate = ConvertToUtilDate("1985-04-03");
        lastBirthDate = ConvertToUtilDate("2009-10-23");
        result = SearshRange(firstBirthDate, lastBirthDate);
        Assert.assertEquals(result, "for range date: '03-04-1985' - '23-10-2009'");

        firstBirthDate = ConvertToUtilDate("2015-12-14");
        result = SearshRange(firstBirthDate, null);
        Assert.assertEquals(result, "for date: '14-12-2015'");
        logger.debug("Finish testSearshRange");
    }

    @Test
    public void testConvertToSQLDate() throws Exception {
        logger.debug("Start testConvertToSQLDate");
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date testDate = ConvertToSQLDate("2015-12-14");
        String stribgDate = myDateFormat.format(testDate);
        Assert.assertEquals(stribgDate, "2015-12-14");
        logger.debug("Finish testConvertToSQLDate");
    }

    @Test
    public void testConvertToUtilDate() throws Exception {
        logger.debug("Start testConvertToUtilDate");
        DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = ConvertToUtilDate("2015-12-14");
        String stribgDate = myDateFormat.format(testDate);
        Assert.assertEquals(stribgDate, "2015-12-14");
        logger.debug("Finish testConvertToUtilDate");
    }
}