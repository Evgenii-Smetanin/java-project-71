package hexlet.code;

import hexlet.code.formatter.FormatterService;
import hexlet.code.parser.ParserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2, String format) throws IOException {
        String ext1 = getExt(pathToFile1);
        String ext2 = getExt(pathToFile2);

        if (!ext1.equals(ext2)) {
            throw new IllegalArgumentException("Different file extensions: " + ext1 + ", " + ext2 + ".");
        }

        Set<Difference> differences = new DifferenceFinder().findDifference(getData(pathToFile1), getData(pathToFile2));
        return FormatterService.format(differences, format);
    }

    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        return generate(pathToFile1, pathToFile2, "stylish");
    }

    private static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }

        String content = Files.readString(path);
        return ParserService.parse(content, getExt(filePath));
    }

    private static String getExt(String filePath) {
        if (filePath.lastIndexOf('.') == -1) {
            throw new IllegalArgumentException("File extension not found in path: " + filePath + ".");
        }

        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }
}
