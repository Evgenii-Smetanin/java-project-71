package hexlet.code;

import org.junit.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.junit.Assert.assertEquals;

public class TestDiffer {
    private final String correctResult = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    @Test
    public void testGenerateJson() throws IOException {
        assertEquals(correctResult, generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }

    @Test
    public void testGenerateYaml() throws IOException {
        assertEquals(correctResult, generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
    }
}
