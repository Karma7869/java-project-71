package hexlet.code.formatters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, Object> diff) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(diff);
    }
}
