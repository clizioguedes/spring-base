package com.acer.sellercenter.sellercenter.controller;

import com.acer.sellercenter.sellercenter.dto.ApiResponseDTO;
import com.acer.sellercenter.sellercenter.model.BaseEntity;
import com.acer.sellercenter.sellercenter.service.GenericService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * A generic controller providing common CRUD operations for entities in the application.
 * This abstract class defines methods for handling HTTP requests related to entity management.
 *
 * @param <E>   The type of the entity extending BaseEntity.
 * @param <DTO> The type of the DTO (Data Transfer Object) associated with the entity.
 * @param <S>   The type of the service extending GenericService for the entity.
 */
@Validated
public abstract class GenericController<E extends BaseEntity, DTO, S extends GenericService<E, DTO>> {

    protected S service;

    /**
     * Constructs a GenericController instance with the provided service.
     *
     * @param service The service associated with the controller.
     */
    protected GenericController(S service) {
        this.service = service;
    }

    /**
     * Get all the entities.
     *
     * @return ResponseEntity containing the list of DTOs and status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<DTO>>> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(new ApiResponseDTO<Page<DTO>>(
                true,
                "Sucess: Entity located successfully.",
                service.findAll(pageable),
                null
        ));
    }

    /**
     * Get an entity by its ID.
     *
     * @param id The ID of the entity to get.
     * @return ResponseEntity containing the DTO and status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<DTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponseDTO<DTO>(
                true,
                "Sucess: Entity has been successfully registered.",
                service.findById(id),
                null
        ));
    }

    /**
     * Save a new entity.
     *
     * @param dto The DTO representing the entity to save.
     * @return ResponseEntity containing the DTO and status 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<ApiResponseDTO<DTO>> create(@Valid @RequestBody DTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<DTO>(
                true,
                "Sucess: Entity created successfully..",
                service.create(dto),
                null
        ));
    }

    /**
     * Update an existing entity.
     *
     * @param id  The ID of the entity to update.
     * @param dto The DTO representing the updated entity.
     * @return ResponseEntity containing the DTO and status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<DTO>> update(@PathVariable Long id, @Valid @RequestBody DTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<DTO>(
                true,
                "Sucess: Entity has been successfully updated.",
                service.update(id, dto),
                null
        ));
    }

    /**
     * Delete an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     * @return ResponseEntity with status 200 (OK).
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDTO<DTO>> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDTO<DTO>(
                true,
                "Sucess: Entity has been successfully removed.",
                null,
                null
        ));
    }

}

