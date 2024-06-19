package hexlet.code.formatter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Difference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        List<Map<String, Object>> differences = new ArrayList<>();

        difference.forEach(d -> {
            Map<String, Object> diff = new TreeMap();
            diff.put("key", d.getKey());
            String operation = "";

            switch (d.getOperation()) {
                case "+":
                    operation = "add";
                    break;
                case "-":
                    operation = "delete";
                    break;
                case "-+":
                    operation = "modify";
                    break;
                case " ":
                    operation = "unchanged";
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + d.getOperation());
            }

            diff.put("operation", operation);
            diff.put("value1", d.getLeftVal());

            if (operation.equals("modify")) {
                diff.put("value2", d.getRightVal());
            }

            differences.add(diff);
        });

        try {
            return new ObjectMapper().writeValueAsString(differences);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
