package hexlet.code;

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

        return Formatter.format(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> diff = new LinkedHashMap<>();

        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                diff.put("-" + key, value1);
            } else if (!data1.containsKey(key)) {
                diff.put("+" + key, value2);
            } else if ((value1 == null && value2 != null) || (value1 != null && !value1.equals(value2))) {
                diff.put("-" + key, value1);
                diff.put("+" + key, value2);
            } else {
                diff.put(key, value1);
            }
        }

        return diff;
    }
}

