package shared

public class CpfCnpjUtils {
    public static String removeNonNumeric(String text) {
        if (text == null) return null
        return text?.replaceAll("\\D+","")
    }
}
