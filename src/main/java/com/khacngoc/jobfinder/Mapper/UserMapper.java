package com.khacngoc.jobfinder.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.khacngoc.jobfinder.DTO.Request.ProfileRequest;
import com.khacngoc.jobfinder.DTO.Request.RecruiterRequest;
import com.khacngoc.jobfinder.DTO.Request.UserDTO;
import com.khacngoc.jobfinder.DTO.Response.UserDTOResponse;
import com.khacngoc.jobfinder.Entity.Profile;
import com.khacngoc.jobfinder.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO request);

    User updateUser(@MappingTarget User user, UserDTO request);

    UserDTOResponse toUserResponse(User user);

    Profile toProfile(ProfileRequest request);

    Profile toRecruiter(RecruiterRequest request);
}
