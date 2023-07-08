package com.stubee.rollingadapter.common.mapper;

public interface GenericMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

}