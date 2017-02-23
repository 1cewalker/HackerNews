package sb.hackernews.com.utils;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Created by Nathaniel James Lim on 29/12/2016.
 */

public class Helper {

    public static String getCommentedTime(long seconds){

        DateTime post = new DateTime(seconds*1000);
        DateTime now = new DateTime();
        Period period = new Period(post, now);

        PeriodFormatter formatter;

        if(period.getYears() != 0){
            formatter = new PeriodFormatterBuilder().appendYears().appendSuffix(" years ago").printZeroNever().toFormatter();
        }else if(period.getMonths() !=0){
            formatter = new PeriodFormatterBuilder().appendMonths().appendSuffix(" months ago").printZeroNever().toFormatter();
        }else if(period.getWeeks() !=0){
            formatter = new PeriodFormatterBuilder().appendWeeks().appendSuffix(" weeks ago").printZeroNever().toFormatter();
        }else if(period.getDays() !=0){
            formatter = new PeriodFormatterBuilder().appendDays().appendSuffix(" days ago").printZeroNever().toFormatter();
        }else if(period.getHours() !=0){
            formatter = new PeriodFormatterBuilder().appendHours().appendSuffix(" hours ago").printZeroNever().toFormatter();
        }else if(period.getMinutes() !=0){
            formatter = new PeriodFormatterBuilder().appendMinutes().appendSuffix(" mins ago").printZeroNever().toFormatter();
        }else{
            formatter = new PeriodFormatterBuilder().appendSeconds().appendSuffix(" seconds ago").printZeroNever().toFormatter();
        }

        return formatter.print(period);
    }
}
