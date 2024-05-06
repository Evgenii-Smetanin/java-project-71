package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String pathToFile1, String pathToFile2) throws IOException {
        Path path1 = Paths.get(pathToFile1);
        Path path2 = Paths.get(pathToFile2);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type =
                new TypeReference<HashMap<String, Object>>() {
                };

        Map<String, Object> leftMap = mapper.readValue(Files.readAllLines(path1).get(0), type);
        Map<String, Object> rightMap = mapper.readValue(Files.readAllLines(path2).get(0), type);

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
