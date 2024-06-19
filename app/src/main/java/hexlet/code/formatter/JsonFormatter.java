package hexlet.code.formatter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Difference;

import java.util.Set;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(difference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
