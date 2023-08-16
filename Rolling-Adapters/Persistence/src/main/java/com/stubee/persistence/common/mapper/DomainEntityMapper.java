package com.stubee.persistence.common.mapper;

public interface DomainEntityMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

}