package hexlete.code;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;

public class DifferTest {
/*
    @Test
    public void testDifferJson() throws Exception {

        Path filePath1 = Path.of("src/test/resources/filepath1.json");
        Path filePath2 = Path.of("src/test/resources/filepath2.json");

        String expected = """
                {
                  - follow: false
                    host: "hexlet.io"
                  - proxy: "123.234.53.22"
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;


        String result = Differ.generate(filePath1.toString(), filePath2.toString());


        assertEquals(expected.trim(), result.trim());
    }

    @Test
    public void testDifferYaml() throws Exception {

        Path filePath1 = Path.of("src/test/resources/filepath1.yml");
        Path filePath2 = Path.of("src/test/resources/filepath2.yml");

        String expected = """
                {
                  - follow: false
                    host: "hexlet.io"
                  - proxy: "123.234.53.22"
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;


        String result = Differ.generate(filePath1.toString(), filePath2.toString());


        assertEquals(expected.trim(), result.trim());
    }

    @Test
    public void testIdenticalFiles() throws Exception {

        Path filePath1 = Path.of("src/test/resources/identicalFile1.json");
        Path filePath2 = Path.of("src/test/resources/identicalFile2.json");

        String expected = "{\n}";


        String result = Differ.generate(filePath1.toString(), filePath2.toString());


        assertEquals(expected, result);
    }
*/
    @Test
    public void testDifferJsonWithNestedStructure() throws Exception {
        Path filePath1 = Path.of("src/test/resources/filepath1.json");
        Path filePath2 = Path.of("src/test/resources/filepath2.json");

        String expected = """
                    {
                        chars1: [a, b, c]
                      - chars2: [d, e, f]
                      + chars2: false
                      - checked: false
                      + checked: true
                      - default: null
                      + default: [value1, value2]
                      - id: 45
                      + id: null
                      - key1: value1
                      + key2: value2
                        numbers1: [1, 2, 3, 4]
                      - numbers2: [2, 3, 4, 5]
                      + numbers2: [22, 33, 44, 55]
                      - numbers3: [3, 4, 5]
                      + numbers4: [4, 5, 6]
                      + obj1: {nestedKey=value, isNested=true}
                      - setting1: Some value
                      + setting1: Another value
                      - setting2: 200
                      + setting2: 300
                      - setting3: true
                      + setting3: none
                    }
                    """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), "stylish");
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    public void testDifferYamlWithNestedStructure() throws Exception {
        Path filePath1 = Path.of("src/test/resources/filepath1.yml");
        Path filePath2 = Path.of("src/test/resources/filepath2.yml");

        String expected = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }
            """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), "stylish");

        assertEquals(expected.trim(), result.trim());
    }

}
