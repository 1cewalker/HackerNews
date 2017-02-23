package sb.hackernews.com;

import org.joda.time.DateTimeUtils;
import org.junit.Test;

import sb.hackernews.com.utils.Helper;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTestTime {



    //Time Ago
    @Test
    public void checkTimeParser() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(1482950886000L);
        assertEquals("0 seconds ago",Helper.getCommentedTime(1482950886));
        DateTimeUtils.setCurrentMillisFixed(1483028888676L);
        assertEquals("3 hours ago",Helper.getCommentedTime(1483016087));
        assertEquals("1 days ago",Helper.getCommentedTime(1482877106));

    }


}