package com.example.cardisassemblyservice.repository;

import com.example.cardisassemblyservice.domain.ItemCar;
import com.example.cardisassemblyservice.domain.QItemCar;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ItemCarRepository extends PagingAndSortingRepository<ItemCar, UUID>, QuerydslPredicateExecutor<ItemCar>, QuerydslBinderCustomizer<QItemCar> {
    @Override
    default public void customize(final QuerydslBindings bindings, final QItemCar root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
    @Query("SELECT i FROM ItemCar i INNER JOIN FETCH i.itemState INNER JOIN FETCH i.category where i.id = :id ")
    Optional<ItemCar> findById(@Param("id") UUID id);


}