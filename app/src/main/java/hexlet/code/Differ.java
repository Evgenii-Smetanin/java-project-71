package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2, String format) throws IOException {
        String ext = pathToFile1.substring(pathToFile1.indexOf('.') + 1);
        String ext2 = pathToFile1.substring(pathToFile2.indexOf('.') + 1);
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
