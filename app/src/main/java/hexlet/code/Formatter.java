package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, Object> diff, String format) {
        switch (format) {
            case "plain":
                return PlainFormatter.format(diff);
            case "stylish":
                return StylishFormatter.format(diff);
            case "json":
                return JsonFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }
}


