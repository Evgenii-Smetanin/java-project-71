package hexlet.code.formatter;

import hexlet.code.Difference;

import java.util.Set;

public class PlainFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        StringBuilder sb = new StringBuilder();
        String updated = "Property '%s' was updated. From %s to %s\n";
        String added = "Property '%s' was added with value: %s\n";
        String deleted = "Property '%s' was removed\n";

        difference.forEach(d -> {
            if (sb.length() > 0) {
                sb.append("\n");
            }

            switch (d.getOperation()) {
                case "+":
                    sb.append(String.format(added, d.getKey(), d.getLeftVal()));
                    break;
                case "-":
                    sb.append(String.format(deleted, d.getKey(), d.getLeftVal()));
                    break;
                case "-+":
                    sb.append(String.format(updated, d.getKey(), d.getLeftVal(), d.getRightVal()));
                    break;
                case " ":
                    break;
                default:
                    throw new RuntimeException("Unknown operation: " + d.getOperation());
            }
        });

        return sb.toString();
    }
}
