package shared

import java.text.SimpleDateFormat

class CustomDateUtils {

    public static Date fromString(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
            return sdf.parse(dateStr)
        } catch (Exception exception) {
            return null
        }
    }
}
