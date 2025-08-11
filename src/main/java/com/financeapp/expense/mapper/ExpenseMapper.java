package com.financeapp.expense.mapper;

import com.financeapp.expense.api.dto.ExpenseCreateRequest;
import com.financeapp.expense.api.dto.ExpenseResponse;
import com.financeapp.expense.api.dto.ExpenseUpdateRequest;
import com.financeapp.expense.domain.Expense;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Expense toEntity(ExpenseCreateRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void toEntity(ExpenseUpdateRequest dto, @MappingTarget Expense entity);

    ExpenseResponse toResponse(Expense entity);
    List<ExpenseResponse> toResponseList(List<Expense> entities);
}
