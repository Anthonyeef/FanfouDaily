package io.github.anthonyeef.fanfoudaily.Utils;

import io.github.anthonyeef.fanfoudaily.extras.Constants;

/**
 * Created by anthonyeef on 10/17/15.
 */
public class UrlUtils {
    public static String getRequestUrlHead() {
        return Constants.URL_BASE;
    }
    public static String getRequestUrlFanfouDailyHead() {
        return Constants.URL_BASE;
    }

    public static String getRequestUrlFanfouWeekly() {
        return Constants.URL_BASE
                + Constants.URL_TEMP2
                + Constants.URL_WEEKLY;
    }
    public static String getRequestUrlDailyEnd() {
        return Constants.URL_DAILY+Constants.URL_END;
    }
    public static String getRequestUrlWeeklyEnd() {
        return Constants.URL_WEEKLY+Constants.URL_END;
    }
}
