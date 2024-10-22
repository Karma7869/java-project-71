package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        Map<String, Object> data1 = Parser.parse(filepath1);
        Map<String, Object> data2 = Parser.parse(filepath2);

        Map<String, Object> diff = buildDiff(data1, data2);

        switch (format) {
            case "stylish":
                return StylishFormatter.format(diff);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    private static Map<String, Object> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> diff = new LinkedHashMap<>();

        // Собираем все ключи и сортируем их
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        // Проходим по отсортированным ключам
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                // Если ключ есть только в первой карте
                diff.put("-" + key, value1);
            } else if (!data1.containsKey(key)) {
                // Если ключ есть только во второй карте
                diff.put("+" + key, value2);
            } else if ((value1 == null && value2 != null) || (value1 != null && !value1.equals(value2))) {
                // Если значения различаются
                diff.put("-" + key, value1);
                diff.put("+" + key, value2);
            } else {
                // Если значения одинаковые
                diff.put(key, value1);
            }
        }

        return diff;
    }

    private static boolean isNestedStructure(Object value) {
        return value instanceof Map;
    }

/*
    private static String valueToString(Object value) {
        if (value instanceof Map) {
            return value.toString();
        } else if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return value.toString();
    }

*/
}
