package org.lib;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * An implementation of serializer that use JSON format
 */
public class JsonSerializer {
    public String serialize(Serializable serializable) {
        try {
            return objectMapper.writeValueAsString(serializable);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(Class<T> cl, String serializedObject) throws DeserializationFailure {
        try {
            return objectMapper.readValue(serializedObject, cl);
        } catch (JsonProcessingException e) {
            throw new DeserializationFailure(e.getMessage());
        }
    }

    private final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper res = new ObjectMapper();
        res.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return res;
    }
}