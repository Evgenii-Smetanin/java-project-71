package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {
    private static final String JSON_PATH_1 = "src/test/resources/file1.json";
    private static final String JSON_PATH_2 = "src/test/resources/file2.json";
    private static final String YAML_PATH_1 = "src/test/resources/file1.yaml";
    private static final String YAML_PATH_2 = "src/test/resources/file2.yaml";

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    private static String correctResultStylish;
    private static String correctResultPlain;
    private static String correctResultJson;

    public TestDiffer() {
    }

    @BeforeAll
    public static void init() throws IOException {
        correctResultStylish = readFixture("correctResult.txt");
        correctResultPlain = readFixture("correctResultPlain.txt");
        correctResultJson = readFixture("correctResultJson.txt");
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws IOException {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @Test
    void testGenerateDefaultFromJson() throws IOException {
        assertEquals(correctResultStylish, generate(JSON_PATH_1, JSON_PATH_2));
    }

    @Test
    void testGenerateDefaultFromYaml() throws IOException {
        assertEquals(correctResultStylish, generate(YAML_PATH_1, YAML_PATH_2));
    }

    @Test
    void testGenerateStylishFromJson() throws IOException {
        assertEquals(correctResultStylish, generate(JSON_PATH_1, JSON_PATH_2, STYLISH));
    }

    @Test
    void testGenerateStylishFromYaml() throws IOException {
        assertEquals(correctResultStylish, generate(YAML_PATH_1, YAML_PATH_2, STYLISH));
    }

    @Test
    void testGeneratePlainFromJson() throws IOException {
        assertEquals(correctResultPlain, generate(JSON_PATH_1, JSON_PATH_2, PLAIN));
    }

    @Test
    void testGeneratePlainFromYaml() throws IOException {
        assertEquals(correctResultPlain, generate(YAML_PATH_1, YAML_PATH_2, PLAIN));
    }

    @Test
    void testGenerateJsonFromJson() throws IOException {
        assertEquals(correctResultJson, generate(JSON_PATH_1, JSON_PATH_2, JSON));
    }

    @Test
    void testGenerateJsonFromYaml() throws IOException {
        assertEquals(correctResultJson, generate(YAML_PATH_1, YAML_PATH_2, JSON));
    }
}
