package hexlet.code.formatter;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        switch (format) {
            case "stylish":
                return new StylishFormatter();
            default:
                throw new IllegalArgumentException("Unknown format: " + format + ".");
        }
    }
}
