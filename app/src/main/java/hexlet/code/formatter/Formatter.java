package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Difference;

import java.util.Set;

public interface Formatter {
    String format(Set<Difference> difference) throws JsonProcessingException;
}
