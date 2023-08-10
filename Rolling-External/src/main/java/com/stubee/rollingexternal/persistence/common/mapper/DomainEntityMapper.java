package com.stubee.rollingexternal.persistence.common.mapper;

public interface DomainEntityMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

}