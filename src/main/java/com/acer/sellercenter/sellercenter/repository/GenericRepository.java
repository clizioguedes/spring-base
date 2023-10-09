package com.acer.sellercenter.sellercenter.repository;

import com.acer.sellercenter.sellercenter.model.BaseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    @Override
    @Transactional
    default void deleteById(Long id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            entity.get().setDeleted(true);
            save(entity.get());
        }
    }

    @Override
    @Transactional
    default void delete(T obj) {
        obj.setDeleted(true);
        save(obj);
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> arg0) {
        arg0.forEach(entity -> deleteById(entity.getId()));
    }

}
