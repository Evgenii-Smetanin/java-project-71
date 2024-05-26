package hexlet.code;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        String ext = pathToFile1.substring(pathToFile1.indexOf('.') + 1);
        String ext2 = pathToFile1.substring(pathToFile2.indexOf('.') + 1);

        if (!ext.equals(ext2)) {
            throw new IllegalArgumentException("Different file extensions: " + ext + ", " + ext2 + ".");
        }

        Parser parser = ParserFactory.getParser(ext);

        Map<String, Object> leftMap = parser.parse(pathToFile1);
        Map<String, Object> rightMap = parser.parse(pathToFile2);

        MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
        Map<String, Object> deleted = difference.entriesOnlyOnLeft();
        Map<String, Object> added = difference.entriesOnlyOnRight();
        Map<String, Object> common = difference.entriesInCommon();
        Map<String, MapDifference.ValueDifference<Object>> differing = difference.entriesDiffering();

        Map<String, String> lines = new HashMap<>();
        deleted.forEach((k, v) -> lines.put(k, "  - " + k + ": " + v + "\n"));
        added.forEach((k, v) -> lines.put(k, "  + " + k + ": " + v + "\n"));
        common.forEach((k, v) -> lines.put(k, "    " + k + ": " + v + "\n"));
        differing.forEach((k, v) -> lines.put(k, "  - " + k + ": " + v.leftValue() + "\n"
                + "  + " + k + ": " + v.rightValue() + "\n"));

        Set<String> keyset = new TreeSet<>();
        keyset.addAll(lines.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        keyset.stream().forEach(k -> sb.append(lines.get(k)));
        sb.append("}");

        return sb.toString();
    }
}
