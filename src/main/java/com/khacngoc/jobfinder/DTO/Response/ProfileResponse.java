package com.khacngoc.jobfinder.DTO.Response;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse {
    String id;
    String fullName;
    String phoneNumber;
    Date dateOfBirth;
    Boolean gender;
    String desiredLocation;
    String position;
    Set<String> advancedSkill;
    Set<String> softSkill;
    Set<String> interest;
    WorkExperienceResponse workExperience;
    AddressResponse address;

}
