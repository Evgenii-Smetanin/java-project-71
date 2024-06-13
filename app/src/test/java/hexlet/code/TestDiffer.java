package hexlet.code;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.Assert.assertEquals;

public class TestDiffer {
    private final String correctResult = String.join("\n",
            Files.readAllLines(Path.of("src/test/resources/correctResult.txt")));

    public TestDiffer() throws IOException {
    }

    @Test
    public void testGenerateFromJson() throws IOException {
        assertEquals(correctResult,
                generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
    }

    @Test
    public void testGenerateFromYaml() throws IOException {
        assertEquals(correctResult,
                generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
    }

    @Test
    public void testGeneratePlain() throws IOException {
        assertEquals(
                String.join(
                        "\n",
                        Files.readAllLines(Path.of("src/test/resources/correctResultPlain.txt"))),
                generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "plain"));
    }

    @Test
    public void testGenerateJson() throws IOException {
        assertEquals(
                String.join(
                        "\n",
                        Files.readAllLines(Path.of("src/test/resources/correctResultJson.txt"))),
                generate("src/test/resources/file1.json",
                        "src/test/resources/file2.json",
                        "json"));
    }
}
