package com.stubee.rollingadapter.out.common.mapper;

public interface GenericMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

}