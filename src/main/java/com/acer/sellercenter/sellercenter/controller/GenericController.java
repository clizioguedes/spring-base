package com.acer.sellercenter.sellercenter.controller;

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


@Validated
public abstract class GenericController<E extends BaseEntity, DTO, S extends GenericService<E, DTO>> {

    protected S service;

    protected GenericController(S service) {
        this.service = service;
    }

    /**
     * Get all the entities.
     *
     * @return the list of DTO and status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<Page<DTO>> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    /**
     * Get an entity.
     *
     * @param id the id of the entity to get.
     * @return the ResponseEntity with DTO and status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Save a new entity.
     *
     * @param dto the entity to save.
     * @return the ResponseEntity with DTO and status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    /**
     * Update an existing entity.
     *
     * @param dto the entity to update.
     * @return the ResponseEntity with DTO and status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable Long id, @Valid @RequestBody DTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    /**
     * Delete an entity.
     *
     * @param id the id of the entity to delete.
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}

