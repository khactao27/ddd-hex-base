package tech.ibrave.metabucket.infra.persistence.jpa.entity.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class Map2StringConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Map<String, Object> mapData) {
        return mapper.writeValueAsString(mapData);
    }

    @Override
    @SneakyThrows
    public Map<String, Object> convertToEntityAttribute(String json) {
        return mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {});
    }
}