package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.enums.Rating;

@Converter
public class RatingAttributeConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating == null ? null : rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String string) {
        return string == null ? null : Rating.fromDbValue(string);
    }

}
