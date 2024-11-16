package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter {

    public static String format(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.startsWith("-") || key.startsWith("+")) {
                char prefix = key.charAt(0);
                String actualKey = key.substring(1);
                String prefixSymbol = prefix == '-' ? "- " : "+ ";
                result.append("  ").append(prefixSymbol).append(actualKey).append(": ")
                        .append(formatValue(value, 1)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(formatValue(value, 1)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value, int depth) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map) {
            return formatNestedStructureInOneLine((Map<String, Object>) value, depth + 1);
        }
        return value.toString();
    }

    private static String formatNestedStructureInOneLine(Map<String, Object> map, int indentLevel) {
        StringBuilder formatted = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            formatted.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(", ");
        }
        if (formatted.length() > 1) {
            formatted.setLength(formatted.length() - 2);
        }
        formatted.append("}");
        return formatted.toString();
    }
}
