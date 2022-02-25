package com.project.backend.mapper;

import com.project.backend.dto.CustomerDto;
import com.project.backend.entity.Customer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    @Named("CustomerDtoToCustomerEntity")
    Customer CustomerDtoToCustomerEntity(CustomerDto customerDto);

    @Named("CustomerEntityToCustomerDto")
    CustomerDto CustomerEntityToCustomerDto(Customer customer);

    @IterableMapping(qualifiedByName = "CustomerEntitiesToCustomerDto")
    List<CustomerDto> CustomerEntitiesToCustomerDto(List<Customer> customers);

}
