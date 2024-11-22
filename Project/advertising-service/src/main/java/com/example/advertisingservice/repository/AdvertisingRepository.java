package com.example.advertisingservice.repository;


import com.example.advertisingservice.domain.Advertising;
import com.example.advertisingservice.domain.QAdvertising;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdvertisingRepository extends PagingAndSortingRepository<Advertising, Long>, QuerydslPredicateExecutor<Advertising>, QuerydslBinderCustomizer<QAdvertising> {

    @Override
    default public void customize(final QuerydslBindings bindings, final QAdvertising root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }

    Optional<Advertising> findByItemCar(UUID itemCar);

}