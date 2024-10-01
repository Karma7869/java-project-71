package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws IOException {
        String fileContent = Files.readString(Path.of(filePath));
        if (filePath.endsWith(".json")) {
            return parseJson(fileContent);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return parseYaml(fileContent);
        } else {
            throw new IOException("Unsupported file format: " + filePath);
        }
    }

    private static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Map.class);
    }

    private static Map<String, Object> parseYaml(String content) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(content, Map.class);
    }
}
