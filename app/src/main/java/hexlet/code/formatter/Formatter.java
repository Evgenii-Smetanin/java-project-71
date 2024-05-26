package hexlet.code.formatter;

import hexlet.code.Difference;

import java.util.Set;

public interface Formatter {
    String format(Set<Difference> difference);
}
