package hexlet.code;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class DifferenceFinder {
    public Set<Difference> findDifference(Map<String, Object> leftMap, Map<String, Object> rightMap) {
        Set<Difference> differences = new TreeSet<>();
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
        return differences;
    }
}
