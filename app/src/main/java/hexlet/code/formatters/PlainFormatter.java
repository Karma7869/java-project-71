package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter {

    public static String format(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.startsWith("-") && !diff.containsKey("+" + key.substring(1))) {
                result.append("Property '").append(key.substring(1)).append("' was removed").append("\n");
            } else if (key.startsWith("+") && !diff.containsKey("-" + key.substring(1))) {
                result.append("Property '").append(key.substring(1)).append("' was added with value: ").append(formatValue(value)).append("\n");
            } else if (key.startsWith("-") && diff.containsKey("+" + key.substring(1))) {
                Object newValue = diff.get("+" + key.substring(1));
                result.append("Property '").append(key.substring(1)).append("' was updated. From ").append(formatValue(value))
                        .append(" to ").append(formatValue(newValue)).append("\n");
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof Iterable || value.getClass().isArray()) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}


