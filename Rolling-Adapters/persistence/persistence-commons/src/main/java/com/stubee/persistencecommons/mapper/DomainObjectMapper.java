package com.stubee.persistencecommons.mapper;

public interface DomainObjectMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

}