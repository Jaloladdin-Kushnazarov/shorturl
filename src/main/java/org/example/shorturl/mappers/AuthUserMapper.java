package org.example.shorturl.mappers;

import org.example.shorturl.dtos.auth.AuthUserCreateDto;
import org.example.shorturl.entities.AuthUser;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthUserMapper {

    // DTO'dan toza yangi Entity yaratish
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);

    // Entity'ni faqat null bo'lmagan fieldlar bilan yangilash
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void partialUpdate(AuthUserCreateDto authUserCreateDto, @MappingTarget AuthUser authUser);
}
