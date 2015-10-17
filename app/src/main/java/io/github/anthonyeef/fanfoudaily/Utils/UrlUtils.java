package io.github.anthonyeef.fanfoudaily.Utils;

import io.github.anthonyeef.fanfoudaily.extras.UrlEndpoints;

/**
 * Created by anthonyeef on 10/17/15.
 */
public class UrlUtils {
    public static String getRequestUrlFanfouDaily() {
        return UrlEndpoints.URL_BASE
                + UrlEndpoints.URL_JSON
                + UrlEndpoints.URL_TEMP
                + UrlEndpoints.URL_DAILY
                + UrlEndpoints.URL_END;
    }

    public static String getRequestUrlFanfouWeekly() {
        return UrlEndpoints.URL_BASE
                + UrlEndpoints.URL_JSON
                + UrlEndpoints.URL_TEMP2
                + UrlEndpoints.URL_WEEKLY
                + UrlEndpoints.URL_END;
    }
}
