package com.khacngoc.jobfinder.Mapper;

import org.mapstruct.Mapper;

import com.khacngoc.jobfinder.DTO.Request.AddressRequest;
import com.khacngoc.jobfinder.Entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressRequest request);

}
