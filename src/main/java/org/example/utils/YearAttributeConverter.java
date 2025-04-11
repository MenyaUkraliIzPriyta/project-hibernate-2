package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter
public class YearAttributeConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year year) {
        return year == null ? null : year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer integer) {
        return integer == null ? null : Year.of(integer);
    }

}
