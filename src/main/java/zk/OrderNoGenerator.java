package zk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNoGenerator {
    private static int count;
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    public static String next() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(new Date()) + ++count;
    }
}
