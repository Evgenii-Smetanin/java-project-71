package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Difference;

import java.util.Set;

public class FormatterService {
    public static String format(Set<Difference> differences, String format) throws JsonProcessingException {
        Formatter formatter;

        switch (format) {
            case "stylish":
                formatter = new StylishFormatter();
                break;
            case "plain":
                formatter = new PlainFormatter();
                break;
            case "json":
                formatter = new JsonFormatter();
                break;
            default:
                throw new IllegalArgumentException("Unknown format: " + format + ".");
        }

        return formatter.format(differences);
    }
}
