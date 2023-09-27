package com.acer.sellercenter.sellercenter.repository;

import com.acer.sellercenter.sellercenter.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
}
