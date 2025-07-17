package spring_reactive.com.mapper;

import org.springframework.beans.BeanUtils;
import spring_reactive.com.dto.AddressDto;
import spring_reactive.com.dto.CustomerDto;
import spring_reactive.com.entity.Address;
import spring_reactive.com.entity.Customer;

import java.util.List;

public class EntityAndDtoMapper {
    public static Customer toCustomerEntity(CustomerDto dto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto,customer);
        return  customer;
    }

    public static Address toAddressEntity(AddressDto dto, Long customerId){
        Address address = new Address();
        BeanUtils.copyProperties(dto,address);
        address.setCustomerId(customerId);
        return  address;
    }


    public static AddressDto toAddressDto(Address address){
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return  addressDto;

    }


    public static CustomerDto toCustomerDto(Customer  customer, List<Address> addresses){
        CustomerDto customerDto  = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        if (!addresses.isEmpty()){
            List<AddressDto> addressDtos = addresses.stream()
                    .map(EntityAndDtoMapper::toAddressDto).toList();

            customerDto.setAddresses(addressDtos);
        }
        return  customerDto;

    }

}
