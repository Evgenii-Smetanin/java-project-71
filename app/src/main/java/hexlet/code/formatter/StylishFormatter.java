package hexlet.code.formatter;

import hexlet.code.Difference;

import java.util.Set;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        StringBuilder sb = new StringBuilder("{\n");
        String line = "  %s %s: %s\n";
        difference.forEach(d -> {
            String operation;

            switch (d.getOperation()) {
                case ADDED:
                    operation = "+";
                    break;
                case DELETED:
                    operation = "-";
                    break;
                case CHANGED:
                    operation = "+-";
                    break;
                case UNCHANGED:
                    operation = " ";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operation: '" + d.getOperation() + "'.");
            }

            if (operation.equals("+-")) {
                sb.append(String.format(line,
                        "-",
                        d.getKey(),
                        d.getLeftVal() == null ? "null" : d.getLeftVal().toString()));
                sb.append(String.format(line,
                        "+",
                        d.getKey(),
                        d.getRightVal() == null ? "null" : d.getRightVal().toString()));
            } else {
                sb.append(String.format(
                        line, operation, d.getKey(), d.getLeftVal().toString()));
            }
        });
        sb.append("}");
        return sb.toString();
    }
}
