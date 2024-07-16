package net.mistiksdevs.literalura.network.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T convert(String data, Class<T> clazz) {
        try {
            return mapper.readValue(data, clazz);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
