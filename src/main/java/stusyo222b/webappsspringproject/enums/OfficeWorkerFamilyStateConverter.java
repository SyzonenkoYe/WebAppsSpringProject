package stusyo222b.webappsspringproject.enums;

import jakarta.persistence.AttributeConverter;

public class OfficeWorkerFamilyStateConverter implements AttributeConverter<OfficeWorkerFamilyState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OfficeWorkerFamilyState pl) {
        return pl.ordinal();
    }


    @Override
    public OfficeWorkerFamilyState convertToEntityAttribute(Integer codPL) {
        return OfficeWorkerFamilyState.getOfficeWorkerFamilyStateById(codPL);
    }
}
