package hexlet.code.formatter;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        switch (format) {
            case "stylish":
                return new StylishFormatter();
            case "plain":
                return new PlainFormatter();
            default:
                throw new IllegalArgumentException("Unknown format: " + format + ".");
        }
    }
}
