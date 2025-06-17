package org.example.shorturl.mappers;

import org.example.shorturl.dtos.UrlCreateDto;
import org.example.shorturl.entities.Url;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {
    Url toEntity(UrlCreateDto urlCreateDto);

}