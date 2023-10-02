package com.acer.sellercenter.sellercenter.controller;

import com.acer.sellercenter.sellercenter.model.BaseEntity;
import com.acer.sellercenter.sellercenter.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public abstract class GenericController<E extends BaseEntity, DTO, S extends GenericService<E, DTO>> {

    protected S service;

    protected GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<DTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable UUID id, @RequestBody DTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
