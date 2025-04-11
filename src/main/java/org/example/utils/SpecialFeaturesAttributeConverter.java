package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Converter
public class SpecialFeaturesAttributeConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return attribute != null ? String.join(",", attribute) : null;
    }

    @Override
    public Set<String> convertToEntityAttribute(String string) {
        if (string == null || string.trim().isEmpty()) return Collections.emptySet();
        return new HashSet<>(Arrays.asList(string.split(",")));
    }
}
