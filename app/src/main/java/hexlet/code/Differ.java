package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.util.Map;

public class Differ {

    public static Map<String, Object> readJsonFile(String filepath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filepath), Map.class);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> data1 = readJsonFile(filepath1);
        Map<String, Object> data2 = readJsonFile(filepath2);

        return buildDiff(data1, data2);
    }

    private static String buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(valueToString(value1)).append("\n");
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(valueToString(value2)).append("\n");
            } else if (value1.equals(value2)) {
                result.append("    ").append(key).append(": ").append(valueToString(value1)).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(valueToString(value1)).append("\n");
                result.append("  + ").append(key).append(": ").append(valueToString(value2)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String valueToString(Object value) {
        return value instanceof String ? "\"" + value + "\"" : value.toString();
    }
}
