package hexlet.code;

import org.junit.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.junit.Assert.assertEquals;

public class TestDiffer {
    @Test
    public void testGenerate() throws IOException {
        String correctResult = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  +app file1.json file2.json verbose: true\n"
                + "}";

        assertEquals(generate("src/test/resources/file1.json", "src/test/resources/file2.json"), correctResult);
    }
}