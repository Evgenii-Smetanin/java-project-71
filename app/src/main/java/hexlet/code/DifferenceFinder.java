package hexlet.code;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.UNCHANGED;

public final class DifferenceFinder {
    public Set<Difference> findDifference(Map<String, Object> leftMap, Map<String, Object> rightMap) {
        Set<Difference> differences = new TreeSet<>();
        MapDifference<String, Object> mapDifference = Maps.difference(leftMap, rightMap);
        mapDifference.entriesOnlyOnLeft().forEach((k, v) ->
                differences.add(new Difference(k, DELETED, v)));
        mapDifference.entriesOnlyOnRight().forEach((k, v) ->
                differences.add(new Difference(k, ADDED, v)));
        mapDifference.entriesInCommon().forEach((k, v) ->
                differences.add(new Difference(k, UNCHANGED, v)));
        mapDifference.entriesDiffering().forEach((k, v) ->
                differences.add(new Difference(k, CHANGED, v.leftValue(), v.rightValue())));
        return differences;
    }
}
