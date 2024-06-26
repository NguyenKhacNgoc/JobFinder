package com.khacngoc.jobfinder.DTO.Request;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecruiterRequest {
    String fullName;
    String phoneNumber;
    Date dateOfBirth;
    AddressRequest address;
}
