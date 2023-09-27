package com.acer.sellercenter.sellercenter.mappers;

import java.util.List;

public interface DtoMapper<E, DTO> {

    DTO toDto(E entity);

    default List<DTO> toDto(List<E> entity) {
        return entity.stream().map(this::toDto).toList();
    }

    E toEntity(DTO dto);

}
