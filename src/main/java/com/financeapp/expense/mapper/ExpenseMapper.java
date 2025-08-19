package com.financeapp.expense.mapper;

import com.financeapp.expense.dtos.ExpenseCreateRequest;
import com.financeapp.expense.dtos.ExpenseResponse;
import com.financeapp.expense.dtos.ExpenseUpdateRequest;
import com.financeapp.expense.domain.Expense;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "idExpense", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", source = "category")
    Expense toEntity(ExpenseCreateRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idExpense", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "category", source = "category")
    void updateEntityFromDto(ExpenseUpdateRequest dto, @MappingTarget Expense entity);

    ExpenseResponse toResponse(Expense entity);

    List<ExpenseResponse> toResponseList(List<Expense> entities);
}
