package lamvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: NortonL
 * Date: 7/15/11
 * Time: 1:20 PM
 */
public class DateUtil {
  public final static String INT_FORMAT = "yyyyMMdd";
  private static SimpleDateFormat _intSdf = new SimpleDateFormat(INT_FORMAT);

  public static int toInt(Date date) {
    String intFormatStr = _intSdf.format(date);

    return Integer.parseInt(intFormatStr);
  }

  public static Date fromInt(int intDate) throws ParseException {
    return _intSdf.parse(String.valueOf(intDate));
  }
}
