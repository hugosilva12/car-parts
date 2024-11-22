package com.example.purchaseservice.repository;

import com.example.purchaseservice.domain.Car;
import com.example.purchaseservice.domain.QCar;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>, QuerydslPredicateExecutor<Car>, QuerydslBinderCustomizer<QCar> {
    @Override
    default public void customize(final QuerydslBindings bindings, final QCar root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
    Optional<Car> findByVin(String vin);
}