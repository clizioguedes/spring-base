package com.acer.sellercenter.sellercenter.service;

import com.acer.sellercenter.sellercenter.mappers.DtoMapper;
import com.acer.sellercenter.sellercenter.model.BaseEntity;
import com.acer.sellercenter.sellercenter.repository.GenericRepository;
import com.acer.sellercenter.sellercenter.utils.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


public interface GenericService<E extends BaseEntity, DTO> {
    GenericRepository<E> getRepository();

    DtoMapper<E, DTO> getDtoMapper();

    default Page<DTO> findAll(Pageable pageable) {
        Page<E> entityPage = getRepository().findAll(pageable);
        return new PageImpl<>(getDtoMapper().toDto(entityPage.getContent()), pageable, entityPage.getTotalElements());
    }

    default DTO findById(Long id) {

        E entity = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));

        return getDtoMapper().toDto(entity);
    }

    default DTO create(DTO dto) {
        E entity = getDtoMapper().toEntity(dto);
        return getDtoMapper().toDto(getRepository().save(entity));
    }

    default DTO update(Long id, DTO dto) {

        getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));

        E updatedEntity = getDtoMapper().toEntity(dto);

        getRepository().save(updatedEntity);

        return getDtoMapper().toDto(getRepository().save(updatedEntity));
    }

    default void deleteById(Long id) {
        E entity = getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        entity.setDeleted(true);
        getRepository().save(entity);
    }
}