package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestDiffer {
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
        return Files.readString(filePath).trim().replace("\r\n", "\n");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testGenerateDefault(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertEquals(correctResultStylish, Differ.generate(filePath1, filePath2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testGenerateStylish(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertEquals(correctResultStylish, Differ.generate(filePath1, filePath2, STYLISH));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testGeneratePlain(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertEquals(correctResultPlain, Differ.generate(filePath1, filePath2, PLAIN));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void testGenerateJson(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertEquals(correctResultJson, Differ.generate(filePath1, filePath2, JSON));
    }
}
