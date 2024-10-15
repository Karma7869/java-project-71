package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StylishFormatter {

    public static String format(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder("{\n");

        Set<String> allKeys = new TreeSet<>(diff.keySet());

        for (String key : allKeys) {
            Object value = diff.get(key);

            if (value instanceof Map) {
                result.append(formatNestedStructure(key, (Map<String, Object>) value));
            } else {
                result.append("    ").append(key).append(": ").append(value).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatNestedStructure(String key, Map<String, Object> nestedValue) {
        StringBuilder nestedResult = new StringBuilder();
        nestedResult.append("    ").append(key).append(": {\n");

        for (Map.Entry<String, Object> entry : nestedValue.entrySet()) {
            nestedResult.append("        ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        nestedResult.append("    }\n");
        return nestedResult.toString();
    }
}
