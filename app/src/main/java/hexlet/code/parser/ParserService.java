package hexlet.code.parser;

import java.io.IOException;
import java.util.Map;

public class ParserService {
    public static Map<String, Object> parse(String content, String parserType) throws IOException {
        Parser parser;

        switch (parserType) {
            case "json":
                parser = new JsonParser();
                break;
            case "yml":
            case "yaml":
                parser = new YamlParser();
                break;
            default:
                throw new IllegalArgumentException("Unsupported extension: " + parserType + ".");
        }

        return parser.parse(content);
    }
}
