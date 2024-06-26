package com.khacngoc.jobfinder.Mapper;

import org.mapstruct.Mapper;

import com.khacngoc.jobfinder.DTO.Request.PostCreationRequest;
import com.khacngoc.jobfinder.DTO.Response.PostResponse;
import com.khacngoc.jobfinder.Entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreationRequest request);

    PostResponse toPostResponse(Post post);

}
