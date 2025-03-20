package dev.ducnguyen.social_network_project.mapper;

import dev.ducnguyen.social_network_project.dto.request.PostCreateRequest;
import dev.ducnguyen.social_network_project.dto.request.PostUpdateRequest;
import dev.ducnguyen.social_network_project.dto.response.PostResponse;
import dev.ducnguyen.social_network_project.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreateRequest request);

    PostResponse toPostResponse(Post post);

    void updatePost(@MappingTarget Post post, PostUpdateRequest request);
}
