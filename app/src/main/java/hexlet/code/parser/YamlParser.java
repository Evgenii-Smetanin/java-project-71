package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class YamlParser implements Parser {
    @Override
    public Map<String, Object> parse(String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, Object>> type =
                new TypeReference<HashMap<String, Object>>() {
                };

        return mapper.readValue(Files.readAllBytes(path), type);
    }
}
