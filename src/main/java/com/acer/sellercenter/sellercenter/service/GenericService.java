package com.acer.sellercenter.sellercenter.service;

import com.acer.sellercenter.sellercenter.mappers.DtoMapper;
import com.acer.sellercenter.sellercenter.model.BaseEntity;
import com.acer.sellercenter.sellercenter.repository.GenericRepository;
import com.acer.sellercenter.sellercenter.utils.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface GenericService<E extends BaseEntity, DTO> {
    GenericRepository<E> getRepository();

    DtoMapper<E, DTO> getDtoMapper();

    default List<DTO> findAll() {
        List<E> entities = getRepository().findAll();
        return getDtoMapper().toDto(entities);
    }

    default DTO findById(UUID id) {

        E entity = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));

        return getDtoMapper().toDto(entity);
    }

    default DTO create(DTO dto) {
        E entity = getDtoMapper().toEntity(dto);
        return getDtoMapper().toDto(getRepository().save(entity));
    }

    default DTO update(UUID id, DTO dto) {

        getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));

        E updatedEntity = getDtoMapper().toEntity(dto);

        getRepository().save(updatedEntity);

        return getDtoMapper().toDto(getRepository().save(updatedEntity));
    }

    default void deleteById(UUID id) {
        E entity = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        entity.setDeleted(true);
        getRepository().save(entity);
    }
}