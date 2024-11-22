package com.example.salesservice.repository;

import com.example.salesservice.domain.QSale;
import com.example.salesservice.domain.Sale;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface SaleRepository extends PagingAndSortingRepository<Sale, UUID>, QuerydslPredicateExecutor<Sale>, QuerydslBinderCustomizer<QSale> {

    @Override
    default public void customize(final QuerydslBindings bindings, final QSale root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
    @Query("SELECT s FROM Sale s where s.client = :id ")
    List<Sale> findAllByClient(@Param("id") UUID id);

}