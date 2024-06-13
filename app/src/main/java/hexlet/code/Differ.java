package hexlet.code;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2, String format) throws IOException {
        String ext = pathToFile1.substring(pathToFile1.indexOf('.') + 1);
        String ext2 = pathToFile1.substring(pathToFile2.indexOf('.') + 1);
        Set<Difference> differences = new TreeSet<>();
        Formatter formatter = FormatterFactory.getFormatter(format);

        if (!ext.equals(ext2)) {
            throw new IllegalArgumentException("Different file extensions: " + ext + ", " + ext2 + ".");
        }

        Parser parser = ParserFactory.getParser(ext);

        Map<String, Object> leftMap = parser.parse(pathToFile1);
        Map<String, Object> rightMap = parser.parse(pathToFile2);

        MapDifference<String, Object> mapDifference = Maps.difference(leftMap, rightMap);
        mapDifference.entriesOnlyOnLeft().forEach((k, v) ->
                differences.add(new Difference(k, "-", v)));
        mapDifference.entriesOnlyOnRight().forEach((k, v) ->
                differences.add(new Difference(k, "+", v)));
        mapDifference.entriesInCommon().forEach((k, v) ->
                differences.add(new Difference(k, " ", v)));
        mapDifference.entriesDiffering().forEach((k, v) ->
                differences.add(new Difference(k,
                        "-+", v.leftValue(),
                        v.rightValue())));

        return formatter.format(differences);
    }

    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        return generate(pathToFile1, pathToFile2, "stylish");
    }
}
