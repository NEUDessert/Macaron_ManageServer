package Utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentTime {
    private String time;
    private static GetCurrentTime getCurrentTime;

    private GetCurrentTime() {
    }

    public static GetCurrentTime singleGetCurrentTime() {
        if(getCurrentTime == null) {
            getCurrentTime = new GetCurrentTime();
            return getCurrentTime;
        } else {
            return getCurrentTime;
        }
    }

    public String getTime() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String t = nowTime+"";
        String x = t.substring(11,13);
        this.time = sdFormatter.format(nowTime);
        StringBuilder stringBuilder = new StringBuilder(time);
        stringBuilder.replace(11,13,x);
        this.time = stringBuilder.toString();
        return time;
    }
    public String getTime(long tt) throws ParseException {
        Date nowTime = new Date(tt);
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String t = nowTime+"";
        String x = t.substring(11,13);
        this.time = sdFormatter.format(nowTime);
        StringBuilder stringBuilder = new StringBuilder(time);
        stringBuilder.replace(11,13,x);
        this.time = stringBuilder.toString();
        return time;
    }
}
