package hexlet.code.formatter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Difference;

import java.util.Set;

public final class JsonFormatter implements Formatter {
    @Override
    public String format(Set<Difference> difference) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(difference);
    }
}
