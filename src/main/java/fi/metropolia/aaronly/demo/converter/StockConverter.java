package fi.metropolia.aaronly.demo.converter;

import jakarta.persistence.*;

@Converter
public class StockConverter implements AttributeConverter<Integer, Integer>{
    @Override
    public Integer convertToDatabaseColumn(Integer attribute) {
        return attribute;
    }
    @Override
    public Integer convertToEntityAttribute(Integer dbData) {
        return dbData;
    }
}
