package wisepanda.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static boolean isEmpty(String value) {
        return (value == null || value.isEmpty());
    }

    public static List<String> splitStr(String value) {
        return new ArrayList<>(Arrays.asList(value.split(",")));
    }

    public static List<String> splitStr(String value, String regex) {
        return new ArrayList<>(Arrays.asList(value.split(regex)));
    }
}
