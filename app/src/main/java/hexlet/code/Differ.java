package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2, String format) throws IOException {
        boolean isPath1Valid = pathToFile1.lastIndexOf('.') != -1;
        boolean isPath2Valid = pathToFile2.lastIndexOf('.') != -1;

        if (!isPath1Valid && !isPath2Valid) {
            throw new IllegalArgumentException("File extensions not found in paths: "
                    + pathToFile1 + ", " + pathToFile2 + ".");
        } else if (!isPath1Valid || !isPath2Valid) {
            throw new IllegalArgumentException("File extension not found in path: "
                    + (isPath2Valid ? pathToFile1 : pathToFile2) + ".");
        }

        String ext = pathToFile1.substring(pathToFile1.lastIndexOf('.') + 1);
        String ext2 = pathToFile2.substring(pathToFile2.lastIndexOf('.') + 1);
        Formatter formatter = FormatterFactory.getFormatter(format);

        if (!ext.equals(ext2)) {
            throw new IllegalArgumentException("Different file extensions: " + ext + ", " + ext2 + ".");
        }

        Parser parser = ParserFactory.getParser(ext);
        return formatter.format(
                new DifferenceFinder().findDifference(
                        parser.parse(pathToFile1),
                        parser.parse(pathToFile2)));
    }

    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        return generate(pathToFile1, pathToFile2, "stylish");
    }
}
