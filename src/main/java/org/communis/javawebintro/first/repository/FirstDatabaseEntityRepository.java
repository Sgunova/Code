package org.communis.javawebintro.first.repository;

import org.communis.javawebintro.first.entity.FirstDataSourceEntity;
import org.communis.javawebintro.repository.BaseEntityRepository;

import java.util.List;

public interface FirstDatabaseEntityRepository extends BaseEntityRepository<FirstDataSourceEntity, Long> {
    List<FirstDataSourceEntity> findAllByNameContainsOrderByName(String name);
}
