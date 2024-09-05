package hexlete.code;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;

public class DifferTest {

    @Test
    public void testDiffer() throws Exception {
        Path filePath1 = Path.of("./filepath1.json");
        Path filePath2 = Path.of("./filepath2.json");

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
}
