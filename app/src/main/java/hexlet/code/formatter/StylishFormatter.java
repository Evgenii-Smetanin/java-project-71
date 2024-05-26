package hexlet.code.formatter;

import hexlet.code.Difference;

import java.util.Set;

public class StylishFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        StringBuilder sb = new StringBuilder("{\n");
        String line = "  %s %s: %s\n";
        difference.forEach(d -> {
            if (d.getOperation().equals("-+")) {
                sb.append(String.format(line, "-", d.getKey(), d.getLeftVal()));
                sb.append(String.format(line, "+", d.getKey(), d.getRightVal()));
            } else {
                sb.append(String.format(line, d.getOperation(), d.getKey(), d.getLeftVal()));
            }
        });
        sb.append("}");
        return sb.toString();
    }
}
