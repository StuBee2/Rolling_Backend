package com.stubee.persistencecommons.support;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.stubee.rollingdomains.common.model.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryDSLHelperSupport<T> {

    private final JPAQueryFactory jpaQueryFactory;

    public T findById(EntityPath<T> target, Predicate id) {
        return jpaQueryFactory
                .selectFrom(target)
                .where(id)
                .fetchOne();
    }

    public Integer existsByOption(EntityPath<T> target, Predicate option) {
        return jpaQueryFactory
                .selectOne()
                .from(target)
                .where(option)
                .fetchFirst();
    }

    public List<T> findByOptionWithPagination(EntityPath<T> target, Predicate option, PageRequest pageRequest, OrderSpecifier<?> order) {
        return jpaQueryFactory
                .selectFrom(target)
                .where(option)
                .orderBy(order)
                .offset((pageRequest.page()-1)*pageRequest.size())
                .limit(pageRequest.size())
                .fetch();
    }

    public List<T> findWithOrderAndLimit(EntityPath<T> target, OrderSpecifier<?> order, int limit) {
        return jpaQueryFactory
                .selectFrom(target)
                .orderBy(order)
                .limit(limit)
                .fetch();
    }

}