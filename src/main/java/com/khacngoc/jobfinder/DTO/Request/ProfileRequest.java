package com.khacngoc.jobfinder.DTO.Request;

import java.util.Date;
import java.util.Set;
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
public class ProfileRequest {
    String fullName;
    String phoneNumber;
    Date dateOfBirth;
    Boolean gender;
    String desiredLocation;
    String position;
    Set<String> advancedSkill;
    Set<String> softSkill;
    Set<String> interest;
    WorkExperienceRequest workExperience;
    AddressRequest address;
}
