package com.khacngoc.jobfinder.DTO.Request;

import java.time.LocalDateTime;

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
public class WorkExperienceRequest {
    String companyName;
    String title;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String jobDescription;
}
