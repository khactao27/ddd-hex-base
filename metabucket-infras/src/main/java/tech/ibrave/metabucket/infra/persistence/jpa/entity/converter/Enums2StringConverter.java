package tech.ibrave.metabucket.infra.persistence.jpa.entity.converter;

import jakarta.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: nguyendinhthi
 * Date: 27/05/2023
 */
public class Enums2StringConverter<E extends Enum<E>> implements AttributeConverter<List<E>, String> {

    private final Class<E> clazz;
    private final String splitter;

    public Enums2StringConverter(Class<E> clazzType) {
        this.clazz = clazzType;
        this.splitter = ",";
    }

    public Enums2StringConverter(Class<E> clazzType, String splitter) {
        this.clazz = clazzType;
        this.splitter = splitter;
    }

    @Override
    public String convertToDatabaseColumn(List<E> enums) {
        if (CollectionUtils.isEmpty(enums)) {
            return null;
        }
        return enums.stream()
                .map(E::toString)
                .collect(Collectors.joining(splitter));
    }

    @Override
    public List<E> convertToEntityAttribute(String str) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }

        return Arrays.stream(str.split(splitter))
                .map(e -> E.valueOf(clazz, e))
                .collect(Collectors.toList());
    }
}
