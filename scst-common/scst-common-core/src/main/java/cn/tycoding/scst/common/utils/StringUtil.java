package cn.tycoding.scst.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tycoding
 * @date 2019-06-19
 */
public class StringUtil {

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
