package com.khacngoc.jobfinder.DTO.Response;

import java.time.LocalDateTime;
import com.khacngoc.jobfinder.Entity.Career;

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
public class PostResponse {
    String id;
    String title;
    String description;
    Career career;
    Float exp;
    String level;
    Float salaries;
    AddressResponse address;
    UserDTOResponse user;
    LocalDateTime createAt;
    String status;
}
