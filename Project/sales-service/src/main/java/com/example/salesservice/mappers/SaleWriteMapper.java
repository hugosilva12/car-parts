package com.example.salesservice.mappers;


import com.example.salesservice.domain.Sale;
import com.example.salesservice.dtos.write.SaleWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface SaleWriteMapper extends EntityToDtoMapper<SaleWriteDto, Sale> {
}
