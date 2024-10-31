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

    @Test
    public void testDifferPlainFormat() throws Exception {
        Path filePath1 = Path.of("src/test/resources/filepath1.json");
        Path filePath2 = Path.of("src/test/resources/filepath2.json");

        String expected = """
        Property 'chars2' was updated. From [complex value] to false
        Property 'checked' was updated. From false to true
        Property 'default' was updated. From null to [complex value]
        Property 'id' was updated. From 45 to null
        Property 'key1' was removed
        Property 'key2' was added with value: 'value2'
        Property 'numbers2' was updated. From [complex value] to [complex value]
        Property 'numbers3' was removed
        Property 'numbers4' was added with value: [complex value]
        Property 'obj1' was added with value: [complex value]
        Property 'setting1' was updated. From 'Some value' to 'Another value'
        Property 'setting2' was updated. From 200 to 300
        Property 'setting3' was updated. From true to 'none'
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), "plain");
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    public void testDifferJsonFormat() throws Exception {
        Path filePath1 = Path.of("src/test/resources/filepath1.json");
        Path filePath2 = Path.of("src/test/resources/filepath2.json");

        String expected = """
        "chars1": [
            "a",
            "b",
            "c"
          ],
          "-chars2": [
            "d",
            "e",
            "f"
          ],
          "+chars2": false,
          "-checked": false,
          "+checked": true,
          "+default": [
            "value1",
            "value2"
          ],
          "-id": 45,
          "-key1": "value1",
          "+key2": "value2",
          "numbers1": [
            1,
            2,
            3,
            4
          ],
          "-numbers2": [
            2,
            3,
            4,
            5
          ],
          "+numbers2": [
            22,
            33,
            44,
            55
          ],
          "-numbers3": [
            3,
            4,
            5
          ],
          "+numbers4": [
            4,
            5,
            6
          ],
          "+obj1": {
            "nestedKey": "value",
            "isNested": true
          },
          "-setting1": "Some value",
          "+setting1": "Another value",
          "-setting2": 200,
          "+setting2": 300,
          "-setting3": true,
          "+setting3": "none"
        }
        """;

        String result = Differ.generate(filePath1.toString(), filePath2.toString(), "json");
        assertEquals(expected.trim(), result.trim());
    }
}
