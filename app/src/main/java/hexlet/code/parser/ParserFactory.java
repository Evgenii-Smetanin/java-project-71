package hexlet.code.parser;

public class ParserFactory {
    public static Parser getParser(String ext) {
        switch (ext) {
            case "json":
                return new JsonParser();
            case "yml":
            case "yaml":
                return new YamlParser();
            default:
                throw new IllegalArgumentException("Unsupported extension: " + ext + ".");
        }
    }
}
