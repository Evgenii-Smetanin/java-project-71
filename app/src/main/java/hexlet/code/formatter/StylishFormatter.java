package hexlet.code.formatter;

import hexlet.code.Difference;
import hexlet.code.Status;

import java.util.Set;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        StringBuilder sb = new StringBuilder("{\n");
        String line = "  %s %s: %s\n";
        difference.forEach(d -> {
            if (d.getOperation().equals(Status.CHANGED)) {
                sb.append(String.format(line,
                        "-",
                        d.getKey(),
                        d.getLeftVal() == null ? "null" : d.getLeftVal().toString()));
                sb.append(String.format(line,
                        "+",
                        d.getKey(),
                        d.getRightVal() == null ? "null" : d.getRightVal().toString()));
            } else {
                sb.append(String.format(line, d.getOperation(), d.getKey(), d.getLeftVal().toString()));
            }
        });
        sb.append("}");
        return sb.toString();
    }
}
