package org.communis.javawebintro.first.service;

import org.communis.javawebintro.first.entity.FirstDataSourceEntity;
import org.communis.javawebintro.first.repository.FirstDatabaseEntityRepository;
import org.communis.javawebintro.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstDatasourceEntityService implements CrudService<FirstDataSourceEntity, Long> {
    private final FirstDatabaseEntityRepository repository;

    public FirstDatasourceEntityService(FirstDatabaseEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long save(FirstDataSourceEntity entity) {
        return repository.save(entity).getId();
    }

    @Override
    public FirstDataSourceEntity findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<FirstDataSourceEntity> findAll() {
        return repository.findAll();
    }


    @Override
    public Long updateById(Long id) {
        final FirstDataSourceEntity entity = repository.findOne(id);
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }

    @Override
    public Long update(FirstDataSourceEntity entity) {
        return repository.save(entity).getId();
    }

    @Override
    public void delete(FirstDataSourceEntity entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }
}
