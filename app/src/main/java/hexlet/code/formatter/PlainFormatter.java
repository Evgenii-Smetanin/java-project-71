package hexlet.code.formatter;

import hexlet.code.Difference;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PlainFormatter implements Formatter {
    private static final String UPD = "Property '%s' was updated. From %s to %s\n";
    private static final String ADD = "Property '%s' was added with value: %s\n";
    private static final String DEL = "Property '%s' was removed\n";

    @Override
    public String format(Set<Difference> difference) {
        StringBuilder sb = new StringBuilder();

        difference.forEach(d -> {
            switch (d.getOperation()) {
                case "+":
                    sb.append(String.format(ADD, d.getKey(), getDiffVal(d.getLeftVal())));
                    break;
                case "-":
                    sb.append(String.format(DEL, d.getKey(), getDiffVal(d.getLeftVal())));
                    break;
                case "-+":
                    sb.append(String.format(UPD, d.getKey(), getDiffVal(d.getLeftVal()), getDiffVal(d.getRightVal())));
                    break;
                case " ":
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + d.getOperation());
            }
        });

        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    private String getDiffVal(Object val) {
        if (val == null) {
            return "null";
        }

        if (val instanceof String) {
            return "'" + val + "'";
        }

        if (val instanceof Map || val instanceof List) {
            return "[complex value]";
        }

        return val.toString();
    }
}
