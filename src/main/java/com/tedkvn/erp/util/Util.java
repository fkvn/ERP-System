package com.tedkvn.erp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static String redactPassword(String json) {
        // Regex to match "password" key and its value, handling potential whitespace
        Pattern pattern = Pattern.compile("(\"\\s*password\\s*\"\\s*:\\s*\")([^\",]+)(\")");
        Matcher matcher = pattern.matcher(json);

        return matcher.replaceAll("$1********$3");
    }
}
